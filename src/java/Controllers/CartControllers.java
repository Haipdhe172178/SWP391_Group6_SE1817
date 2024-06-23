package Controllers;

import DAL.CartDAO;
import DAL.ProductDao;
import Models.Account;
import Models.Cart;
import Models.Item;
import Models.Product;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
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

public class CartControllers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        ProductDao productDao = new ProductDao();
        List<Product> productList = productDao.getAllProducts();

        Cart cart;
        if (account != null) {
            CartDAO cartDao = new CartDAO();
            cart = cartDao.getCartByUserId(account.getAccountId());

            // Merge cart from cookie if exists
            String cartData = getCartDataFromCookie(request);
            Cart cookieCart = new Cart(cartData, productList);
            mergeCarts(cart, cookieCart);

            // Update database and cookie
            cartDao.addCartItem(cart);
            updateCartCookie(response, cart);
        } else {
            String cartData = getCartDataFromCookie(request);
            cart = new Cart(cartData, productList);
        }

        request.setAttribute("cart", cart);
        request.setAttribute("cartSize", cart.getItems().size());
        request.getRequestDispatcher("Views/Cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        String cartData = getCartDataFromCookie(request);
        ProductDao productDao = new ProductDao();
        List<Product> productList = productDao.getAllProducts();
        Cart cart = new Cart(cartData, productList);

        Product product = cart.getProductById(productId, productList);
        if (product != null) {
            Item newItem = new Item(product, quantity, product.getPrice());
            cart.addItem(newItem);
          
      
        
        } else {
            System.out.println("Không tìm thấy sản phẩm!");
        }

        updateCartCookie(response, cart);

        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        if (a != null) {
            CartDAO cartDao = new CartDAO();
            cart.setAccountId(a.getAccountId());
            cartDao.addCartItem(cart);
        }

        session.setAttribute("cart", cart);
        response.sendRedirect(request.getContextPath() + "/single?productID=" + productId);
    }

    @Override
    public String getServletInfo() {
        return "Đây là Servlet quản lý giỏ hàng";
    }

    private String getCartDataFromCookie(HttpServletRequest request) {
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

    private void updateCartCookie(HttpServletResponse response, Cart cart) throws IOException {
        String sanitizedValue = URLEncoder.encode(cart.toString(), StandardCharsets.UTF_8.toString());
        Cookie cartCookie = new Cookie("cart", sanitizedValue);
        cartCookie.setMaxAge(60 * 60 * 24 * 7); // Hết hạn sau 1 tuần
        response.addCookie(cartCookie);
    }

    private void mergeCarts(Cart dbCart, Cart cookieCart) {
        for (Item item : cookieCart.getItems()) {
            dbCart.addItem(item);
        }
    }
}
