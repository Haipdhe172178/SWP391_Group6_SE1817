/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminControllers;

import DAL.DiscountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
public class DatetimeDiscount extends HttpServlet {

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
            out.println("<title>Servlet DatetimeDiscount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DatetimeDiscount at " + request.getContextPath() + "</h1>");
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
        String date_rawid = request.getParameter("dateold");
        String date_rawup = request.getParameter("date");
        DiscountDAO dal = new DiscountDAO();
        boolean isUpdated = dal.UpdateTime(date_rawid, date_rawup);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Update Status</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }");
        out.println(".notification-box { border: 1px solid #ccc; padding: 20px; background-color: #f8f8f8; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }");
        out.println(".success { border-color: #4CAF50; color: #4CAF50; }");
        out.println(".error { border-color: #f44336; color: #f44336; }");
        out.println("</style>");
        out.println("<script type='text/javascript'>");
        out.println("function redirect() {");
        out.println("  setTimeout(function() { window.location.href = 'discount'; }, 2000);");
        out.println("}");
        out.println("</script>");
        out.println("</head>");
        out.println("<body onload='redirect()'>");
        out.println("<div class='notification-box " + (isUpdated ? "success" : "error") + "'>");
        if (isUpdated) {
            out.println("<h2>Cập nhập ngày thành công!</h2>");
        } else {
            out.println("<h2>Cập nhật ngày thất bại.</h2>");
            out.println("<p>Bạn hãy nhập đúng cấu trúc đã có.</p>");
        }
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");

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
        String date_rawid = request.getParameter("dateole");
        String date_rawup = request.getParameter("date1");
        DiscountDAO dal = new DiscountDAO();
        boolean isUpdated = dal.UpdateTime(date_rawid, date_rawup);

        if (isUpdated) {
            response.sendRedirect("discount");
        } else {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Update Status</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }");
            out.println(".notification-box { border: 1px solid #ccc; padding: 20px; background-color: #f8f8f8; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }");
            out.println("</style>");
            out.println("<script type='text/javascript'>");
            out.println("function redirect() {");
            out.println("  setTimeout(function() { window.location.href = 'discount'; }, 2000);");
            out.println("}");
            out.println("</script>");
            out.println("</head>");
            out.println("<body onload='redirect()'>");
            out.println("<div class='notification-box'>");
            out.println("<h2>Update not successful.</h2>");
            out.println("<p>No rows affected or an error occurred.</p>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
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
