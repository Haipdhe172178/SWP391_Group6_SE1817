/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.StaffController;

import DAL.OrderDao;
import DAL.ProductDao;
import Models.Cart;
import Models.Item;
import Models.OrderGuest;
import Models.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author USER
 */
public class NewOrderController extends HttpServlet {

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
            out.println("<title>Servlet NewOrderController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewOrderController at " + request.getContextPath() + "</h1>");
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
        List<Product> list = new ProductDao().getAllProducts();
        Cookie[] arr = request.getCookies();
        String txt = "";
        if (arr != null) {
            for (Cookie i : arr) {
                if (i.getName().equals("cartAdmin")) {
                    txt += i.getValue();
                }
            }
        }
        Cart cart = new Cart(txt, list, "/");
        List<Item> listItem = cart.getItems();
        int n = 0;
        if (listItem.size() != 0) {
            n = listItem.size();
        } else {
            n = 0;
        }

        request.setAttribute("listsp", listItem);
        request.getRequestDispatcher("Views/Staff/NewOrder.jsp").forward(request, response);
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
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String payment = request.getParameter("payment");
        String status = request.getParameter("status");
        String totalPrice = request.getParameter("total");

        OrderDao od = new OrderDao();
        int orderId = 0;
         List<Item> listItem = new ArrayList<>();
        if(Integer.parseInt(totalPrice)>20000){
              orderId = od.AddOrderGuest(name, email, phone, address,
                Float.parseFloat(totalPrice),
                Integer.parseInt(status), Integer.parseInt(payment));
          Cookie[] arr = request.getCookies();
        String txt = "";
        if (arr != null) {
            for (Cookie i : arr) {
                if (i.getName().equals("cartAdmin")) {
                    txt += i.getValue();//"1:1/2:1/3:1"
                }
            }
        }
        List<String> productIds = new ArrayList<>();//

        String[] data = txt.split("/");//"1:1"
        for (String s : data) {
            productIds.add(s.split(":")[0]);
        }

        //lay  quantity
        //add orderGuestDetail
       
        for (String productId : productIds) {
            //price 36 25
            String quantity = request.getParameter("quantity" + productId);//1 2
            String price = request.getParameter("price" + productId);//1 2
            //query tu db lay price
            //dat name cho price
            //2 doi tuong
            Product p1 = new Product();
            p1.setProductId(Integer.parseInt(productId));
            
            Item i = new Item(p1, Integer.parseInt(quantity), Float.parseFloat(price));
            listItem.add(i);
        
        
        
        
        
        }
      

        //product id
       
            
        }
        if(orderId == 0 ){
            String error = "Bạn chưa chọn 1 sản phẩm nào";
            request.setAttribute("error", error);
            request.getRequestDispatcher("Views/Staff/NewOrder.jsp").forward(request, response);
        }else{
              od.AddOrderGuestDetails(orderId, listItem);
       response.sendRedirect("staffdashboard");
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
