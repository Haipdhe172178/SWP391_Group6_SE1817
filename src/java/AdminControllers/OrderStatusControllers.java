/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminControllers;

import DAL.OrderDao;
import Models.Order;
import Models.OrderCustomer;
import Models.OrderGuest;
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

        String indexPage = request.getParameter("index");
        String sort = request.getParameter("sort");
        int index;
        if (indexPage != null) {
            index = Integer.parseInt(indexPage);
        } else {
            index = 1;
        }

        OrderDao orderDao = new OrderDao();
        List<Orders> orders;
        int totalOrders = 0;
        String sortBy = null;
        String sortDirection = "ASC";

        if (sort != null && !sort.isEmpty()) {
            switch (sort) {
                case "priceasc":
                    sortBy = "TotalPrice";
                    sortDirection = "ASC";
                    break;
                case "pricedesc":
                    sortBy = "TotalPrice";
                    sortDirection = "DESC";
                    break;
                case "dateasc":
                    sortBy = "Date";
                    sortDirection = "ASC";
                    break;
                case "datedesc":
                    sortBy = "Date";
                    sortDirection = "DESC";
                    break;
            }
        }

        totalOrders = orderDao.getTotalOrdersByStatus(statusId);
        if (sortBy != null) {
            orders = orderDao.getOrderByStatusSorted(index, statusId, sortBy, sortDirection);
        } else {
            orders = orderDao.getOrderByStatus(index, statusId);
        }

        int endPage = totalOrders / 5;
        if (totalOrders % 5 != 0) {
            endPage++;
        }

        String statusName = null;
        switch (statusId) {
            case "1":
                statusName = "Chờ xác nhận";
                break;
            case "2":
                statusName = "Đã xác nhận";
                break;
            case "3":
                statusName = "Chờ giao hàng";
                break;
            case "4":
                statusName = "Hoàn thành";
                break;
            case "5":
                statusName = "Đã hủy";
                break;
        }

        String query = "";
        if (sort != null && !sort.isEmpty()) {
            query += "&sort=" + sort;
        }

        // Đặt các thuộc tính cho request
        request.setAttribute("statusId", statusId);
        request.setAttribute("ListA", orders);
        request.setAttribute("endP", endPage);
        request.setAttribute("tag", index);
        request.setAttribute("statusName", statusName);
        request.setAttribute("query", query);

        // Chuyển tiếp request tới trang JSP
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
