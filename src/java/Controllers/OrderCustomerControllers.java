package Controllers;

import DAL.AccountDAO;
import DAL.OrderDao;
import Models.Account;
import Models.OrderCustomer;
import Models.Status; // Import enum Status
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
 * @author admin
 */
public class OrderCustomerControllers extends HttpServlet {

    private final OrderDao orderDao = new OrderDao();

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

        // Ánh xạ chuỗi trạng thái thành giá trị số
        Map<String, Integer> statusMap = new HashMap<>();
        statusMap.put("all", -1);
        statusMap.put("pending", 1);
        statusMap.put("confirmed", 2);
        statusMap.put("shipping", 3);
        statusMap.put("completed", 4);
        statusMap.put("canceled", 5);

        int status = -1; // Mặc định là lấy tất cả trạng thái
        if (statusStr != null && statusMap.containsKey(statusStr)) {
            status = statusMap.get(statusStr);
        }

        List<OrderCustomer> orders;

        if (status == -1) {
            orders = orderDao.getOrderCustomersByAccountId(accountId);
        } else {
            orders = orderDao.getOrderCustomersByAccountIdAndStatus(accountId, status);
        }

        int all = orderDao.getAllOrderCountForCustomers(accountId);
        int pendingCount = orderDao.getOrderCountByStatusForCustomers(accountId, 1);
        int confirmedCount = orderDao.getOrderCountByStatusForCustomers(accountId, 2);
        int shippingCount = orderDao.getOrderCountByStatusForCustomers(accountId, 3);
        int completedCount = orderDao.getOrderCountByStatusForCustomers(accountId, 4);
        int canceledCount = orderDao.getOrderCountByStatusForCustomers(accountId, 5);
        boolean noOrders = all == 0 && pendingCount == 0 && confirmedCount == 0 && shippingCount == 0 && completedCount == 0 && canceledCount == 0;

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
                    // Đặt trạng thái hủy đơn hàng và lưu lý do hủy vào cơ sở dữ liệu
                    boolean success = orderDao.cancelOrder(orderId);
                    if (success) {
                        // Thông báo thành công và làm mới lại danh sách đơn hàng
                        response.sendRedirect(request.getContextPath() + "/ordercustomer");
                    } else {
                        // Xử lý lỗi khi hủy đơn hàng không thành công
                        // Có thể chuyển hướng hoặc thông báo lỗi
                        // Ví dụ: request.setAttribute("cancelError", "Không thể hủy đơn hàng vào lúc này.");
                        // và forward lại trang hiện tại để hiển thị thông báo lỗi
                    }
                }
                break;
            case "updateAddress":
                // Xử lý cập nhật địa chỉ và số điện thoại nhận hàng
                break;
            case "requestChange":
                // Xử lý yêu cầu thay đổi đơn hàng (màu sắc, kích thước, v.v.)
                break;
            // Thêm các case khác tương ứng với các hành động khác
        }
    }
}

    

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}