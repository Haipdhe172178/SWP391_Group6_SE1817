/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers;

import DAL.CartDAO;
import DAL.ProductDao;
import Models.Account;
import Models.Cart;
import Models.Item;
import Models.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 *
 * @author admin
 */
public class UpdateCartController extends HttpServlet {
   
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
            out.println("<title>Servlet UpdateCartController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateCartController at " + request.getContextPath () + "</h1>");
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
        processRequest(request, response);
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
    HttpSession session = request.getSession();
    String url = request.getParameter("url");

    if (url == null || url.isEmpty()) {
        url = request.getHeader("Referer");
        if (url == null) {
            url = "home";
        }
    }
    request.getParameterMap().forEach((key, value) -> System.out.println(key + " = " + String.join(", ", value)));
   try {
       
  
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));


        if (quantity <= 0) {
            session.setAttribute("message", "Số lượng sản phẩm phải lớn hơn 0");
            response.sendRedirect(request.getContextPath() + "/single?productID=" + productId);
            return;
        }

        String cartData = getCartDataFromCookie(request);
        ProductDao productDao = new ProductDao();
        List<Product> productList = productDao.getAllProducts();
        Cart cart = new Cart(cartData, productList);
        

        Product product = cart.getProductById(productId, productList);
        if (product != null) {
            int currentCartQuantity = cart.getQuantityByProductId(productId);
            if (url.contains("productID")) {
                if (currentCartQuantity + quantity > product.getQuantity()) {
                    session.setAttribute("message", "Số lượng thêm vào vượt quá số lượng sản phẩm");
                } else {
                    Item newItem = new Item(product, quantity, product.getPrice());
                    cart.addItem(newItem);
                    session.setAttribute("message", "Thêm vào giỏ hàng thành công");
                }
            } else {
                Item t = new Item(product, quantity, product.getPrice());
                cart.updateItem(t);
            }
        } else {
            session.setAttribute("message", "Không tìm thấy sản phẩm!");
        }

        updateCartCookie(response, cart);
        Account account = (Account) session.getAttribute("account");
        if (account != null) {
            CartDAO cartDao = new CartDAO();
            cart.setAccountId(account.getAccountId());
            cartDao.addCartItem(cart);
        }
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"success\": true, \"price\": " + product.getPrice() + "}");
        
    } catch (Exception e) {
        e.printStackTrace();
        session.setAttribute("message", "Đã có lỗi xảy ra, vui lòng thử lại sau.");
        response.sendRedirect(request.getContextPath() + "/errorPage");
    }
 
}
 private void updateCartCookie(HttpServletResponse response, Cart cart) throws IOException {
        String sanitizedValue = URLEncoder.encode(cart.toString(), StandardCharsets.UTF_8.toString());
        Cookie cartCookie = new Cookie("cart", sanitizedValue);
        cartCookie.setMaxAge(60 * 60 * 24 * 7); // Hết hạn sau 1 tuần
        response.addCookie(cartCookie);
    } private String getCartDataFromCookie(HttpServletRequest request) {
        String cartData = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cart")) {
                    cartData = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
                    break;
                }
            }
        }
        return cartData;
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
