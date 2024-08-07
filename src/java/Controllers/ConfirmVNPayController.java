/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Controllers.VNPay.Config;
import DAL.OrderDao;
import Models.Account;
import Models.OrderCustomer;
import Models.OrderDetailCustomer;
import Models.OrderDetailGuest;
import Models.OrderGuest;
import SendEmail.SendEmail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hai Pham
 */
public class ConfirmVNPayController extends HttpServlet {

    private static final String THANKS_PAGE = "Views/thanks.jsp";
    private static final String ERROR_PAGE = "Views/error.jsp";

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
            out.println("<title>Servlet ConfirmVNPayController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConfirmVNPayController at " + request.getContextPath() + "</h1>");
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

        // Begin process return from VNPAY
        Map<String, String> fields = new HashMap<>();
        for (Enumeration<String> params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = URLEncoder.encode(params.nextElement(), StandardCharsets.US_ASCII.toString());
            String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        fields.remove("vnp_SecureHashType");
        fields.remove("vnp_SecureHash");

        String signValue = Config.hashAllFields(fields);
        int orderId = Integer.parseInt(request.getParameter("vnp_TxnRef"));
        Account acc = (Account) request.getSession().getAttribute("account");
        String transactionStatus = request.getParameter("vnp_TransactionStatus");
        OrderDao od = new OrderDao();
        try {
            if (signValue.equals(vnp_SecureHash)) {
                if ("00".equals(transactionStatus)) {
                    handleSuccessfulPayment(orderDao, orderId, acc);
                    request.getRequestDispatcher(THANKS_PAGE).forward(request, response);
                    SendEmail.sendEmail(orderDao.getOrderGuestByID(orderId).getEmail(), "Xác nhận đơn hàng #" + orderId, SendEmail.sendEmailConfirm(orderId));
                } else {
                    handleFailedPayment(orderDao, orderId, acc);
                    if ("24".equals(transactionStatus)) {
                        response.sendRedirect("home");
                    } else {
                        request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
                    }
                }
            } else {
                request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }

    private void handleSuccessfulPayment(OrderDao orderDao, int orderId, Account acc) {
        if (acc != null) {
            orderDao.updatePaymentSuccess("customer", orderId);
            List<OrderDetailCustomer> listItem = orderDao.getOrderDetailCustomers(orderId);
            for (OrderDetailCustomer orderDetailCustomer : listItem) {
                orderDao.updateProductQuantity(orderDetailCustomer.getProductId(), orderDetailCustomer.getQuantity(), "-");
            }
        } else {
            List<OrderDetailGuest> listItem = orderDao.getOrderDetailGuests(orderId);
            for (OrderDetailGuest orderDetailGuest : listItem) {
                orderDao.updateProductQuantity(orderDetailGuest.getProductId(), orderDetailGuest.getQuantity(), "-");
            }
            orderDao.updatePaymentSuccess("guest", orderId);
        }
    }

    private void handleFailedPayment(OrderDao orderDao, int orderId, Account acc) {
        if (acc != null) {
            orderDao.deleteOrderCustomer(orderId);
        } else {
            orderDao.deleteOrderGuest(orderId);
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
