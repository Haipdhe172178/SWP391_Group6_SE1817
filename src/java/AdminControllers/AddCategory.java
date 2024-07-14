/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package AdminControllers;

import DAL.CategoryDao;
import Models.Category;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class AddCategory extends HttpServlet {
   
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
            out.println("<title>Servlet AddCategory</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddCategory at " + request.getContextPath () + "</h1>");
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
       request.getRequestDispatcher("Views/Admin/AddCategory.jsp").forward(request, response);
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
        CategoryDao dal = new CategoryDao();
       if(isValidName(name)){
           List<Category> cal = new ArrayList<>();
           
           cal = dal.getallCategorys();
           int k = 0;
              for(int i = 0;i<cal.size();i++){
                  if(name.equals(cal.get(i).getCategoryName().trim())){
                  k++;    
                  }
              }
              if(k==0){
                boolean check =  dal.addCategory(name); 
         
            if(check==true){
                 response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Update Status</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }");
                out.println(".popup-box { width: 300px; padding: 20px; background-color: #d4edda; border: 1px solid #c3e6cb; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%); box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); text-align: center; }");
                out.println("</style>");
                out.println("<script type='text/javascript'>");
                out.println("document.addEventListener('DOMContentLoaded', function() {");
                out.println("  var popup = document.createElement('div');");
                out.println("  popup.className = 'popup-box';");
                out.println("  popup.innerHTML = '<h2>Thêm thể loại.</h2><p> thành công.</p>';");
                out.println("  document.body.appendChild(popup);");
                out.println("  setTimeout(function() { popup.style.display = 'none'; window.location.href = 'category'; }, 2000);"); // Chờ 2 giây trước khi chuyển hướng
                out.println("});");
                out.println("</script>");
                out.println("</head>");
                out.println("<body>");
                out.println("</body>");
                out.println("</html>");
            }
          
              }else{
                  request.setAttribute("name", name);
           String error = "Nhập dữ liệu trùng với dữ liệu đã có ";
           request.setAttribute("error", error);
           request.getRequestDispatcher("Views/Admin/AddCategory.jsp").forward(request, response);
              }
         
       }else{
           request.setAttribute("name", name);
           String error = "Nhập không đúng dữ liệu ";
           request.setAttribute("error", error);
           request.getRequestDispatcher("Views/Admin/AddCategory.jsp").forward(request, response);
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
