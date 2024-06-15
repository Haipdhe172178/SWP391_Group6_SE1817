package AdminControllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


import DAL.HomeDAO;
import DAL.ProductDao;
import Models.ImageBackground;
import Models.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class ImaBGController extends HttpServlet {
   
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
            out.println("<title>Servlet ImaBGController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ImaBGController at " + request.getContextPath () + "</h1>");
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
      
        HomeDAO dal = new HomeDAO();
        ArrayList<ImageBackground> data = new  ArrayList<>();
        data = dal.getImageBackgroundlist();
        request.setAttribute("data", data);
        request.getRequestDispatcher("Views/Admin/ImageBackground.jsp").forward(request, response);
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
           try {
            String Names = request.getParameter("name");
            
            int status = Integer.parseInt(request.getParameter("status"));

            // Xử lý ảnh
            Part part = request.getPart("image");
            String imgProduct = null;
            if (part != null && part.getSize() > 0) {
                String path = request.getServletContext().getRealPath("/img");
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                File image = new File(dir, fileName);
                part.write(image.getAbsolutePath());
                imgProduct = request.getContextPath() + "/img/" + fileName;
            }

            // Thêm sản phẩm vào cơ sở dữ liệu
            HomeDAO dal = new HomeDAO();
            
            if (Names.isBlank()) {
               
               
                   String error = "bạn chưa nhập tên bức ảnh";
                } else {
                    ImageBackground ims = new ImageBackground();
                    ims.setId(1);
                    ims.setName(Names);
                   
                    ims.setUrl(imgProduct);
                    ims.setStatus(status);
                   dal.addImage(ims);
                }
           response.sendRedirect("image");
        } catch (NumberFormatException | IOException | ServletException ex) {
            ex.printStackTrace();
            }
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
