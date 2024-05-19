/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huyca
 */
public class ProductDao extends DBContext{
    //Ham nay de list du lieu trong database ra
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = " Select * From Product";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getFloat("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setDescription(rs.getString("description"));
                product.setCategoryId(rs.getInt("categoryId"));
                product.setAuthorID(rs.getInt("authorId"));
                product.setImgProduct(rs.getString("imgProduct"));               
                products.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return products;
    }
    
     public static void main(String[] args) {
         ProductDao productDao = new ProductDao();
        List<Product> allProducts = productDao.getAllProducts();
         for (Product products : allProducts) {
             System.out.println("Product ID: " + products.getProductId());
            System.out.println("Product Name: " + products.getName());
         }
        
    }
   
}
