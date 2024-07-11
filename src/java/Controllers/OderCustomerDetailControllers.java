package Controllers;

import DAL.AccountDAO;
import DAL.OrderDao;
import DAL.ProductDao;
import DAL.ShipAddressDAO;
import Models.Account;
import Models.OrderCustomer;
import Models.OrderDetailCustomer;
import Models.Product;
import Models.ShipAddress;
import Models.ShippingAddress;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author admin
 */
public class OderCustomerDetailControllers extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet OderCustomerDetailControllers</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OderCustomerDetailControllers at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        ProductDao productDAO = new ProductDao();
        OrderDao orderDAO = new OrderDao();
        ShipAddressDAO shipAddressDAO = new ShipAddressDAO();
        AccountDAO accountDAO = new AccountDAO();

        List<OrderDetailCustomer> orderDetails = orderDAO.getOrderDetailCustomers(orderId);
        for (OrderDetailCustomer orderDetail : orderDetails) {
            int productId = orderDetail.getProductId();
            Product product = productDAO.getProductById(productId);
            orderDetail.setProduct(product); 
        }

    
        ShipAddress shipAddress = shipAddressDAO.selectShipAddress(orderId);
        OrderCustomer order = orderDAO.getOrderCustomerById(orderId);
        int accountId = order.getAccount().getAccountId();
        Account account = accountDAO.getAccountByid(accountId);
        
        request.setAttribute("orderDetails", orderDetails);
        request.setAttribute("order", order);
        request.setAttribute("account", account);
        request.setAttribute("shippingAddress", shipAddress);

        request.getRequestDispatcher("Views/OrderCustomerDetail.jsp").forward(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
