/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminControllers;

import DAL.OrderDao;
import DAL.RevenueDAO;
import Models.Revenue;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 *
 * @author huyca
 */
public class StatisticsControllers extends HttpServlet {

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
            out.println("<title>Servlet StatisticsControllers</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StatisticsControllers at " + request.getContextPath() + "</h1>");
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

        RevenueDAO revenueDAO = new RevenueDAO();
        List<Revenue> ordersWithCounts = revenueDAO.getRevenueByDateRange(startDate, endDate);
        int[] dailyRevenues = new int[31];

        for (Revenue revenue : ordersWithCounts) {
            int day = revenue.getOrderDay();
            int totalRevenue = (int) revenue.getTotalRevenue();
            dailyRevenues[day - 1] = totalRevenue;
        }

        // Lấy dữ liệu doanh thu theo tháng trong năm
        List<Revenue> monthlyRevenuesList = revenueDAO.getRevenueByYear(year);
        int[] monthlyRevenueData = new int[12];
        for (Revenue revenue : monthlyRevenuesList) {
            int revenueMonth = revenue.getOrderMonth();
            float totalRevenue = revenue.getTotalRevenue();
            monthlyRevenueData[revenueMonth - 1] = (int) totalRevenue;
        }
        float totalRevenue = revenueDAO.getTotalRevenue(startDate, endDate);
        request.setAttribute("dailyRevenues", dailyRevenues);
        request.setAttribute("monthlyRevenueData", monthlyRevenueData);
        request.setAttribute("selectedYear", year);
        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);
        request.setAttribute("totalRevenue", totalRevenue);

        request.getRequestDispatcher("/Views/Admin/Statistics.jsp").forward(request, response);
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
