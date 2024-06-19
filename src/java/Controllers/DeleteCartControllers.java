package Controllers;

import DAL.ProductDao;
import Models.Cart;
import Models.Item;
import Models.Product;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteCartControllers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDao productDao = new ProductDao();
        List<Product> productList = productDao.getAllProducts();
        String cartData = getCartDataFromCookie(request);
        Map<Integer, Integer> cartItems = parseCartData(cartData);

        String idRaw = request.getParameter("id");
        String numRaw = request.getParameter("num");
        int productId = Integer.parseInt(idRaw);
        int num = Integer.parseInt(numRaw);

        if (cartItems.containsKey(productId)) {
            int newQuantity = cartItems.get(productId) + num;
            if (newQuantity <= 0) {
                cartItems.remove(productId);
            } else {
                cartItems.put(productId, newQuantity);
            }
        }

        String newCartData = encodeCartData(cartItems);
        updateCartCookie(response, newCartData);

        request.setAttribute("cart", new Cart(newCartData, productList));
        request.getRequestDispatcher("Views/Cart.jsp").forward(request, response);
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

    private Map<Integer, Integer> parseCartData(String cartData) {
        Map<Integer, Integer> cartItems = new HashMap<>();
        if (cartData != null && !cartData.isEmpty()) {
            String[] items = cartData.split(",");
            for (String item : items) {
                String[] parts = item.split(":");
                int productId = Integer.parseInt(parts[0]);
                int quantity = Integer.parseInt(parts[1]);
                cartItems.put(productId, quantity);
            }
        }
        return cartItems;
    }

    private String encodeCartData(Map<Integer, Integer> cartItems) {
        StringBuilder cartData = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry : cartItems.entrySet()) {
            if (cartData.length() > 0) {
                cartData.append(",");
            }
            cartData.append(entry.getKey()).append(":").append(entry.getValue());
        }
        return URLEncoder.encode(cartData.toString(), StandardCharsets.UTF_8);
    }

    private void updateCartCookie(HttpServletResponse response, String cartData) throws IOException {
        Cookie cartCookie = new Cookie("cart", cartData);
        cartCookie.setMaxAge(60 * 60 * 24 * 7); // 1 week
        response.addCookie(cartCookie);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
