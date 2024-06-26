/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.StaffController;

import DAL.FeedbackDAO;
import Models.Feedback;
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
public class FeedbackListController extends HttpServlet {

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
            out.println("<title>Servlet FeedbackListController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FeedbackListController at " + request.getContextPath() + "</h1>");
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
        FeedbackDAO fDao = new FeedbackDAO();

        //Phân trang feedback and lấy theo Status
        //Page
        int page = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            page = Integer.parseInt(pageParam);
        }

        //get Feedback theo status
        List<Feedback> listFeedback;
        String status = request.getParameter("status");
        String search = request.getParameter("search");
        String filter = request.getParameter("filter");

        if (status == null || status.isEmpty()) {
            status = "pending";
        }
        if (search == null || search.isEmpty()) {
            search = "";
        }
        if (filter == null || filter.isEmpty()) {
            filter = "";
        }

        listFeedback = fDao.getAllFeedbackByStatus(page, status, search, filter);
        int quantity = fDao.getQuantityFeedbacks(status, search, filter);

        request.setAttribute("filter", filter);
        request.setAttribute("status", status);
        request.setAttribute("searchResult", search);
        request.setAttribute("listFeedback", listFeedback);

        int endPage = (quantity / 5) + (quantity % 5 == 0 ? 0 : 1);

        request.setAttribute("total", quantity);
        request.setAttribute("totalPending", fDao.getQuantityFeedbacks("pending", search, filter));
        request.setAttribute("totalAccept", fDao.getQuantityFeedbacks("accept", search, filter));
        request.setAttribute("totalReject", fDao.getQuantityFeedbacks("reject", search, filter));
        request.setAttribute("endPage", endPage);
        request.setAttribute("page", page);
        request.getRequestDispatcher("Views/Staff/FeedbackList.jsp").forward(request, response);
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

        FeedbackDAO fDao = new FeedbackDAO();
        String action = request.getParameter("action");
        String search = request.getParameter("search");
        String status = request.getParameter("status");
        String filter = request.getParameter("filter");
        String page = request.getParameter("page");

        String feedbackId_raw =request.getParameter("feedbackId");
        int feedbackId = 0;
        if (feedbackId_raw != null && !feedbackId_raw.isEmpty()) {
            feedbackId = Integer.parseInt(feedbackId_raw);
        }
        
        if (action != null && !action.isEmpty()) {
            boolean isComplete = false;
            String ms;
            switch (action) {
                case "displayElement":
                    isComplete = fDao.updateStatusFeedbackById(feedbackId, 1);
                    ms = "Hiển thị thành công";
                    break;
                case "hidden":
                    isComplete = fDao.updateStatusFeedbackById(feedbackId, -1);
                    ms = "Đã ẩn đánh giá";
                    break;
                case "delete":
                    isComplete = fDao.deleteFeedback(feedbackId);
                    ms = "Đã xóa đánh giá";
                    break;
                case "accept_all":
                    isComplete = fDao.updateStatusFeedbackByStatus(1);
                    ms = "Chấp nhận tất cả đánh giá thành công";
                    break;
                default:
                    ms = "Vui lòng thử lại!";
            }
            if (isComplete) {
                request.getSession().setAttribute("notification", action);
            }
            response.sendRedirect("feedbacklist?page=" + page + "&status=" + status + "&search=" + search + "&filter=" + filter);
        } else {
            response.sendRedirect("feedbacklist?page=" + page + "&status=" + status + "&search=" + search + "&filter=" + filter);
        }
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
