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
public class CategoryController extends HttpServlet {
   
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
            out.println("<title>Servlet CategoryController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CategoryController at " + request.getContextPath () + "</h1>");
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
        CategoryDao dal = new CategoryDao();
        
        String searchtext = request.getParameter("s");
          String index = request.getParameter("index");
        if(request.getParameter("s")!=null){
            int indexx;
             if(request.getParameter("index")==null)
       {
           indexx = 1;
       }else{
         indexx = Integer.parseInt(index);
        }
        String a = "";
          if(searchtext.isBlank()){
               a = "";
          }else{
              a = searchtext;
              a= a.trim();
          }
      
        int count = dal.getToralCategorybyname(a);
        int endpage = count/3;
        if(count%3==0){           
        }else{
            endpage++;
        }
          
         
           List<Category> category = dal.getallCategorypseachbyname(indexx,a);
           request.setAttribute("endP", endpage);
          request.setAttribute("tag", indexx);
           request.setAttribute("s", searchtext);
         request.setAttribute("category", category);
         request.getRequestDispatcher("Views/Admin/Category.jsp").forward(request, response);
        }else{
      
      int indexx;
        if(request.getParameter("index")==null)
       {
           indexx = 1;
       }else{
         indexx = Integer.parseInt(index);
        }
      
      
        int count = dal.getToralCategory();
        int endpage = count/3;
        if(count%3==0){           
        }else{
            endpage++;
        }
          request.setAttribute("endP", endpage);
          request.setAttribute("tag", indexx);
          List<Category> category = dal.getallCategorypage(indexx);
        request.setAttribute("category", category);
        request.getRequestDispatcher("Views/Admin/Category.jsp").forward(request, response);
        }
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
       String text = request.getParameter("s");
       
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
