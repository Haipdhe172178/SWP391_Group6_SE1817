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
    //dem so luon category trong database

    public int getToralCategory() {
        String query = " select count(*) from Category";
        try {
            PreparedStatement ps = connection.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                return rs.getInt(1);
            }
        } catch (Exception e) {
        }

        return 0;
    }

    public static void main(String[] args) {
        CategoryDao categoryDao = new CategoryDao();
        int count = categoryDao.getToralCategory();
        System.out.println(count);
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

    public List<Category> getallCategorypage(int index) {
        List<Category> categorys = new ArrayList<>();
        String query = " select * from Category\n"
                + "  order by CategoryID\n"
                + "  offset ? rows fetch next 3 rows only";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, (index - 1) * 3);
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

    public List<Category> getallCategorypseachbyname(int index, String searchtext) {
        List<Category> categories = new ArrayList<>();
        String query = "select * from Category where CategoryName like ? "
                + "order by CategoryID "
                + "offset ? rows fetch next 3 rows only";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + searchtext + "%"); 
            ps.setInt(2, (index - 1) * 3);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("categoryId"));
                category.setCategoryName(rs.getString("categoryName"));
                categories.add(category);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return categories;
    }

    public int getToralCategorybyname(String searchtext) {
         String query = "  select count(*) from Category where CategoryName like  ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + searchtext + "%"); 

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                return rs.getInt(1);
            }
        } catch (Exception e) {
        }

        return 0;
    }
}
