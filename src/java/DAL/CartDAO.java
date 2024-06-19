package DAL;

import Models.Cart;
import Models.Item;
import Models.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CartDAO extends DBContext {

    public void addCartItem(Cart cart) {
        String checkSql = "SELECT Quantity FROM Cart WHERE AccountID = ? AND ProductID = ?";

        try (PreparedStatement checkPs = connection.prepareStatement(checkSql)) {
            for (Item item : cart.getItems()) {
                checkPs.setInt(1, cart.getAccountId());
                checkPs.setInt(2, item.getProduct().getProductId());
                ResultSet rs = checkPs.executeQuery();
                if (rs.next()) {
                    int newQuantity = rs.getInt("Quantity") + item.getQuantity();
                    item.setQuantity(newQuantity); 
                    updateCartItem(cart, item); 
                } else {
                    insertCartItem(cart, item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCartItem(Cart cart, Item item) {
        String updateSql = "UPDATE Cart SET Quantity = ? WHERE AccountID = ? AND ProductID = ?";

        try (PreparedStatement updatePs = connection.prepareStatement(updateSql)) {
            updatePs.setInt(1, item.getQuantity());
            updatePs.setInt(2, cart.getAccountId());
            updatePs.setInt(3, item.getProduct().getProductId());
            int updated = updatePs.executeUpdate();
            if (updated > 0) {
                System.out.println("Updated: AccountID=" + cart.getAccountId() + ", ProductID=" + item.getProduct().getProductId() + ", Quantity=" + item.getQuantity());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertCartItem(Cart cart, Item item) {
        String insertSql = "INSERT INTO Cart(AccountID, ProductID, Quantity) VALUES (?, ?, ?)";

        try (PreparedStatement insertPs = connection.prepareStatement(insertSql)) {
            insertPs.setInt(1, cart.getAccountId());
            insertPs.setInt(2, item.getProduct().getProductId());
            insertPs.setInt(3, item.getQuantity());
            insertPs.executeUpdate();
            System.out.println("Inserted: AccountID=" + cart.getAccountId() + ", ProductID=" + item.getProduct().getProductId() + ", Quantity=" + item.getQuantity());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Cart getCartByUserId(int userId) {
    Cart cart = new Cart();
    cart.setAccountId(userId);
    String query = "SELECT c.ProductID, c.Quantity, p.Name, p.Price, p.Description, p.CategoryID, p.AuthorID, p.ImgProduct, p.Status "
            + "FROM Cart c "
            + "JOIN Product p ON c.ProductID = p.ProductID "
            + "WHERE c.AccountID = ?";

    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setInt(1, userId);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("ProductID"));
                product.setName(rs.getString("Name"));
                product.setPrice(rs.getFloat("Price"));
                product.setDescription(rs.getString("Description"));
                product.setCategory(null);
                product.setAuthor(null);
                product.setImgProduct(rs.getString("ImgProduct"));
                product.setStatus(rs.getInt("Status"));

                Item item = new Item();
                item.setProduct(product);
                item.setQuantity(rs.getInt("Quantity"));
                item.setPrice(product.getPrice());

                cart.addItem(item); // Add item to cart without merging
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return cart;
}

    public void removeCartItem(Cart cart, int productId) {
    String query = "DELETE FROM CartItems WHERE accountId = ? AND productId = ?";
    try (PreparedStatement ps = connection.prepareStatement(query)){
        ps.setInt(1, cart.getAccountId());
        ps.setInt(2, productId);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    public static void main(String[] args) {
        CartDAO cartDAO = new CartDAO();
        int userId = 10; 
        Cart cart = cartDAO.getCartByUserId(userId);
        System.out.println("Cart items for User ID: " + userId);
        for (Item item : cart.getItems()) {
            System.out.println("Product ID: " + item.getProduct().getProductId() + ", Quantity: " + item.getQuantity());
        }
    }
}
