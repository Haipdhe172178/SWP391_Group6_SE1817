/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.DiscountDAO;
import Models.Account;
import Models.UsedCoupon;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Hai Pham
 */
public class ApplyDiscountController extends HttpServlet {

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
            out.println("<title>Servlet ApplyDiscountController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ApplyDiscountController at " + request.getContextPath() + "</h1>");
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
        String discountCode = request.getParameter("discount_code");
        String totalStr = request.getParameter("total_price");
        double totalAmount = Double.parseDouble(totalStr) - 20000;
        Account acc = (Account) request.getSession().getAttribute("account");

        DiscountDAO dDAO = new DiscountDAO();

        UsedCoupon coupon = dDAO.getDiscountByCodeName(discountCode);
        boolean checkUsed = dDAO.checkHistoryCoupon(acc.getAccountId(), coupon.getCodeId());

        double finalTotalPrice = (totalAmount - (totalAmount * coupon.getDiscount() / 100)) + 20000;
        int shippingFee = 20000;

        // Set content type and character encoding for the response
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Format currency
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        String content = "";
        if (coupon != null && checkUsed == false) {
            content = "<table cellspacing=\"0\" class=\"table text-capitalize\">\n"
                    + "    <tbody>\n"
                    + "        <tr class=\"subtotal pt-2 pb-2 border-top border-bottom\">\n"
                    + "            <th>Tạm tính</th>\n"
                    + "            <td data-title=\"Subtotal\">\n"
                    + "                <span class=\"price-amount amount text-primary ps-5 fw-light\">\n"
                    + "                    <bdi>\n"
                    + "                        <span class=\"price-currency-symbol\">\n"
                    + "                            " + currencyFormat.format(totalAmount) + "\n"
                    + "                        </span>\n"
                    + "                    </bdi>\n"
                    + "                </span>\n"
                    + "            </td>\n"
                    + "        </tr>\n"
                    + "        <tr class=\"order-total pt-2 pb-2 border-bottom\">\n"
                    + "            <th>Phí giao hàng</th>\n"
                    + "            <td data-title=\"Ship\">\n"
                    + "                <span class=\"price-amount amount text-primary ps-5 fw-light\">\n"
                    + "                    <bdi>\n"
                    + "                        <span class=\"price-currency-symbol\">\n"
                    + "                            " + currencyFormat.format(shippingFee) + "\n"
                    + "                        </span>\n"
                    + "                </span>\n"
                    + "            </td>\n"
                    + "        </tr>\n"
                    + "        <tr class=\"order-total pt-2 pb-2 border-bottom\" id=\"totalPriceID\">\n"
                    + "            <th style=\"font-weight: bold\">Giảm giá</th>\n"
                    + "            <td data-title=\"Discount\">\n"
                    + "                <span class=\"price-amount amount text-primary ps-5 fw-light\">\n"
                    + "                    <bdi>\n"
                    + "                        <span class=\"price-currency-symbol\" style=\"font-weight: bold; font-size: 22px\">-\n"
                    + "                            " + currencyFormat.format(totalAmount * coupon.getDiscount() / 100) + "\n"
                    + "                        </span>\n"
                    + "                    </bdi>\n"
                    + "                </span>\n"
                    + "            </td>\n"
                    + "        </tr>\n"
                    + "        <tr class=\"order-total pt-2 pb-2 border-bottom\" id=\"totalPriceID\">\n"
                    + "            <th style=\"font-weight: bold\">Tổng cộng</th>\n"
                    + "            <td data-title=\"Total\">\n"
                    + "                <span class=\"price-amount amount text-primary ps-5 fw-light\">\n"
                    + "                    <bdi>\n"
                    + "                        <span class=\"price-currency-symbol\" style=\"font-weight: bold; font-size: 22px\">\n"
                    + "                            " + currencyFormat.format(finalTotalPrice) + "\n"
                    + "                            <input type=\"hidden\" id=\"valueTotalPrice\" name=\"totalPrice\" value=\"" + finalTotalPrice + "\">\n"
                    + "                        </span>\n"
                    + "                    </bdi>\n"
                    + "                </span>\n"
                    + "            </td>\n"
                    + "        </tr>\n"
                    + "    </tbody>\n"
                    + "</table>";
        }

        out.println(content);
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
