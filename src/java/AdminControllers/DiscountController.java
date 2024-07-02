/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package AdminControllers;

import DAL.DiscountDAO;
import DAL.HomeDAO;
import Models.UsedCoupon;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class DiscountController extends HttpServlet {
   
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
            out.println("<title>Servlet DiscountController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DiscountController at " + request.getContextPath () + "</h1>");
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
       ArrayList<UsedCoupon> data = new ArrayList<>();
       HomeDAO dal1 = new HomeDAO();
       String date = dal1.getTime();
       
          DiscountDAO dal = new DiscountDAO();
          String status = request.getParameter("op");
            int status1 = 3;
          if(status==null){
                
            }else{
                 status1 = Integer.parseInt(status);
            }
          
         
     
         
          
          
        String searchtext = request.getParameter("s");
          String index = request.getParameter("index");
          
          
        if(request.getParameter("submit")!=null || searchtext != null){
            
            
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
      
        int count = dal.getToralDiscountbyname(a);
        int endpage = count/3;
        if(count%3==0){           
        }
        
        
        else{
            endpage++;
        }
                  
             data = dal.listcodepageseachbytype(indexx,a);
              request.setAttribute("endP", endpage);
          request.setAttribute("tag", indexx);    
           request.setAttribute("s", searchtext);
           request.setAttribute("n", status);
       request.setAttribute("date", data);
        request.setAttribute("date1", date);
      
           request.getRequestDispatcher("Views/Admin/DataDiscount.jsp").forward(request, response); 
        }
        else if (status1 == 0 || status1 == 1 && searchtext ==null){
          int indexx;
        if(request.getParameter("index")==null)
       {
           indexx = 1;
       }else{
         indexx = Integer.parseInt(index);
        }
            
            
            int count = dal.getToralDiscountbystatus(status);
        int endpage = count/3;
        if(count%3==0){           
        }
        
        
        else{
            endpage++;
        }
                  
             data = dal.listcodepageseachbystatus(indexx,status);
              request.setAttribute("endP", endpage);
          request.setAttribute("tag", indexx);    
           request.setAttribute("s", searchtext);
       request.setAttribute("data", data);
       request.setAttribute("n", status);
       request.setAttribute("date1", date);
           request.getRequestDispatcher("Views/Admin/DataDiscount.jsp").forward(request, response); 
        }
        
        
        else{
      
    
            
            
            
            int indexx;
        if(request.getParameter("index")==null)
       {
           indexx = 1;
       }else{
         indexx = Integer.parseInt(index);
        }
      
         int count = dal.getToralDiscount();
      
        int endpage = count/3;
        if(count%3==0){           
        }else{
            endpage++;
        }
          request.setAttribute("endP", endpage);
          request.setAttribute("tag", indexx);
               data = dal.listcodepage(indexx);
               request.setAttribute("data", data);
             request.setAttribute("n", status);
              request.setAttribute("date1", date);
           request.getRequestDispatcher("Views/Admin/DataDiscount.jsp").forward(request, response); 
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
        processRequest(request, response);
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
