/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
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
            query = "SELECT * FROM Product WHERE CategoryID = ? ";
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

    //thêm sản phẩm
    public void addProduct(Product product) {
        String query = "INSERT INTO Product (name, price, quantity, description, categoryId, authorId, imgProduct, ageId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, product.getName());
            ps.setFloat(2, product.getPrice());
            ps.setInt(3, product.getQuantity());
            ps.setString(4, product.getDescription());
            ps.setInt(5, product.getCategoryId());
            ps.setInt(6, product.getAuthorId());
            ps.setString(7, product.getImgProduct());
            ps.setInt(8, product.getAgeId());
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Method hien tai can dung
    public List<Product> pagingProductsByCatagoryId(int index, List<Integer> categoryIds) {
        List<Product> products = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Product WHERE categoryId IN (");
        for (int i = 0; i < categoryIds.size(); i++) {
            queryBuilder.append("?");
            if (i < categoryIds.size() - 1) {
                queryBuilder.append(", ");
            }
        }
        queryBuilder.append(") ORDER BY productId OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY");

        String query = queryBuilder.toString();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            int parameterIndex = 1;
            for (Integer categoryId : categoryIds) {
                ps.setInt(parameterIndex++, categoryId);
            }
            ps.setInt(parameterIndex++, (index - 1) * 8);
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

    public int countProductsByCategoryIds(List<Integer> categoryIds) {
        int totalCount = 0;
        StringBuilder queryBuilder = new StringBuilder("SELECT COUNT(*) FROM Product WHERE categoryId IN (");
        for (int i = 0; i < categoryIds.size(); i++) {
            queryBuilder.append("?");
            if (i < categoryIds.size() - 1) {
                queryBuilder.append(", ");
            }
        }
        queryBuilder.append(")");
        String query = queryBuilder.toString();
        try {
            PreparedStatement ps = connection.prepareStatement(query);

            int parameterIndex = 1;
            for (Integer categoryId : categoryIds) {
                ps.setInt(parameterIndex++, categoryId);
            }

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return totalCount;
    }

    public List<Product> pagingProductsByCateIdAgeId(int index, List<Integer> categoryIds, int ageId) {
        List<Product> products = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Product WHERE categoryId IN (");
        for (int i = 0; i < categoryIds.size(); i++) {
            queryBuilder.append("?");
            if (i < categoryIds.size() - 1) {
                queryBuilder.append(", ");
            }
        }
        queryBuilder.append(") AND AgeID = ? ORDER BY productId OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY");

        String query = queryBuilder.toString();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            int parameterIndex = 1;
            for (Integer categoryId : categoryIds) {
                ps.setInt(parameterIndex++, categoryId);
            }
            ps.setInt(parameterIndex++, ageId);
            ps.setInt(parameterIndex++, (index - 1) * 8);
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

    public int countProductsByCategoryIdsAgeID(List<Integer> categoryIds, int ageId) {
        int totalCount = 0;
        StringBuilder queryBuilder = new StringBuilder("SELECT COUNT(*) FROM Product WHERE categoryId IN (");
        for (int i = 0; i < categoryIds.size(); i++) {
            queryBuilder.append("?");
            if (i < categoryIds.size() - 1) {
                queryBuilder.append(", ");
            }
        }
        queryBuilder.append(") AND AgeID = ?");
        String query = queryBuilder.toString();
        try {
            PreparedStatement ps = connection.prepareStatement(query);

            int parameterIndex = 1;
            for (Integer categoryId : categoryIds) {
                ps.setInt(parameterIndex++, categoryId);
            }
            ps.setInt(parameterIndex++, ageId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return totalCount;
    }

    public List<Product> paginProductByCateIdAgeIdPrice(int index, List<Integer> categoryIds, int ageId, float minPrice, float maxPrice, boolean above, boolean below) {
        List<Product> products = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Product WHERE categoryId IN (");
        for (int i = 0; i < categoryIds.size(); i++) {
            queryBuilder.append("?");
            if (i < categoryIds.size() - 1) {
                queryBuilder.append(", ");
            }
        }
        queryBuilder.append(") AND AgeID = ? ");

        if (above && below) {
            queryBuilder.append("AND price BETWEEN ? AND ? ");
        } else if (above) {
            queryBuilder.append("AND price >= ? ");
        } else if (below) {
            queryBuilder.append("AND price <= ? ");
        }

        queryBuilder.append("ORDER BY productId OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY");

        String query = queryBuilder.toString();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            int parameterIndex = 1;
            for (Integer categoryId : categoryIds) {
                ps.setInt(parameterIndex++, categoryId);
            }
            ps.setInt(parameterIndex++, ageId);

            if (above && below) {
                ps.setFloat(parameterIndex++, minPrice);
                ps.setFloat(parameterIndex++, maxPrice);
            } else if (above) {
                ps.setFloat(parameterIndex++, minPrice);
            } else if (below) {
                ps.setFloat(parameterIndex++, maxPrice);
            }

            ps.setInt(parameterIndex++, (index - 1) * 8);
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

    public int countProductsByCategoryIdsAgeIDPrice(List<Integer> categoryIds, int ageId, float minPrice, float maxPrice) {
        int totalCount = 0;
        StringBuilder queryBuilder = new StringBuilder("SELECT COUNT(*) FROM Product WHERE categoryId IN (");
        for (int i = 0; i < categoryIds.size(); i++) {
            queryBuilder.append("?");
            if (i < categoryIds.size() - 1) {
                queryBuilder.append(", ");
            }
        }
        queryBuilder.append(") AND AgeID = ?");

        if (minPrice >= 0 && maxPrice > 0) {
            queryBuilder.append(" AND price BETWEEN ? AND ?");
        } else if (minPrice >= 0) {
            queryBuilder.append(" AND price >= ?");
        } else if (maxPrice > 0) {
            queryBuilder.append(" AND price <= ?");
        }

        String query = queryBuilder.toString();
        try {
            PreparedStatement ps = connection.prepareStatement(query);

            int parameterIndex = 1;
            for (Integer categoryId : categoryIds) {
                ps.setInt(parameterIndex++, categoryId);
            }
            ps.setInt(parameterIndex++, ageId);

            if (minPrice >= 0 && maxPrice > 0) {
                ps.setFloat(parameterIndex++, minPrice);
                ps.setFloat(parameterIndex, maxPrice);
            } else if (minPrice >= 0) {
                ps.setFloat(parameterIndex, minPrice);
            } else if (maxPrice > 0) {
                ps.setFloat(parameterIndex, maxPrice);
            }

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return totalCount;
    }

    //đếm số lượng sản phẩm theo độ tuổi
    public int countProductsByAgeId(int ageId) {
        int total = 0;
        String query = "SELECT COUNT(*) FROM Product WHERE ageId = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, ageId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return total;
    }

    //phân trang sản phẩm theo độ tuổi
    public List<Product> pagingProductsByAgeId(int index, int ageId) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Product WHERE ageId = ? ORDER BY productId OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, ageId);
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

    //đếm số lượng sản phẩm theo giá
    public int countPriceRange(float minPrice, float maxPrice) {
        int total = 0;
        String query;
        if (maxPrice > 0) {
            query = "SELECT COUNT(*) FROM Product WHERE price BETWEEN ? AND ?";
        } else {
            query = "SELECT COUNT(*) FROM Product WHERE price >= ?";
        }
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setFloat(1, minPrice);

            if (maxPrice > 0) {
                ps.setFloat(2, maxPrice);
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return total;
    }

    // Phân trang sản phẩm trong khoảng giá xác định
    public List<Product> pagingProductsByPriceRange(int index, float minPrice, float maxPrice) {
        List<Product> products = new ArrayList<>();
        String query;
        if (maxPrice > 0) {
            query = "SELECT * FROM Product WHERE price BETWEEN ? AND ? ORDER BY productId OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
        } else {
            query = "SELECT * FROM Product WHERE price >= ? ORDER BY productId OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
        }
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setFloat(1, minPrice);
            if (maxPrice > 0) {
                ps.setFloat(2, maxPrice);
                ps.setInt(3, (index - 1) * 8);
            } else {
                ps.setInt(2, (index - 1) * 8);
            }
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

    public List<Product> pagingProductsByCateIdPrice(int index, List<Integer> categoryIds, float minPrice, float maxPrice) {
        List<Product> products = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Product WHERE categoryId IN (");
        for (int i = 0; i < categoryIds.size(); i++) {
            queryBuilder.append("?");
            if (i < categoryIds.size() - 1) {
                queryBuilder.append(", ");
            }
        }
        queryBuilder.append(")");
        if (minPrice > 0 || maxPrice > 0) {
            if (minPrice > 0 && maxPrice > 0) {
                queryBuilder.append(" AND price BETWEEN ? AND ?");
            } else if (minPrice > 0) {
                queryBuilder.append(" AND price >= ?");
            } else {
                queryBuilder.append(" AND price <= ?");
            }
        }
        queryBuilder.append(" ORDER BY productId OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY");
        String query = queryBuilder.toString();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            int parameterIndex = 1;
            for (Integer categoryId : categoryIds) {
                ps.setInt(parameterIndex++, categoryId);
            }
            if (minPrice > 0 || maxPrice > 0) {
                if (minPrice > 0 && maxPrice > 0) {
                    ps.setFloat(parameterIndex++, minPrice);
                    ps.setFloat(parameterIndex++, maxPrice);
                } else if (minPrice > 0) {
                    ps.setFloat(parameterIndex++, minPrice);
                } else {
                    ps.setFloat(parameterIndex++, maxPrice);
                }
            }
            ps.setInt(parameterIndex++, (index - 1) * 8);

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

    public int countProductsByCareIdPrice(List<Integer> categoryIds, float minPrice, float maxPrice) {
        int totalCount = 0;
        StringBuilder queryBuilder = new StringBuilder("SELECT COUNT(*) FROM Product WHERE categoryId IN (");
        for (int i = 0; i < categoryIds.size(); i++) {
            queryBuilder.append("?");
            if (i < categoryIds.size() - 1) {
                queryBuilder.append(", ");
            }
        }
        queryBuilder.append(")");
        if (minPrice > 0 || maxPrice > 0) {
            if (minPrice > 0 && maxPrice > 0) {
                queryBuilder.append(" AND price BETWEEN ? AND ?");
            } else if (minPrice > 0) {
                queryBuilder.append(" AND price >= ?");
            } else {
                queryBuilder.append(" AND price <= ?");
            }
        }

        String query = queryBuilder.toString();
        try {
            PreparedStatement ps = connection.prepareStatement(query);

            int parameterIndex = 1;
            for (Integer categoryId : categoryIds) {
                ps.setInt(parameterIndex++, categoryId);
            }
            if (minPrice > 0 || maxPrice > 0) {
                if (minPrice > 0 && maxPrice > 0) {
                    ps.setFloat(parameterIndex++, minPrice);
                    ps.setFloat(parameterIndex++, maxPrice);
                } else if (minPrice > 0) {
                    ps.setFloat(parameterIndex++, minPrice);
                } else {
                    ps.setFloat(parameterIndex++, maxPrice);
                }
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return totalCount;
    }

    public List<Product> pagingProductAgeIdPrice(int index, int ageId, float minPrice, float maxPrice) {
        List<Product> products = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Product WHERE AgeID = ?");

        if (minPrice > 0 || maxPrice > 0) {
            if (minPrice > 0 && maxPrice > 0) {
                queryBuilder.append(" AND price BETWEEN ? AND ?");
            } else if (minPrice > 0) {
                queryBuilder.append(" AND price >= ?");
            } else {
                queryBuilder.append(" AND price <= ?");
            }
        }

        queryBuilder.append(" ORDER BY productId OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY");

        String query = queryBuilder.toString();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            int parameterIndex = 1;

            ps.setInt(parameterIndex++, ageId);

            if (minPrice > 0 || maxPrice > 0) {
                if (minPrice > 0 && maxPrice > 0) {
                    ps.setFloat(parameterIndex++, minPrice);
                    ps.setFloat(parameterIndex++, maxPrice);
                } else if (minPrice > 0) {
                    ps.setFloat(parameterIndex++, minPrice);
                } else {
                    ps.setFloat(parameterIndex++, maxPrice);
                }
            }

            ps.setInt(parameterIndex++, (index - 1) * 8);

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

    public int countProductAgeIdPrice(int ageId, float minPrice, float maxPrice) {
        int totalCount = 0;
        StringBuilder queryBuilder = new StringBuilder("SELECT COUNT(*) FROM Product WHERE AgeID = ?");

        if (minPrice > 0 || maxPrice > 0) {
            if (minPrice > 0 && maxPrice > 0) {
                queryBuilder.append(" AND price BETWEEN ? AND ?");
            } else if (minPrice > 0) {
                queryBuilder.append(" AND price >= ?");
            } else {
                queryBuilder.append(" AND price <= ?");
            }
        }

        String query = queryBuilder.toString();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            int parameterIndex = 1;

            ps.setInt(parameterIndex++, ageId);

            if (minPrice > 0 || maxPrice > 0) {
                if (minPrice > 0 && maxPrice > 0) {
                    ps.setFloat(parameterIndex++, minPrice);
                    ps.setFloat(parameterIndex++, maxPrice);
                } else if (minPrice > 0) {
                    ps.setFloat(parameterIndex++, minPrice);
                } else {
                    ps.setFloat(parameterIndex++, maxPrice);
                }
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return totalCount;
    }
public int getQuantitySoldByProductId(int id) {
        String query = " Select * From QualityofProductsell WHERE ProducID=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(2);
            }
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

public void deleteStudent(int studentID) {
        PreparedStatement pstmt = null;
        try {

            // Xóa sinh viên từ bảng Students
            String deleteStudentSql1 = "DELETE FROM [dbo].[Feedback]\n" +
"      WHERE ProductID = ?";
            pstmt = connection.prepareStatement(deleteStudentSql1);
            pstmt.setInt(1, studentID);
            pstmt.executeUpdate();

            String deleteStudentSql2 = "DELETE FROM [dbo].OrderDetailGuest\n" +
"      WHERE ProductID = ?";
            pstmt = connection.prepareStatement(deleteStudentSql2);
            pstmt.setInt(1, studentID);
            pstmt.executeUpdate();
            String deleteStudentSql3 = "DELETE FROM [dbo].OrderDetailCustomer\n" +
"      WHERE ProductID = ?";
            pstmt = connection.prepareStatement(deleteStudentSql3);
            pstmt.setInt(1, studentID);
            pstmt.executeUpdate();
            String deleteStudentSql4 = "DELETE FROM [dbo].QualityofProductsell\n" +
"      WHERE ProducID = ?";
            pstmt = connection.prepareStatement(deleteStudentSql4);
            pstmt.setInt(1, studentID);
            pstmt.executeUpdate();

            String deleteStudentSql5 = "DELETE FROM [dbo].[Product]\n" +
"      WHERE ProductID = ?";
            pstmt = connection.prepareStatement(deleteStudentSql5);
            pstmt.setInt(1, studentID);
            pstmt.executeUpdate();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

public Product get1Productbyid(String id) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        PreparedStatement stm;//thuc hien cau lenh sql
        ResultSet rs;// lua tru du lieu duoc lay ve tu select

        try {
            String stSQL = "SELECT [ProductID]\n"
                    + "      ,[Name]\n"
                    + "      ,[Price]\n"
                    + "      ,[Quantity]\n"
                    + "      ,[Description]\n"
                    + "      ,[CategoryID]\n"
                    + "      ,[AuthorID]\n"
                    + "      ,[ImgProduct]\n"
                    + "      ,[AgeID]\n"
                    + "  FROM [dbo].[Product]\n"
                    + "  where ProductID = ?";

            stm = connection.prepareStatement(stSQL);
            stm.setString(1, id);
            rs = stm.executeQuery();

            while (rs.next()) {
//                String id = String.valueOf(rs.getInt(1));
                int productid = rs.getInt(1);
                String name = rs.getString(2);
                float price = rs.getFloat(3);
                int quantity = rs.getInt(4);
                String description = rs.getString(5);
                int CategoryID = rs.getInt(6);
                int authorID = rs.getInt(7);
                String imgProduct = rs.getString(8);
                int age = rs.getInt(9);
                return new Product(productid, name, price, quantity, description, CategoryID, authorID, imgProduct, age);
                //   student.setId(rs.getInt("sid"));

            }
        } catch (Exception e) {
            System.out.println("getUpdate" + e.getMessage());
        }
        return null;

    }

public void updateProduct(Product u) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            String stSQL = "UPDATE [dbo].[Product]\n"
                    + "   SET [Name] = ?\n"
                    + "      ,[Price] = ?\n"
                    + "      ,[Quantity] = ?\n"
                    + "      ,[Description] = ?\n"
                    + "      ,[CategoryID] = ?\n"
                    + "      ,[AuthorID] = ?\n"
                    + "      ,[ImgProduct] = ?\n"
                    + "      ,[AgeID] = ?\n"
                    + " WHERE ProductID = ?";
            PreparedStatement stm = connection.prepareStatement(stSQL);
            stm = connection.prepareStatement(stSQL);
            stm.setString(1, u.getName());
            stm.setFloat(2, u.getPrice());
            stm.setInt(3, u.getQuantity());
            stm.setString(4, u.getDescription());
            stm.setInt(5, u.getCategoryId());
            stm.setInt(6, u.getAuthorId());
            stm.setString(7, u.getImgProduct());
            stm.setInt(8, u.getAgeId());
            stm.setInt(9, u.getProductId());
            int d = stm.executeUpdate();
        } catch (Exception e) {
            System.out.println("updateProduct" + e.getMessage());
        }
    }


}
