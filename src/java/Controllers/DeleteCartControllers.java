package Controllers;

import DAL.CartDAO;
import DAL.ProductDao;
import Models.Account;
import Models.Cart;
import Models.Product;
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

public class DeleteCartControllers extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int productId = Integer.parseInt(request.getParameter("productId"));

        String cartData = getCartDataFromCookie(request);
        ProductDao productDao = new ProductDao();
        List<Product> productList = productDao.getAllProducts();
        Cart cart = new Cart(cartData, productList);

        cart.removeItem(productId);

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account != null) {
            CartDAO cartDao = new CartDAO();
            cart.setAccountId(account.getAccountId());
            // Xóa mặt hàng khỏi cơ sở dữ liệu
            cartDao.removeCartItem(account.getAccountId(), productId);
        }

        updateCartCookie(response, cart);

        // Cập nhật giỏ hàng trong session
//        session.setAttribute("cart", cart);

        // Chuyển hướng đến trang giỏ hàng
        response.sendRedirect(request.getContextPath() + "/cart");
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

    @Override
    public String getServletInfo() {
        return "Đây là Servlet xóa sản phẩm khỏi giỏ hàng";
    }
}
