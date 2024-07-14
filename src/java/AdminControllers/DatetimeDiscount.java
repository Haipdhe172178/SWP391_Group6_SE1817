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
import java.text.SimpleDateFormat;
import java.util.Date;

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

        // Định dạng chuỗi ngày giờ từ request
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        // Định dạng chuỗi ngày giờ mới mong muốn
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        try {
            // Parse chuỗi ngày giờ từ request thành đối tượng Date
            Date date = inputFormat.parse(date_rawup);
            // Format lại thành chuỗi ngày giờ mới
            String formattedDate = outputFormat.format(date);

            // Sử dụng chuỗi formattedDate để thực hiện các thao tác khác
            DiscountDAO dal = new DiscountDAO();
            boolean isUpdated = dal.UpdateTime(date_rawid, formattedDate);

            if (isUpdated) {
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Update Status</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }");
                out.println(".popup-box { width: 300px; padding: 20px; background-color: #d4edda; border: 1px solid #c3e6cb; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%); box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); text-align: center; }");
                out.println("</style>");
                out.println("<script type='text/javascript'>");
                out.println("document.addEventListener('DOMContentLoaded', function() {");
                out.println("  var popup = document.createElement('div');");
                out.println("  popup.className = 'popup-box';");
                out.println("  popup.innerHTML = '<h2>Update successful.</h2><p>Rows updated successfully.</p>';");
                out.println("  document.body.appendChild(popup);");
                out.println("  setTimeout(function() { popup.style.display = 'none'; window.location.href = 'discount'; }, 2000);"); // Chờ 2 giây trước khi chuyển hướng
                out.println("});");
                out.println("</script>");
                out.println("</head>");
                out.println("<body>");
                out.println("</body>");
                out.println("</html>");
            } else {
                // In thông báo lỗi nếu không cập nhật thành công
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
        } catch (Exception e) {

            // Xử lý lỗi nếu không thể parse chuỗi ngày giờ từ request
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

        String date_rawid = request.getParameter("dateold");
        String date_rawup = request.getParameter("date");

        // Định dạng chuỗi ngày giờ từ request
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        // Định dạng chuỗi ngày giờ mới mong muốn
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        try {
            // Parse chuỗi ngày giờ từ request thành đối tượng Date
            Date date = inputFormat.parse(date_rawup);
            // Format lại thành chuỗi ngày giờ mới
            String formattedDate = outputFormat.format(date);

            // Sử dụng chuỗi formattedDate để thực hiện các thao tác khác
            DiscountDAO dal = new DiscountDAO();
            boolean isUpdated = dal.UpdateTime(date_rawid, formattedDate);

            if (isUpdated) {
                response.sendRedirect("discount");
            } else {
                // In thông báo lỗi nếu không cập nhật thành công
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
        } catch (Exception e) {

            // Xử lý lỗi nếu không thể parse chuỗi ngày giờ từ request
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
