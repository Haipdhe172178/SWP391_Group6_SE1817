package Controllers;

import DAL.CartDAO;
import DAL.ProductDao;
import Models.Account;
import Models.Cart;
import Models.Item;
import Models.Product;
import jakarta.servlet.ServletException;
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

public class CartControllers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        ProductDao productDao = new ProductDao();
        List<Product> productList = productDao.getAllProducts();

        CartDAO cartDAO = new CartDAO();
        String cartData = getCartDataFromCookie(request);

        Cart cart = new Cart(cartData, productList);

        List<Item> cartItems = cart.getItems();
        if (session.getAttribute("cart") != null) {
            Cart cartSession = (Cart) session.getAttribute("cart");
            for (Item i : cartSession.getItems()) {
                int index = cartItems.indexOf(i);
                if (index != -1) {
                    Item item = cartItems.get(index);
                    Product product = productDao.get1Productbyid(item.getProduct().getProductId() + "");
                    int updateQuantity = Math.min(item.getQuantity() + i.getQuantity(), product.getQuantity());
                    item.setQuantity(updateQuantity);
                    cartItems.set(index, item);
                } else {
                    cartItems.add(i);
                }
            }
            session.removeAttribute("cart");
        }

        cart.setItems(cartItems);

        updateCartCookie(response, cart);

        if (account != null) {
    Cart dbCart = cartDAO.getCartByUserId(account.getAccountId());
    for (Item i : cart.getItems()) {
        Product product = productDao.get1Productbyid(i.getProduct().getProductId() + "");
        int availableStock = product.getQuantity();
        if (dbCart.getItems().contains(i)) {
            if (i.getQuantity() > availableStock) {
                i.setQuantity(availableStock);
            }
            cartDAO.updateCartItem(dbCart, i);
        } else {
            cartDAO.insertCartItem(dbCart, i);
        }
    }
    cart = cartDAO.getCartByUserId(account.getAccountId()); // Lấy lại giỏ hàng cập nhật từ cơ sở dữ liệu
}


        request.setAttribute("lastTwoItems", cartItems.size() >= 2 ? cartItems.subList(cartItems.size() - 2, cartItems.size()) : cartItems);
        request.setAttribute("cart", cart);
        Cookie sizeCookie = new Cookie("size", String.valueOf(cart.getItems().size()));
        sizeCookie.setMaxAge(60 * 60 * 24 * 7);
        response.addCookie(sizeCookie);
        request.getRequestDispatcher("Views/Cart.jsp").forward(request, response);
    }

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

    response.sendRedirect(url);
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
