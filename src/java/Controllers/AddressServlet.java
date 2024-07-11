package Controllers;

import DAL.CategoryDao;
import DAL.ShipAddressDAO;
import Models.Account;
import Models.Category;
import Models.ShipAddress;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AddressServlet extends HttpServlet {

   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    String action = request.getParameter("action");
    ShipAddressDAO dao = new ShipAddressDAO();

    try {
        if ("save".equals(action)) {
            int accID = Integer.parseInt(request.getParameter("accID"));
            String address = request.getParameter("address");
            String phoneNumber = request.getParameter("phoneNumber");
            boolean isDefault = request.getParameter("isDefault") != null;

            ShipAddress shipAddress = new ShipAddress(accID, address, phoneNumber, isDefault);
            dao.insertShipAddress(shipAddress);
        } else if ("update".equals(action)) {
            int addressID = Integer.parseInt(request.getParameter("addressID"));
            int accID = Integer.parseInt(request.getParameter("accID"));
            String address = request.getParameter("address");
            String phoneNumber = request.getParameter("phoneNumber");
            boolean isDefault = request.getParameter("isDefault") != null;

            ShipAddress shipAddress = new ShipAddress(addressID, accID, address, phoneNumber, isDefault);
            dao.updateShipAddress(shipAddress);
        } else if ("delete".equals(action)) {
            int addressID = Integer.parseInt(request.getParameter("addressID"));
            dao.deleteShipAddress(addressID);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    response.sendRedirect("address");
}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        ShipAddressDAO dao = new ShipAddressDAO();
        CategoryDao categoryDao = new CategoryDao();
        List<Category> cate = categoryDao.getallCategorys();
        
        Account account = (Account)request.getSession().getAttribute("account");
        // Fetch the list of addresses from the database
        List<ShipAddress> addresses = dao.getUserAddress(account.getAccountId());
        request.setAttribute("category", cate);
        // Set the list of addresses as a request attribute
        request.setAttribute("addresses", addresses);
        // Forward the request to the JSP page
        request.getRequestDispatcher("Views/Address.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
