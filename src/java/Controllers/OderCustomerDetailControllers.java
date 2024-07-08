/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers;

import DAL.AccountDAO;
import DAL.OrderDao;
import DAL.ProductDao;
import Models.Account;
import Models.OrderCustomer;
import Models.OrderDetailCustomer;
import Models.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author admin
 */
public class OderCustomerDetailControllers extends HttpServlet {
   
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
            out.println("<title>Servlet OderCustomerDetailControllers</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OderCustomerDetailControllers at " + request.getContextPath () + "</h1>");
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
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        ProductDao productDAO = new ProductDao();
        OrderDao orderDAO = new OrderDao();
        AccountDAO accountDAO = new AccountDAO();

        // Lấy chi tiết đơn hàng
        List<OrderDetailCustomer> orderDetails = orderDAO.getOrderDetailCustomers(orderId);
        for (OrderDetailCustomer orderDetail : orderDetails) {
            int productId = orderDetail.getProductId();
            Product product = productDAO.getProductById(productId);
            orderDetail.setProduct(product); 
        }
        
        // Lấy danh sách các đơn hàng
        List<OrderCustomer> orders = orderDAO.getOrderCustomersByAccountId(orderId); // Thay thế với phương thức thực tế để lấy các đơn hàng theo orderId
        
        // Duyệt qua từng đơn hàng
        for (OrderCustomer order : orders) {
            int accountId = order.getAccount().getAccountId(); // Giả sử bạn có phương thức getAccountId trong OrderCustomer
            Account account = accountDAO.getAccountByid(accountId); // Giả sử bạn có phương thức getAccountById trong AccountDAO
            order.setAccount(account); // Giả sử bạn có phương thức setAccount trong OrderCustomer
        }

        request.setAttribute("orderDetails", orderDetails);
        request.setAttribute("orders", orders); // Gán danh sách các đơn hàng vào request
        request.getRequestDispatcher("Views/OrderCustomerDetail.jsp").forward(request, response);
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
