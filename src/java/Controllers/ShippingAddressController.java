/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.CategoryDao;
import DAL.ProductDao;
import DAL.ShipAddressDAO;
import Models.Account;
import Models.Category;
import Models.Item;
import Models.Product;
import Models.ShipAddress;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hai Pham
 */
public class ShippingAddressController extends HttpServlet {

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
            out.println("<title>Servlet ShippingAddressController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShippingAddressController at " + request.getContextPath() + "</h1>");
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
        String phone = request.getParameter("phone");
        String address = request.getParameter("fulladdress");
        int accID = Integer.parseInt(request.getParameter("accID"));
        String addressID = request.getParameter("addressID");
        String action = request.getParameter("action");
        boolean isDefault = false;
        ShipAddressDAO shipAddressDao = new ShipAddressDAO();

        //xử lí address
        if (action.equals("add")) {
            ShipAddress newAddress = new ShipAddress(accID, address, phone, isDefault);
            shipAddressDao.addShippingAddress(newAddress);
        } else if (action.equals("update")) {
            
            ShipAddress shipAddress = new ShipAddress(Integer.parseInt(addressID), accID, address, phone, isDefault);
            shipAddressDao.updateShippingAddress(shipAddress);
        }
        request.setAttribute("listAddress", shipAddressDao.getUserAddress(accID));

        //get lại items
        String[] selectedProduct = request.getParameterValues("items");
        List<Item> listToCheckout = new ArrayList<>();
        ProductDao pDao = new ProductDao();

        for (String selectedP : selectedProduct) {
            String[] product = selectedP.split(",");
            Product p = pDao.getProductById(Integer.parseInt(product[0]));
            Item i = new Item(p, Integer.parseInt(product[1]), p.getPrice());
            listToCheckout.add(i);
        }
        
        request.setAttribute("listItem", listToCheckout);
        request.setAttribute("totalAmount", getTotalAmount(listToCheckout));
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categorys = categoryDao.getallCategorys();
        request.setAttribute("category", categorys);
        request.getRequestDispatcher("Views/Checkout.jsp").forward(request, response);
    }

    private double getTotalAmount(List<Item> listItem) {
        double total = 0;
        for (Item item : listItem) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
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
