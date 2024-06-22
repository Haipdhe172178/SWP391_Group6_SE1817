/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huyca
 */
public class CategoryDao extends DBContext {

    // Hàm list ra toàn bộ category
    public List<Category> getallCategorys() {
        List<Category> categorys = new ArrayList<>();
        String query = "Select * From Category";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("categoryId"));
                category.setCategoryName(rs.getString("categoryName"));
                categorys.add(category);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return categorys;
    }

    //Get category by categoryID
    public Category getCategoryByID(int id) {
        String query = "Select * From Category WHERE CategoryID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("categoryId"));
                category.setCategoryName(rs.getString("categoryName"));
                return category;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categorys = categoryDao.getallCategorys();
        for (Category category : categorys) {
            System.out.println("CategoryId: " + category.getCategoryId());
            System.out.println("CategoryName: " + category.getCategoryName());
        }

        Category c = categoryDao.getCategoryByID(1);
        System.out.println(c.getCategoryName());
    }

    public void addCategory(String name) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            String sql = "INSERT INTO Category (CategoryName) VALUES (?)";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);

            stm.execute();
        } catch (Exception e) {
            System.out.println("addcategory" + e.getMessage());
        }

    }

    public void updateCategory(String id, String name) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            String stSQL = "UPDATE [dbo].[Category]\n"
                    + "   SET [CategoryName] = ?\n"
                    + " WHERE CategoryID = ?";
            PreparedStatement stm = connection.prepareStatement(stSQL);
            stm = connection.prepareStatement(stSQL);
            stm.setString(1, name);
            stm.setString(2, id);

            int d = stm.executeUpdate();

        } catch (Exception e) {
            System.out.println("updateCategory" + e.getMessage());
        }

    }

    public List<Category> getallCategorys(String text) {
       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
      List<Category> categories = new ArrayList<>();
String query = "SELECT [CategoryID], [CategoryName] FROM [ShopBook88].[dbo].[Category] WHERE CategoryName LIKE ?";
       
       try {
        
        // Kết hợp dấu % vào tham số tìm kiếm
        String searchPattern = "%" + text + "%";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, searchPattern);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Category category = new Category();
            category.setCategoryId(rs.getInt("CategoryID")); // Sử dụng đúng tên cột như trong truy vấn
            category.setCategoryName(rs.getString("CategoryName")); // Sử dụng đúng tên cột như trong truy vấn
            categories.add(category);
        }

        rs.close();
        ps.close();
    } catch (Exception ex) {
           ex.printStackTrace();
    }
    return categories;
}
    }

