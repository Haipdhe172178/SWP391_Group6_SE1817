/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.StaffController;

import DAL.OrderDao;
import Models.Orders;
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
 * @author Hai Pham
 */
public class StaffDashboardController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
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
            out.println("<title>Servlet StaffDashboardController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StaffDashboardController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String status = request.getParameter("status");
         String text = request.getParameter("text");
         
         
        if(status==null && text ==null ){
            
         List<Orders> list = new ArrayList<>();
        OrderDao dal = new OrderDao();
        String index = request.getParameter("index");

        int indexx;
        if (request.getParameter("index") == null) {
            indexx = 1;
        } else {
            indexx = Integer.parseInt(index);
        }

        int count = dal.getToralOrder();

        int endpage = count / 10;
        if (count % 10 == 0) {
        } else {
            endpage++;
        }
        list = dal.getallOrder(indexx);
        request.setAttribute("endP", endpage);
        request.setAttribute("tag", indexx);

        request.setAttribute("list", list);
        request.getRequestDispatcher("Views/Staff/StaffDashboard.jsp").forward(request, response);
        } else if(status!=null && !(status.isEmpty())){
            
            List<Orders> list = new ArrayList<>();
        OrderDao dal = new OrderDao();
        String index = request.getParameter("index");

        int indexx;
        if (request.getParameter("index") == null) {
            indexx = 1;
        } else {
            indexx = Integer.parseInt(index);
        }
         
        int count = dal.getToralOrderByStatus(status);

        int endpage = count / 10;
        if (count % 10 == 0) {
        } else {
            endpage++;
        }
        list = dal.getallOrderbyStatus(indexx,status);
        request.setAttribute("status", status);
        request.setAttribute("endP", endpage);
        request.setAttribute("tag", indexx);

        request.setAttribute("list", list);
        request.getRequestDispatcher("Views/Staff/StaffDashboard.jsp").forward(request, response);
            
            
            
            
        }else if(text!=null){
             List<Orders> list = new ArrayList<>();
        OrderDao dal = new OrderDao();
        String index = request.getParameter("index");

        int indexx;
        if (request.getParameter("index") == null) {
            indexx = 1;
        } else {
            indexx = Integer.parseInt(index);
        }
     
        int count = dal.getToralOrderbysearch(text);

        int endpage = count / 10;
        if (count % 10 == 0) {
        } else {
            endpage++;
        }
        list = dal.getallOrderbyText(indexx,text);
        request.setAttribute("text", text);
        request.setAttribute("endP", endpage);
        request.setAttribute("tag", indexx);

        request.setAttribute("list", list);
        request.getRequestDispatcher("Views/Staff/StaffDashboard.jsp").forward(request, response);
            
        }else{
             List<Orders> list = new ArrayList<>();
        OrderDao dal = new OrderDao();
        String index = request.getParameter("index");

        int indexx;
        if (request.getParameter("index") == null) {
            indexx = 1;
        } else {
            indexx = Integer.parseInt(index);
        }

        int count = dal.getToralOrder();

        int endpage = count / 10;
        if (count % 10 == 0) {
        } else {
            endpage++;
        }
        list = dal.getallOrder(indexx);
        request.setAttribute("endP", endpage);
        request.setAttribute("tag", indexx);

        request.setAttribute("list", list);
        request.getRequestDispatcher("Views/Staff/StaffDashboard.jsp").forward(request, response);
        }       
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
