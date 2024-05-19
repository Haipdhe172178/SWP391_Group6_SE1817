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
}
