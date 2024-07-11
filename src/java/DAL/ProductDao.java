/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Account;
import Models.Author;
import Models.Category;
import Models.ObjectAge;
import Models.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        String query = "SELECT p.*, c.CategoryName, oa.Age, a.AuthorName, a.Description AS AuthorDescription\n"
                + "FROM Product p\n"
                + "INNER JOIN Category c ON c.CategoryID = p.CategoryID\n"
                + "INNER JOIN ObjectAge oa ON oa.AgeID = p.AgeID\n"
                + "INNER JOIN Author a ON a.AuthorID = p.AuthorID;";
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
                Category category = new Category();
                category.setCategoryId(rs.getInt("categoryId"));
                category.setCategoryName(rs.getString("categoryName"));
                product.setCategory(category);
                Author author = new Author();
                author.setAuthorID(rs.getInt("authorID"));
                author.setAuthorName(rs.getString("authorName"));
                author.setDescription(rs.getString("description"));
                product.setAuthor(author);
                ObjectAge oage = new ObjectAge();
                oage.setAgeId(rs.getInt("ageId"));
                oage.setAge(rs.getString("age"));
                product.setOage(oage);

                product.setImgProduct(rs.getString("imgProduct"));
                product.setStatus(rs.getInt("status"));

                products.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return products;
    }

    //Get a product by productID
    public Product getProductById(int id) {
        String query = " SELECT p.*, c.CategoryName,oa.Age, a.AuthorName,a.Description\n"
                + "from Product p\n"
                + "INNER JOIN Category c ON c.CategoryID = p.CategoryID\n"
                + "JOIN ObjectAge oa ON oa.AgeID = p.AgeID\n"
                + "JOIN Author a ON a.AuthorID = p.AuthorID\n"
                + "WHERE p.ProductID= ?";
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
                Category category = new Category();
                category.setCategoryId(rs.getInt("categoryId"));
                category.setCategoryName(rs.getString("categoryName"));
                product.setCategory(category);
                Author author = new Author();
                author.setAuthorID(rs.getInt("authorID"));
                author.setAuthorName(rs.getString("authorName"));
                author.setDescription(rs.getString("description"));
                product.setAuthor(author);
                ObjectAge oage = new ObjectAge();
                oage.setAgeId(rs.getInt("ageId"));
                oage.setAge(rs.getString("age"));
                product.setOage(oage);
                product.setImgProduct(rs.getString("imgProduct"));
                product.setStatus(rs.getInt("status"));
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
            query = "SELECT TOP 4 p.*, c.CategoryName, oa.Age, a.AuthorName, a.Description\n"
                    + "FROM Product p\n"
                    + "INNER JOIN Category c ON c.CategoryID = p.CategoryID\n"
                    + "JOIN ObjectAge oa ON oa.AgeID = p.AgeID\n"
                    + "JOIN Author a ON a.AuthorID = p.AuthorID\n"
                    + "WHERE p.CategoryID = ?\n"
                    + "order by NEWID()";
        } else {
            query = "SELECT  p.*, c.CategoryName,oa.Age, a.AuthorName,a.Description \n"
                    + "FROM Product p\n"
                    + "INNER JOIN Category c ON c.CategoryID = p.CategoryID\n"
                    + "JOIN ObjectAge oa ON oa.AgeID = p.AgeID\n"
                    + "JOIN Author a ON a.AuthorID = p.AuthorID\n"
                    + "WHERE p.CategoryID = ?";
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
                Category category = new Category();
                category.setCategoryId(rs.getInt("categoryId"));
                category.setCategoryName(rs.getString("categoryName"));
                product.setCategory(category);
                Author author = new Author();
                author.setAuthorID(rs.getInt("authorID"));
                author.setAuthorName(rs.getString("authorName"));
                author.setDescription(rs.getString("description"));
                product.setAuthor(author);
                ObjectAge oage = new ObjectAge();
                oage.setAgeId(rs.getInt("ageId"));
                oage.setAge(rs.getString("age"));
                product.setOage(oage);
                product.setImgProduct(rs.getString("imgProduct"));
                product.setStatus(rs.getInt("status"));
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
        String query = "SELECT  p.*, c.CategoryName,oa.Age, a.AuthorName,a.Description \n"
                + "FROM Product p\n"
                + "INNER JOIN Category c ON c.CategoryID = p.CategoryID\n"
                + "JOIN ObjectAge oa ON oa.AgeID = p.AgeID\n"
                + "JOIN Author a ON a.AuthorID = p.AuthorID\n"
                + "\n"
                + "WHERE name LIKE ? ORDER BY productId OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
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
                Category category = new Category();
                category.setCategoryId(rs.getInt("categoryId"));
                category.setCategoryName(rs.getString("categoryName"));
                product.setCategory(category);
                Author author = new Author();
                author.setAuthorID(rs.getInt("authorID"));
                author.setAuthorName(rs.getString("authorName"));
                author.setDescription(rs.getString("description"));
                product.setAuthor(author);
                ObjectAge oage = new ObjectAge();
                oage.setAgeId(rs.getInt("ageId"));
                oage.setAge(rs.getString("age"));
                product.setOage(oage);
                product.setImgProduct(rs.getString("imgProduct"));
                product.setStatus(rs.getInt("status"));

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
        String query = "SELECT  p.*, c.CategoryName,oa.Age, a.AuthorName,a.Description  \n"
                + "FROM Product p\n"
                + "INNER JOIN Category c ON c.CategoryID = p.CategoryID\n"
                + "JOIN ObjectAge oa ON oa.AgeID = p.AgeID\n"
                + "JOIN Author a ON a.AuthorID = p.AuthorID\n"
                + "WHERE Name LIKE ?";
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
                Category category = new Category();
                category.setCategoryId(rs.getInt("categoryId"));
                category.setCategoryName(rs.getString("categoryName"));
                product.setCategory(category);
                Author author = new Author();
                author.setAuthorID(rs.getInt("authorID"));
                author.setAuthorName(rs.getString("authorName"));
                author.setDescription(rs.getString("description"));
                product.setAuthor(author);
                ObjectAge oage = new ObjectAge();
                oage.setAgeId(rs.getInt("ageId"));
                oage.setAge(rs.getString("age"));
                product.setOage(oage);
                product.setImgProduct(rs.getString("imgProduct"));
                product.setStatus(rs.getInt("status"));

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
        String query = "SELECT p.*, c.CategoryName, oa.Age, a.AuthorName, a.Description AS AuthorDescription \n"
                + "FROM Product p \n"
                + "INNER JOIN Category c ON c.CategoryID = p.CategoryID \n"
                + "JOIN ObjectAge oa ON oa.AgeID = p.AgeID \n"
                + "JOIN Author a ON a.AuthorID = p.AuthorID \n"
                + "ORDER BY p.Name " + sortOrder + " \n"
                + "OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, (index - 1) * 8);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getFloat("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setDescription(rs.getString("description"));
                Category category = new Category();
                category.setCategoryId(rs.getInt("categoryId"));
                category.setCategoryName(rs.getString("categoryName"));
                product.setCategory(category);
                Author author = new Author();
                author.setAuthorID(rs.getInt("authorID"));
                author.setAuthorName(rs.getString("authorName"));
                author.setDescription(rs.getString("description"));
                product.setAuthor(author);
                ObjectAge oage = new ObjectAge();
                oage.setAgeId(rs.getInt("ageId"));
                oage.setAge(rs.getString("age"));
                product.setOage(oage);
                product.setImgProduct(rs.getString("imgProduct"));
                product.setStatus(rs.getInt("status"));
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
        String query = "SELECT p.*, c.CategoryName, oa.Age, a.AuthorName, a.Description AS AuthorDescription \n"
                + "FROM Product p \n"
                + "INNER JOIN Category c ON c.CategoryID = p.CategoryID \n"
                + "JOIN ObjectAge oa ON oa.AgeID = p.AgeID \n"
                + "JOIN Author a ON a.AuthorID = p.AuthorID \n"
                + "ORDER BY p.Price " + sortOrder + " \n"
                + "OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, (index - 1) * 8);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getFloat("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setDescription(rs.getString("description"));
                Category category = new Category();
                category.setCategoryId(rs.getInt("categoryId"));
                category.setCategoryName(rs.getString("categoryName"));
                product.setCategory(category);
                Author author = new Author();
                author.setAuthorID(rs.getInt("authorID"));
                author.setAuthorName(rs.getString("authorName"));
                author.setDescription(rs.getString("description"));
                product.setAuthor(author);
                ObjectAge oage = new ObjectAge();
                oage.setAgeId(rs.getInt("ageId"));
                oage.setAge(rs.getString("age"));
                product.setOage(oage);
                product.setImgProduct(rs.getString("imgProduct"));
                product.setStatus(rs.getInt("status"));
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
        String query = "INSERT INTO Product (name, price, quantity, description, categoryId, authorId, imgProduct, ageId, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, product.getName());
            ps.setFloat(2, product.getPrice());
            ps.setInt(3, product.getQuantity());
            ps.setString(4, product.getDescription());
            ps.setInt(5, product.getCategory().getCategoryId());
            ps.setInt(6, product.getAuthor().getAuthorID());
            ps.setString(7, product.getImgProduct());
            ps.setInt(8, product.getOage().getAgeId());
            ps.setInt(9, product.getStatus());
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Method hien tai can dung
    public List<Product> paginProductByFilter(int index, List<Integer> categoryIds, int ageId, float minPrice, float maxPrice) {
        List<Product> products = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder(
                "SELECT p.*, c.CategoryName, oa.Age, a.AuthorName, a.Description AS AuthorDescription "
                + "FROM Product p "
                + "INNER JOIN Category c ON c.CategoryID = p.CategoryID "
                + "JOIN ObjectAge oa ON oa.AgeID = p.AgeID "
                + "JOIN Author a ON a.AuthorID = p.AuthorID "
                + "WHERE 1=1"
        );

        if (!categoryIds.isEmpty()) {
            queryBuilder.append(" AND p.CategoryID IN (");
            for (int i = 0; i < categoryIds.size(); i++) {
                queryBuilder.append("?");
                if (i < categoryIds.size() - 1) {
                    queryBuilder.append(", ");
                }
            }
            queryBuilder.append(")");
        }
        if (ageId != 0) {
            queryBuilder.append(" AND p.AgeID = ?");
        }
        if (minPrice > 0) {
            queryBuilder.append(" AND p.Price >= ?");
        }
        if (maxPrice > 0) {
            queryBuilder.append(" AND p.Price <= ?");
        }
        queryBuilder.append(" ORDER BY p.ProductID OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY");

        String query = queryBuilder.toString();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            int parameterIndex = 1;
            for (Integer categoryId : categoryIds) {
                ps.setInt(parameterIndex++, categoryId);
            }
            if (ageId != 0) {
                ps.setInt(parameterIndex++, ageId);
            }
            if (minPrice > 0) {
                ps.setFloat(parameterIndex++, minPrice);
            }
            if (maxPrice > 0) {
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
                Category category = new Category();
                category.setCategoryId(rs.getInt("categoryId"));
                category.setCategoryName(rs.getString("categoryName"));
                product.setCategory(category);
                Author author = new Author();
                author.setAuthorID(rs.getInt("authorID"));
                author.setAuthorName(rs.getString("authorName"));
                author.setDescription(rs.getString("description"));
                product.setAuthor(author);
                ObjectAge oage = new ObjectAge();
                oage.setAgeId(rs.getInt("ageId"));
                oage.setAge(rs.getString("age"));
                product.setOage(oage);
                product.setImgProduct(rs.getString("imgProduct"));
                product.setStatus(rs.getInt("status"));
                products.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public int countProductsByFilter(List<Integer> categoryIds, int ageId, float minPrice, float maxPrice) {
        int totalCount = 0;
        StringBuilder queryBuilder = new StringBuilder("SELECT COUNT(*) FROM Product WHERE 1=1");

        if (!categoryIds.isEmpty()) {
            queryBuilder.append(" AND categoryId IN (");
            for (int i = 0; i < categoryIds.size(); i++) {
                queryBuilder.append("?");
                if (i < categoryIds.size() - 1) {
                    queryBuilder.append(", ");
                }
            }
            queryBuilder.append(")");
        }

        if (ageId != 0) {
            queryBuilder.append(" AND AgeID = ?");
        }

        if (minPrice > 0) {
            queryBuilder.append(" AND price >= ?");
        }
        if (maxPrice > 0) {
            queryBuilder.append(" AND price <= ?");
        }

        String query = queryBuilder.toString();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            int parameterIndex = 1;

            for (Integer categoryId : categoryIds) {
                ps.setInt(parameterIndex++, categoryId);
            }

            if (ageId != 0) {
                ps.setInt(parameterIndex++, ageId);
            }

            if (minPrice > 0) {
                ps.setFloat(parameterIndex++, minPrice);
            }
            if (maxPrice > 0) {
                ps.setFloat(parameterIndex++, maxPrice);
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
        String query = "SELECT p.*, c.CategoryName, oa.Age, a.AuthorName, a.Description AS AuthorDescription "
                + "FROM Product p "
                + "INNER JOIN Category c ON c.CategoryID = p.CategoryID "
                + "JOIN ObjectAge oa ON oa.AgeID = p.AgeID "
                + "JOIN Author a ON a.AuthorID = p.AuthorID "
                + "ORDER BY p.ProductID "
                + "OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY;";
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
                Category category = new Category();
                category.setCategoryId(rs.getInt("categoryId"));
                category.setCategoryName(rs.getString("categoryName"));
                product.setCategory(category);
                Author author = new Author();
                author.setAuthorID(rs.getInt("authorID"));
                author.setAuthorName(rs.getString("authorName"));
                author.setDescription(rs.getString("description"));
                product.setAuthor(author);
                ObjectAge oage = new ObjectAge();
                oage.setAgeId(rs.getInt("ageId"));
                oage.setAge(rs.getString("age"));
                product.setOage(oage);
                product.setImgProduct(rs.getString("imgProduct"));
                product.setStatus(rs.getInt("status"));
                list.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Product> pagingProducts(int index, List<String> ids) {
        List<Product> list = new ArrayList<>();

        // Manually create the idsString
        String idString = "";
        for (String id : ids) {
            if (idString.length() > 0) {
                idString += ",";
            }
            idString += "'" + id + "'";
        }
        String idsString = idString.substring(0, idString.length());
        System.out.println(idsString);
        String query = "SELECT p.*, c.CategoryName, oa.Age, a.AuthorName, a.Description AS AuthorDescription "
                + "FROM Product p "
                + "INNER JOIN Category c ON c.CategoryID = p.CategoryID "
                + "JOIN ObjectAge oa ON oa.AgeID = p.AgeID "
                + "JOIN Author a ON a.AuthorID = p.AuthorID "
                + "WHERE p.ProductID NOT IN (" + idsString + ") "
                + "ORDER BY p.ProductID "
                + "OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY;";
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
                Category category = new Category();
                category.setCategoryId(rs.getInt("categoryId"));
                category.setCategoryName(rs.getString("categoryName"));
                product.setCategory(category);
                Author author = new Author();
                author.setAuthorID(rs.getInt("authorID"));
                author.setAuthorName(rs.getString("authorName"));
                author.setDescription(rs.getString("description"));
                product.setAuthor(author);
                ObjectAge oage = new ObjectAge();
                oage.setAgeId(rs.getInt("ageId"));
                oage.setAge(rs.getString("age"));
                product.setOage(oage);
                product.setImgProduct(rs.getString("imgProduct"));
                product.setStatus(rs.getInt("status"));
                list.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
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
            String deleteStudentSql1 = "DELETE FROM [dbo].[Feedback]\n"
                    + "      WHERE ProductID = ?";
            pstmt = connection.prepareStatement(deleteStudentSql1);
            pstmt.setInt(1, studentID);
            pstmt.executeUpdate();

            String deleteStudentSql2 = "DELETE FROM [dbo].OrderDetailGuest\n"
                    + "      WHERE ProductID = ?";
            pstmt = connection.prepareStatement(deleteStudentSql2);
            pstmt.setInt(1, studentID);
            pstmt.executeUpdate();
            String deleteStudentSql3 = "DELETE FROM [dbo].OrderDetailCustomer\n"
                    + "      WHERE ProductID = ?";
            pstmt = connection.prepareStatement(deleteStudentSql3);
            pstmt.setInt(1, studentID);
            pstmt.executeUpdate();
            String deleteStudentSql4 = "DELETE FROM [dbo].QualityofProductsell\n"
                    + "      WHERE ProducID = ?";
            pstmt = connection.prepareStatement(deleteStudentSql4);
            pstmt.setInt(1, studentID);
            pstmt.executeUpdate();

            String deleteStudentSql5 = "DELETE FROM [dbo].[Product]\n"
                    + "      WHERE ProductID = ?";
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
                    + "      ,[Status]\n"
                    + "  FROM [ShopBook88].[dbo].[Product]\n"
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
                Category c = new Category();
                c.setCategoryId(CategoryID);
                int authorID = rs.getInt(7);
                Author a = new Author();
                a.setAuthorID(authorID);
                String imgProduct = rs.getString(8);
                int age = rs.getInt(9);
                ObjectAge o = new ObjectAge();
                o.setAgeId(age);
                int status = rs.getInt(10);

                return new Product(productid, name, price, quantity, description, c, a, imgProduct, o, status);
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
                    + "      ,[Status] = ?\n"
                    + " WHERE ProductID = ?";
            PreparedStatement stm = connection.prepareStatement(stSQL);
            stm = connection.prepareStatement(stSQL);
            stm.setString(1, u.getName());
            stm.setFloat(2, u.getPrice());
            stm.setInt(3, u.getQuantity());
            stm.setString(4, u.getDescription());
            stm.setInt(5, u.getCategory().getCategoryId());
            stm.setInt(6, u.getAuthor().getAuthorID());
            stm.setString(7, u.getImgProduct());
            stm.setInt(8, u.getOage().getAgeId());

            stm.setInt(9, u.getStatus());
            stm.setInt(10, u.getProductId());
            int d = stm.executeUpdate();

        } catch (Exception e) {
            System.out.println("updateProduct" + e.getMessage());
        }
    }

    public Product getProductByName(String name) {
        String query = " Select   p.*, c.CategoryName,oa.Age, a.AuthorName,a.Description \n"
                + "  FROM Product p\n"
                + "INNER JOIN Category c ON c.CategoryID = p.CategoryID\n"
                + "JOIN ObjectAge oa ON oa.AgeID = p.AgeID\n"
                + "JOIN Author a ON a.AuthorID = p.AuthorID\n"
                + "  WHERE p.Name= ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getFloat("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setDescription(rs.getString("description"));
                Category c = new Category();
                c.setCategoryId(rs.getInt(6));
                c.setCategoryName(rs.getString("categoryName"));
                ObjectAge oa = new ObjectAge();
                oa.setAgeId(rs.getInt(9));
                oa.setAge(rs.getString("age"));
                Author a = new Author();
                a.setAuthorID(7);
                a.setAuthorName(rs.getString("authorName"));
                a.setDescription(rs.getString("description"));
                product.setImgProduct(rs.getString("imgProduct"));
                return product;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public void hideProduct(int prodcutId) {
        String query = "UPDATE Product Set Status = 0 Where ProductID =?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, prodcutId);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showProduct(int prodcutId) {
        String query = "UPDATE Product Set Status = 1 Where ProductID =?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, prodcutId);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Product> getProductByStatus(int status, int index) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT p.*, c.CategoryName, oa.Age, a.AuthorName, a.Description AS AuthorDescription \n"
                + "FROM Product p \n"
                + "INNER JOIN Category c ON c.CategoryID = p.CategoryID \n"
                + "JOIN ObjectAge oa ON oa.AgeID = p.AgeID \n"
                + "JOIN Author a ON a.AuthorID = p.AuthorID \n"
                + "WHERE p.status = ?\n"
                + "ORDER BY p.ProductID \n"
                + "OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY;";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, status);
            ps.setInt(2, (index - 1) * 5);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getFloat("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setDescription(rs.getString("description"));
                Category category = new Category();
                category.setCategoryId(rs.getInt("categoryId"));
                category.setCategoryName(rs.getString("categoryName"));
                product.setCategory(category);
                Author author = new Author();
                author.setAuthorID(rs.getInt("authorID"));
                author.setAuthorName(rs.getString("authorName"));
                author.setDescription(rs.getString("description"));
                product.setAuthor(author);
                ObjectAge oage = new ObjectAge();
                oage.setAgeId(rs.getInt("ageId"));
                oage.setAge(rs.getString("age"));
                product.setOage(oage);
                product.setImgProduct(rs.getString("imgProduct"));
                product.setStatus(rs.getInt("status"));
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
     public int getTotalProductByStatus(int status) {
        int total = 0;
        String query = "SELECT COUNT(*) FROM Product WHERE status = ? ";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, status);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;

    
    public List<Product> getProductByCOrder(int COrderid) {
        List<Product> products = new ArrayList<>();
        String query = "  SELECT \n"
                + "    odc.[ProductID],\n"
                + "    p.[Name],\n"
                + "    p.[Price],\n"
                + "    odc.[Quantity],\n"
                + "    p.[Description],\n"
                + "    p.[CategoryID],\n"
                + "    p.[AuthorID],\n"
                + "    p.[ImgProduct],\n"
                + "    p.[AgeID],\n"
                + "    p.[Status]\n"
                + "FROM \n"
                + "    [ShopBook88].[dbo].[OrderDetailCustomer] odc\n"
                + "JOIN \n"
                + "    [ShopBook88].[dbo].[Product] p\n"
                + "ON \n"
                + "    odc.[ProductID] = p.[ProductID]\n"
                + "WHERE \n"
                + "    odc.[OrderCID] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, COrderid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("ProductID"));
                product.setName(rs.getString("Name"));
                product.setPrice(rs.getFloat("Price"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setDescription(rs.getString("Description"));

                Category category = new Category();
                category.setCategoryId(rs.getInt("CategoryID"));
                product.setCategory(category);

                Author author = new Author();
                author.setAuthorID(rs.getInt("AuthorID"));
                product.setAuthor(author);

                ObjectAge oage = new ObjectAge();
                oage.setAgeId(rs.getInt("AgeID"));
                product.setOage(oage);

                product.setImgProduct(rs.getString("ImgProduct"));
                product.setStatus(rs.getInt("Status"));

                products.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public List<Product> getProductByGOrder(int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

