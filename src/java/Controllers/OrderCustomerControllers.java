package Controllers;

import DAL.AccountDAO;
import DAL.ProductDao;
import DAL.OrderDao;
import Models.Account;
import Models.OrderCustomer;
import Models.Status;
import Models.OrderDetailCustomer;
import Models.Product; // Import Product model
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Servlet to handle customer orders.
 */
public class OrderCustomerControllers extends HttpServlet {

     private final OrderDao orderDao = new OrderDao();
    private final ProductDao productDao = new ProductDao();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderCustomerController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderCustomerController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String statusStr = request.getParameter("status");
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        if (a == null) {
            response.sendRedirect("login");
            return;
        }
        int accountId = a.getAccountId();
        Map<String, Integer> statusMap = new HashMap<>();
        statusMap.put("all", -1);
        statusMap.put("pending", 1);
        statusMap.put("confirmed", 2);
        statusMap.put("shipping", 3);
        statusMap.put("completed", 4);
        statusMap.put("canceled", 5);

        int status = -1;
        if (statusStr != null && statusMap.containsKey(statusStr)) {
            status = statusMap.get(statusStr);
        }

        List<OrderCustomer> orders;
        if (status == -1) {
            orders = orderDao.getOrderCustomersByAccountId(accountId);
        } else {
            orders = orderDao.getOrderCustomersByAccountIdAndStatus(accountId, status);
        }
        
        for (OrderCustomer order : orders) {
            List<OrderDetailCustomer> orderDetails = order.getOrderDetails();
            for (OrderDetailCustomer detail : orderDetails) {
                Product product = productDao.getProductById(detail.getProductId());
                detail.setProduct(product);
            }
        }
       
        int totalQuantity = orderDao.getTotalQuantityByOrderCId(accountId);
        int all = orderDao.getAllOrderCountForCustomers(accountId);
        int pendingCount = orderDao.getOrderCountByStatusForCustomers(accountId, 1);
        int confirmedCount = orderDao.getOrderCountByStatusForCustomers(accountId, 2);
        int shippingCount = orderDao.getOrderCountByStatusForCustomers(accountId, 3);
        int completedCount = orderDao.getOrderCountByStatusForCustomers(accountId, 4);
        int canceledCount = orderDao.getOrderCountByStatusForCustomers(accountId, 5);
        boolean noOrders = all == 0 && pendingCount == 0 && confirmedCount == 0 && shippingCount == 0 && completedCount == 0 && canceledCount == 0;
        
        request.setAttribute("totalQuantity", totalQuantity);
        request.setAttribute("all", all);
        request.setAttribute("pendingCount", pendingCount);
        request.setAttribute("confirmedCount", confirmedCount);
        request.setAttribute("shippingCount", shippingCount);
        request.setAttribute("completedCount", completedCount);
        request.setAttribute("canceledCount", canceledCount);
        request.setAttribute("accountId", accountId);
        request.setAttribute("orders", orders);
        request.setAttribute("noOrders", noOrders);
        request.getRequestDispatcher("Views/OrderCustomer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "cancel":
                    int orderId = Integer.parseInt(request.getParameter("orderId"));
                    String cancelReason = request.getParameter("cancelReason");
                    if (orderId > 0 && cancelReason != null && !cancelReason.isEmpty()) {
                        boolean success = orderDao.cancelOrder(orderId);
                        if (success) {
                            response.sendRedirect(request.getContextPath() + "/ordercustomer");
                        } else {
                        }
                    }
                    break;
                case "updateAddress":
                    break;
                case "requestChange":
                    break;
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
