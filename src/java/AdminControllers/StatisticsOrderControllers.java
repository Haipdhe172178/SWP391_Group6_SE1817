/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminControllers;

import DAL.RevenueDAO;
import Models.Revenue;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author huyca
 */
public class StatisticsOrderControllers extends HttpServlet {

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
            out.println("<title>Servlet StatisticsOrderControllers</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StatisticsOrderControllers at " + request.getContextPath() + "</h1>");
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
        String yearParam = request.getParameter("year");
        String startDateParam = request.getParameter("startDate");
        String endDateParam = request.getParameter("endDate");

        LocalDate startDate;
        LocalDate endDate;
        int year;
        if (yearParam != null) {
            year = Integer.parseInt(yearParam);
        } else {
            year = LocalDate.now().getYear();
        }
        if (startDateParam != null && endDateParam != null) {
            startDate = LocalDate.parse(startDateParam);
            endDate = LocalDate.parse(endDateParam);
        } else {
            startDate = LocalDate.now().withDayOfMonth(1);
            endDate = LocalDate.now();
        }

        // Retrieve order data within the date range
        RevenueDAO revenueDAO = new RevenueDAO();
        List<Revenue> ordersWithCounts = revenueDAO.getOrdersWithCounts(startDate, endDate);
        int[] dailyOrder = new int[31];

        for (Revenue revenue : ordersWithCounts) {
            int day = revenue.getOrderDay();
            int totalOrders = (int) revenue.getTotalRevenue();
            dailyOrder[day - 1] = totalOrders;
        }
        int totalOrders = revenueDAO.getTotalOrdersCount(startDate, endDate);

        List<Revenue> monthlyOrderList = revenueDAO.getOrderMonth(year);
        int[] monthlyOrderData = new int[12];
        for (Revenue revenue : monthlyOrderList) {
            int revenueMonth = revenue.getOrderMonth();
            monthlyOrderData[revenueMonth - 1] = (int) revenue.getTotalRevenue();
        }
        request.setAttribute("monthlyOrderData", monthlyOrderData);
        request.setAttribute("selectedYear", year);
        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);
        request.setAttribute("dailyOrder", dailyOrder);
          request.setAttribute("totalOrders", totalOrders);

        request.getRequestDispatcher("/Views/Admin/StatisticsOrder.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        RevenueDAO revenueDAO = new RevenueDAO();
//        
//        String startDateStr = request.getParameter("startDate");
//        String endDateStr = request.getParameter("endDate");
//
//        
//        LocalDate startDate = LocalDate.parse(startDateStr);
//        LocalDate endDate = LocalDate.parse(endDateStr);
//
//        int startYear = startDate.getYear();
//        int startMonth = startDate.getMonthValue();
//        int startDay = startDate.getDayOfMonth();
//
//        int endYear = endDate.getYear();
//        int endMonth = endDate.getMonthValue();
//        int endDay = endDate.getDayOfMonth();
//
//       
//        List<Revenue> ordersWithCounts = revenueDAO.getOrdersWithCounts(startDay, endDay, startMonth, endMonth, startYear, endYear);
//        Gson gson = new Gson();
//        String jsonOrders = gson.toJson(ordersWithCounts);
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//
//     
//        PrintWriter out = response.getWriter();
//        out.print(jsonOrders);
//        out.flush();
//          request.setAttribute("ordersWithCounts", ordersWithCounts);
//         request.getRequestDispatcher("/Views/Admin/StatisticsOrder.jsp").forward(request, response);
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
