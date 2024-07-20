/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Controllers.VNPay.Config;
import DAL.DiscountDAO;
import DAL.OrderDao;
import DAL.ProductDao;
import Models.Account;
import Models.Item;
import Models.Product;
import Models.UsedCoupon;
import SendEmail.SendEmail;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 *
 * @author Hai Pham
 */
public class ProcessCheckoutController extends HttpServlet {

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
        processRequest(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account acc = (Account) request.getSession().getAttribute("account");
        OrderDao od = new OrderDao();
        ProductDao pd = new ProductDao();
        String[] items = request.getParameterValues("items");

        List<Item> listItem = getItemsList(pd, items);
        Float totalPrice = Float.parseFloat(request.getParameter("totalPrice"));
        String paymentMethod = request.getParameter("paymentMethod");

        try {
            if (acc == null) {
                handleGuestOrder(request, response, od, listItem, totalPrice, paymentMethod);
            } else {
                handleCustomerOrder(request, response, acc, od, listItem, totalPrice, paymentMethod);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Item> getItemsList(ProductDao pd, String[] items) {
        List<Item> listItem = new ArrayList<>();
        for (String item : items) {
            String[] i = item.split(",");
            Product p = pd.getProductById(Integer.parseInt(i[0]));
            int quantity = Integer.parseInt(i[1]);
            listItem.add(new Item(p, quantity, p.getPrice()));
        }
        return listItem;
    }

    private void handleGuestOrder(HttpServletRequest request, HttpServletResponse response, OrderDao od, List<Item> listItem, Float totalPrice, String paymentMethod) throws IOException, ServletException {
        String fullName = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phone");
        String address = request.getParameter("fulladdress");

        int orderGID = od.addOrderGuestWithDetail(fullName, email, phoneNumber, address, totalPrice, 1, 0, listItem);

        //ORDER FAIL
        if (orderGID == -1) {
            request.getRequestDispatcher("Views/error.jsp").forward(request, response);
            return;
        }

        if (paymentMethod.equalsIgnoreCase("VNPay")) {
            //VNPay
            redirectToVNPay(request, response, totalPrice, orderGID);
        } else {
            //COD
            for (Item item : listItem) {
                od.updateProductQuantity(item.getProduct().getProductId(), item.getQuantity(), "-");
            }
            redirectToThankYouPage(request, response);
            SendEmail.sendEmail(email, "Xac nhan don hang #" + orderGID, SendEmail.sendEmailConfirm(orderGID));
        }

    }

    private void handleCustomerOrder(HttpServletRequest request, HttpServletResponse response, Account acc, OrderDao od, List<Item> listItem, Float totalPrice, String paymentMethod) throws IOException, ServletException {
        int addressID = Integer.parseInt(request.getParameter("address"));
        UsedCoupon coupon = (UsedCoupon) request.getSession().getAttribute("coupon");

        int orderCID = od.addOrderCustomer(acc.getAccountId(), addressID, totalPrice, 1, 0, listItem);

        //ORDER FAIL 
        if (orderCID == -1) {
            request.setAttribute("message", "orderFail");
            request.getRequestDispatcher("Views/error.jsp").forward(request, response);
            return;
        }
        
        //Process coupon
        if (coupon != null) {
            DiscountDAO discountDao = new DiscountDAO();
            boolean isSuccess = discountDao.insertHistoryCoupon(acc.getAccountId(), coupon);
            if (isSuccess) {
                discountDao.UpdateQuantityDiscount(coupon.getCodeId(), coupon.getQuantity() - 1);
                request.getSession().removeAttribute("coupon");
            }
        }

        //VNPay
        if (paymentMethod.equalsIgnoreCase("VNPay")) {
            redirectToVNPay(request, response, totalPrice, orderCID);
        } else {
            //COD
            for (Item item : listItem) {
                od.updateProductQuantity(item.getProduct().getProductId(), item.getQuantity(), "-");
            }
            redirectToThankYouPage(request, response);
        }
    }

    private void redirectToVNPay(HttpServletRequest request, HttpServletResponse response, Float totalPrice, int orderID) throws IOException {
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
        long amount = (long) (totalPrice * 100);
        String bankCode = request.getParameter("bankCode");

        String vnp_TxnRef = String.valueOf(orderID);
        String vnp_IpAddr = Config.getIpAddress(request);

        String vnp_TmnCode = Config.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");

        vnp_Params.put("vnp_BankCode", bankCode);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);

        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        for (String fieldName : fieldNames) {
            String fieldValue = vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                // Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                // Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (!fieldName.equals(fieldNames.get(fieldNames.size() - 1))) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
        response.sendRedirect(paymentUrl);
    }

    private void redirectToThankYouPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("message", "success");
        request.getRequestDispatcher("Views/thanks.jsp").forward(request, response);
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
