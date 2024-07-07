/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminControllers;

import DAL.OrderDao;
import Models.Order;
import Models.OrderCustomer;
import Models.OrderGuest;
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
 * @author huyca
 */
public class OrderStatusControllers extends HttpServlet {

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
            out.println("<title>Servlet OrderStatusControllers</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderStatusControllers at " + request.getContextPath() + "</h1>");
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
        String statusId = request.getParameter("status");
        OrderDao orderDao = new OrderDao();

        List<OrderCustomer> customerOrders = orderDao.getOrdersByStatusForCustomers(statusId);
        List<OrderGuest> guestOrders = orderDao.getOrdersByStatusForGuests(statusId);

        List<Order> order = new ArrayList<>();
        for (OrderCustomer customerOrder : customerOrders) {
            Order orders = new Order();
            orders.setOrderId(customerOrder.getOrderDetails().get(0).getOrderCId());
            orders.setFullName(customerOrder.getAccount().getFullName());
            orders.setEmail(customerOrder.getAccount().getEmail());
            orders.setPhoneNumber(customerOrder.getAccount().getPhoneNumber());
            orders.setAddress(customerOrder.getAccount().getAddress());
            orders.setTotalPrice(customerOrder.getTotalPrice());
            orders.setDate(customerOrder.getDate());
            orders.setStatusName(customerOrder.getStatus().getStatusName());
            orders.setOrderType("Khách hàng");
            order.add(orders);
        }

        for (OrderGuest guestOrder : guestOrders) {
            Order orders = new Order();
            orders.setOrderId(guestOrder.getOrderDetails().get(0).getOrderGId());
            orders.setFullName(guestOrder.getFullName());
            orders.setEmail(guestOrder.getEmail());
            orders.setPhoneNumber(guestOrder.getPhoneNumber());
            orders.setAddress(guestOrder.getAddress());
            orders.setTotalPrice(guestOrder.getTotalPrice());
            orders.setDate(guestOrder.getDate());
            orders.setStatusName(guestOrder.getStatus().getStatusName());
            orders.setOrderType("Khách vãng lai");
            order.add(orders);
        }
        String statusName = null;
        if ("1".equals(statusId)) {
            statusName = "Chờ xác nhận";
        } else if ("2".equals(statusId)) {
            statusName = "Đã xác nhận";
        } else if ("3".equals(statusId)) {
            statusName = "Chờ giao hàng";
        } else if ("4".equals(statusId)) {
            statusName = "Hoàn thành";
        } else if ("5".equals(statusId)) {
            statusName = "Đã hủy";
        }

        request.setAttribute("recentOrders", order);
        request.setAttribute("statusName", statusName);
        request.getRequestDispatcher("Views/Admin/OrderStatus.jsp").forward(request, response);
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
