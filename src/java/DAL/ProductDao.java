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
public class ProductDao extends DBContext {

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
                product.setAgeId(rs.getInt("ageId"));
                products.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return products;
    }

    //Get a product by productID
    public Product getProductById(int id) {
        String query = " Select * From Product WHERE ProductID=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getFloat("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setDescription(rs.getString("description"));
                product.setCategoryId(rs.getInt("categoryId"));
                product.setAuthorID(rs.getInt("authorId"));
                product.setImgProduct(rs.getString("imgProduct"));
                product.setAgeId(rs.getInt("ageId"));
                return product;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //lấy prodcut bằng categoryId
    public List<Product> getProductsByCategoryId(int categoryId, String option) {
        List<Product> products = new ArrayList<>();
        String query;
        if (option.equalsIgnoreCase("fourRandom")) {
            query = "SELECT TOP 4 * FROM Product WHERE CategoryID = ? Order by NEWID()";
        } else {
            query = "SELECT T* FROM Product WHERE CategoryID = ? " ;
        }
        
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, categoryId);
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
                product.setAgeId(rs.getInt("ageId"));
                products.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public static void main(String[] args) {
        //test function
        ProductDao pd = new ProductDao();
        List<Product> p = pd.getProductsByCategoryId(1, "fourRandom");
        for (Product product : p) {
            System.out.println(product.getProductId());
        }
    }
    
    //Đếm số lượng product trong data
    public int getTotalProduct() {
        String query = "Select count (*) from Product";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    //Lấy prduct theo trang 
    public List<Product> pagingProducts(int index) {
        List<Product> list = new ArrayList<>();
        String query = "Select * from Product\n"
                + "  Order by ProductID\n"
                + "  OFFSET ?  Rows fetch next 8 row only;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, (index - 1) * 8);
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
                product.setAgeId(rs.getInt("ageId"));
                list.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    //Phân trang khi search bằng category và tên
    public List<Product> pagingProductsByCategory(int index, int categoryId) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Product WHERE CategoryID = ? ORDER BY productId OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, categoryId);
            ps.setInt(2, (index - 1) * 8);
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
                product.setAgeId(rs.getInt("ageId"));
                products.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return products;
    }
    // phân trang khi tìm kiếm bằng key word
    public List<Product> pagingProductsByKeyword(int index, String keyword) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Product WHERE name LIKE ? ORDER BY productId OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + keyword + "%");
            ps.setInt(2, (index - 1) * 8);
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
                product.setAgeId(rs.getInt("ageId"));
                products.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return products;
    }

    //Tìm kiếm theo tên
    public List<Product> searchProductsByName(String nameKeyword) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Product WHERE Name LIKE ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + nameKeyword + "%");
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
                product.setAgeId(rs.getInt("ageId"));
                products.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return products;
    }

    //Lấy tổng sản phẩm theo keyword
    public int getTotalProductsByKeyword(String keyword) {
        int total = 0;
        String query = "SELECT COUNT(*) FROM Product WHERE name LIKE ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return total;
    }

    //Lấy tổng sản phầm của product theo categoryId
    public int getTotalProductsByCategory(int categoryId) {
        int total = 0;
        String query = "SELECT COUNT(*) FROM Product WHERE CategoryID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return total;
    }



    // phân trang khi sort bằng tên
    public List<Product> pagingProductsSortedByName(int index, boolean ascending) {
        List<Product> products = new ArrayList<>();
        String sortOrder = ascending ? "ASC" : "DESC";
        String query = "SELECT * FROM Product ORDER BY Name " + sortOrder + " OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, (index - 1) * 8);
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
                product.setAgeId(rs.getInt("ageId"));
                products.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return products;
    }
    // phân trang khi sort theo giá
    public List<Product> pagingProductsSortedByPrice(int index, boolean ascending) {
        List<Product> products = new ArrayList<>();
        String sortOrder = ascending ? "ASC" : "DESC";
        String query = "SELECT * FROM Product ORDER BY Price " + sortOrder + " OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, (index - 1) * 8);
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
                product.setAgeId(rs.getInt("ageId"));
                products.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return products;
    }
    // lấy tổng số sản phẩm khi sort
    public int getTotalProductBySort(String sortBy) {
        String query = "SELECT COUNT(*) FROM Product";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    // phân trang khi sort bằng tên (String)
    public List<Product> pagingProductsSortedByName(int index, boolean ascending, String keyword) {
        List<Product> products = new ArrayList<>();
        String sortOrder = ascending ? "ASC" : "DESC";
        String query = "SELECT * FROM Product WHERE name LIKE ? ORDER BY name " + sortOrder + " OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + keyword + "%");
            ps.setInt(2, (index - 1) * 8);
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
                product.setAgeId(rs.getInt("ageId"));
                products.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return products;
    }
    // phân trang khi sort bằng giá (String)
    public List<Product> pagingProductsSortedByPrice(int index, boolean ascending, String keyword) {
        List<Product> products = new ArrayList<>();
        String sortOrder = ascending ? "ASC" : "DESC";
        String query = "SELECT * FROM Product WHERE name LIKE ? ORDER BY price " + sortOrder + " OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + keyword + "%");
            ps.setInt(2, (index - 1) * 8);
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
                product.setAgeId(rs.getInt("ageId"));
                products.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return products;
    }
    // phân trang khi sort bằng tên (int)
    public List<Product> pagingProductsSortedByName(int index, boolean ascending, int categoryId) {
        List<Product> products = new ArrayList<>();
        String sortOrder = ascending ? "ASC" : "DESC";
        String query = "SELECT * FROM Product WHERE categoryId = ? ORDER BY name " + sortOrder + " OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, categoryId);
            ps.setInt(2, (index - 1) * 8);
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
                product.setAgeId(rs.getInt("ageId"));
                products.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return products;
    }
    // phân trang khi sort bằng giá (int)
    public List<Product> pagingProductsSortedByPrice(int index, boolean ascending, int categoryId) {
        List<Product> products = new ArrayList<>();
        String sortOrder = ascending ? "ASC" : "DESC";
        String query = "SELECT * FROM Product WHERE categoryId = ? ORDER BY price " + sortOrder + " OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, categoryId);
            ps.setInt(2, (index - 1) * 8);
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
                product.setAgeId(rs.getInt("ageId"));
                products.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return products;
    }

}
