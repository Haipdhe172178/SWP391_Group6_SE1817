/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package AdminControllers;

import static AdminControllers.AddCategory.isValidName;
import DAL.CategoryDao;
import Models.Category;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
public class UpdateCategory extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateCategory</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateCategory at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
      
        String id_raw = request.getParameter("id");        
        int id = Integer.parseInt(id_raw);
        Category category = new Category();
         CategoryDao dal =new CategoryDao();
        category = dal.getCategoryByID(id);
        request.setAttribute("category", category);
        request.getRequestDispatcher("Views/Admin/UpdateCategory.jsp").forward(request, response);
      
       
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String name = request.getParameter("name");
         String id = request.getParameter("id");
        CategoryDao dal = new CategoryDao();
       if(isValidName(name)){
          dal.updateCategory(id,name); 
          response.sendRedirect("category");
       }else{
          
           String error = "Nhập không đúng dữ liệu ";
           request.setAttribute("error", error);
           String id_raw = request.getParameter("id");        
        int id1 = Integer.parseInt(id_raw);
        Category category = new Category();       
        category = dal.getCategoryByID(id1);
        request.setAttribute("category", category);
        request.getRequestDispatcher("Views/Admin/UpdateCategory.jsp").forward(request, response);
       }
    }
    public static boolean isValidName(String name) {
        // Loại bỏ khoảng trắng ở đầu và cuối
        name = name.trim();
        
        // Kiểm tra độ dài
        if (name.length() < 2 || name.length() > 50) {
            return false;
        }

        // Kiểm tra ký tự hợp lệ
        String regex = "^[a-zA-ZàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ '-]+$";
        if (!name.matches(regex)) {
            return false;
        }

        // Kiểm tra khoảng trắng thừa ở giữa
        String[] words = name.split("\\s+");
        for (String word : words) {
            if (word.length() < 1) {
                return false;
            }
        }

        return true;
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
