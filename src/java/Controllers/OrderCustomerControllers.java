package Controllers;

import DAL.AccountDAO;
import DAL.ProductDao;
import DAL.OrderDao;
import Models.Account;
import Models.Cart;
import Models.Item;
import Models.OrderCustomer;
import Models.Status;
import Models.OrderDetailCustomer;
import Models.Product;
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
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        if (a == null) {
            response.sendRedirect("login");
            return;
        }
        int accountId = a.getAccountId();

        String action = request.getParameter("action");
        String orderIdStr = request.getParameter("orderId");
        String status = request.getParameter("status");

        if (action != null && orderIdStr != null) {
            boolean isComplete = false;
            String ms = null;

            try {
                int orderId = Integer.parseInt(orderIdStr);
                OrderDao orderDao = new OrderDao();

                switch (action) {
                    case "cancel":
                        isComplete = orderDao.cancelOrder(orderId);
                        ms = isComplete ? "Đơn hàng đã được hủy!" : "Không thể hủy đơn hàng!";
                        break;
                    case "received":
                        isComplete = orderDao.updateStatusById(orderId, 4);
                        ms = isComplete ? "Đơn hàng đã được đánh dấu là đã nhận!" : "Không thể cập nhật đơn hàng!";
                        break;
                    case "buyAgain":
                        List<OrderDetailCustomer> orderDetails = orderDao.getOrderDetailCustomers(orderId);
                        Cart cart = (Cart) session.getAttribute("cart");
                        if (cart == null) {
                            cart = new Cart();
                            session.setAttribute("cart", cart);
                        }
                        for (OrderDetailCustomer detail : orderDetails) {
                            Product product = productDao.getProductById(detail.getProductId());
                            Item item = new Item(product, detail.getQuantity(), product.getPrice());
                            cart.addItem(item);
                        }
                        response.sendRedirect("cart");
                        return;

                    default:
                        ms = "Hành động không hợp lệ!";
                }

                request.getSession().setAttribute("notification", ms);
                response.sendRedirect("ordercustomer?accountId=" + accountId + "&status=" + status);

            } catch (NumberFormatException e) {
                request.setAttribute("message", "ID đơn hàng không hợp lệ!");
                response.sendRedirect("ordercustomer?accountId=" + accountId + "&status=" + status);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
