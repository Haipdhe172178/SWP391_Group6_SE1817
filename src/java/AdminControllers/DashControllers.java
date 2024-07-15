/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminControllers;

import DAL.OrderDao;
import Models.Account;
import Models.OrderCustomer;
import Models.OrderDetailCustomer;
import Models.OrderDetailGuest;
import Models.OrderGuest;
import Models.OrderStatus;
import Models.Orders;
import Models.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author huyca
 */
@WebServlet(name = "DashControllers", urlPatterns = {"/dash"})
public class DashControllers extends HttpServlet {

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
            out.println("<title>Servlet DashControllers</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DashControllers at " + request.getContextPath() + "</h1>");
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
        OrderDao orderDao = new OrderDao();

        List<OrderCustomer> customerOrders = orderDao.getAllOrderCustomers();
        List<OrderGuest> guestOrders = orderDao.getAllOrderGuests();
        int totalQuantity = orderDao.OrderCount();
        float totalRevenue = getTotalRevenue(customerOrders, guestOrders);

        List<Orders> order = orderDao.getOrder();

        List<Product> mostPurchasedProducts = orderDao.getMostPurchasedProducts();
        List<Map<String, Object>> topBuyers = orderDao.getTopBuyers();

        request.setAttribute("totalQuantity", totalQuantity);
        request.setAttribute("totalRevenue", totalRevenue);
        request.setAttribute("recentOrders", order);

        request.setAttribute("mostPurchasedProducts", mostPurchasedProducts);
        request.setAttribute("topBuyers", topBuyers);
        List<OrderStatus> orderStatusCounts = orderDao.getOrderStatusCounts();
        request.setAttribute("orderStatusCounts", orderStatusCounts);

        request.getRequestDispatcher("Views/Admin/Dashboard.jsp").forward(request, response);
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

    private float getTotalRevenue(List<OrderCustomer> customerOrders, List<OrderGuest> guestOrders) {
        float totalRevenue = 0;
        for (OrderCustomer order : customerOrders) {
            if (order.getStatus().getStatusId() == 4) {
                totalRevenue += order.getTotalPrice();
            }
        }
        for (OrderGuest order : guestOrders) {
            if (order.getStatus().getStatusId() == 4) {
                totalRevenue += order.getTotalPrice();
            }
        }
        return totalRevenue;
    }
}
