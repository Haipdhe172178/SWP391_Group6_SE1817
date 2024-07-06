package Controllers;

import DAL.CartDAO;
import DAL.ProductDao;
import Models.Account;
import Models.Cart;
import Models.Item;
import Models.Product;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet(name = "UpdateCartControllers", urlPatterns = {"/updateCart"})
public class UpdateCartControllers extends HttpServlet {

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
        boolean success = false;

        if (product != null) {
            if (quantity > 0) {
                Item updatedItem = new Item(product, quantity, product.getPrice());
                cart.updateItem(updatedItem);
                success = true;
            } else {
                cart.removeItem(productId);
            }
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();

        double cartTotal = cart.getItems().stream().mapToDouble(item -> item.getQuantity() * item.getProduct().getPrice()).sum();
        int totalQuantity = cart.getItems().stream().mapToInt(Item::getQuantity).sum();

        response.getWriter().write(gson.toJson(new JsonResponse(success, cartTotal, totalQuantity)));
        updateCartCookie(response, cart); 
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
        // Convert Cart object to JSON string
        String cartJson = new Gson().toJson(cart);
        String sanitizedValue = URLEncoder.encode(cartJson, StandardCharsets.UTF_8.toString());
        Cookie cartCookie = new Cookie("cart", sanitizedValue);
        cartCookie.setMaxAge(60 * 60 * 24 * 7); // Expires in 1 week
        cartCookie.setPath("/"); // Make the cookie available to the entire application
        response.addCookie(cartCookie);

        // Debugging information
        System.out.println("Updated Cart Cookie: " + cartCookie.getValue());
    }

    private static class JsonResponse {
        boolean success;
        double cartTotal;
        int totalQuantity;

        JsonResponse(boolean success, double cartTotal, int totalQuantity) {
            this.success = success;
            this.cartTotal = cartTotal;
            this.totalQuantity = totalQuantity;
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet to update the cart quantities";
    }
}
