/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package AdminControllers;

import DAL.DiscountDAO;
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
public class AddcodeController extends HttpServlet {
   
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
            out.println("<title>Servlet AddcodeController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddcodeController at " + request.getContextPath () + "</h1>");
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
        
        
        if(request.getParameter("submit") != null ){
                String codename = request.getParameter("meomeo");
            String discountStr = request.getParameter("discount1");
            String coupon_type = request.getParameter("theloai");
             String qualityStr = request.getParameter("soluong");          
                 DiscountDAO dal = new DiscountDAO();
               String error="";
               try {
                float discount = Float.parseFloat(discountStr);
                int quality = Integer.parseInt(qualityStr);
                if(codename.isBlank()||coupon_type.isBlank()){
                      error = "Bạn không được để trống";
                 request.setAttribute("error", error);
                
                }else{
                if(discount>0 && quality>0){
                   
                     ArrayList<UsedCoupon> data = new ArrayList<>();
                     data = dal.listcode();
                     int k = 0 ;
                     for (int i = 0; i < data.size(); i++) {
                        if(data.get(i).getCodeName().equalsIgnoreCase(codename)){
                             error = "Mã code đã tồn tại";
                            k++;
                          request.setAttribute("error", error);
                        
                          
                        }
                                
                    }
                     if(k==0){
                      dal.addDiscount(codename, discountStr, coupon_type, qualityStr);
                      
                     
                     }
                             
                   
                }else{
                      error = "Bạn phải nhập phần trăm > 0 và số lượng > 0";
                 request.setAttribute("error", error);
                 
                }
                }
                
                if(error == ""){
                    response.sendRedirect("discount");
                }else{
                     request.getRequestDispatcher("Views/Admin/AddCode.jsp").forward(request, response);
                }
                
            } catch (Exception e) {
                 error = "Bạn nhập số không đúng định dạng phần trăm giảm giá và số lượng mã phát ra";
                 request.setAttribute("error", error);
                   request.getRequestDispatcher("Views/Admin/AddCode.jsp").forward(request, response);
            }
                 
                 
            
         }else{
             request.getRequestDispatcher("Views/Admin/AddCode.jsp").forward(request, response);
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
