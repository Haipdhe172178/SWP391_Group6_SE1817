/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Author;
import Models.Category;
import Models.ImageBackground;
import Models.ObjectAge;
import Models.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class HomeDAO extends DBContext {

    public ArrayList<Product> get6sellmany() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        ArrayList<Product> data = new ArrayList<>();

        try {
            String sql = "SELECT top 6*, c.CategoryName, oa.Age, a.AuthorName, a.Description AS AuthorDescription\n"
                    + "                FROM Product p\n"
                    + "                INNER JOIN Category c ON c.CategoryID = p.CategoryID\n"
                    + "                INNER JOIN ObjectAge oa ON oa.AgeID = p.AgeID\n"
                    + "                INNER JOIN Author a ON a.AuthorID = p.AuthorID\n"
                    + "				INNER JOIn QualityofProductsell q on p.ProductID=q.ProducID\n"
                    + "				Order by q.quality desc";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
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
                if (product.getStatus() == 1) {
                    data.add(product);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;

    }

    public static void main(String[] args) {
        HomeDAO dal = new HomeDAO();
        ArrayList<ImageBackground> data = new ArrayList<>();
        data = dal.getImageBackground();

        System.out.println(data);
    }

    public ArrayList<Product> get3addnew(int i) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        ArrayList<Product> data = new ArrayList<>();
        try {
            String sql = "SELECT top 3*, c.CategoryName, oa.Age, a.AuthorName, a.Description AS AuthorDescription\n"
                    + "                FROM Product p\n"
                    + "                INNER JOIN Category c ON c.CategoryID = p.CategoryID\n"
                    + "                INNER JOIN ObjectAge oa ON oa.AgeID = p.AgeID\n"
                    + "                INNER JOIN Author a ON a.AuthorID = p.AuthorID\n"
                    + "			\n"
                    + "				Where p.CategoryID = " + i + "\n"
                    + "				order by p.ProductID desc";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
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
                if (product.getStatus() == 1) {
                    data.add(product);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;

    }

    public ArrayList<ImageBackground> getImageBackground() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        ArrayList<ImageBackground> data = new ArrayList<>();
        try {
            String sql = "SELECT [type]\n"
                    + "      ,[url]\n"
                    + "      ,[status]\n"
                    + "      ,[ID]\n"
                    + "  FROM [ShopBook88].[dbo].[Image_background] ";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String type = (rs.getString(1));
                String url = rs.getString(2);

                int id = rs.getInt(4);
                int status = (rs.getInt(3));
                if (status == 1) {
                    data.add(new ImageBackground(type, url, status, id));
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;

    }

    public ArrayList<ImageBackground> getImageBackgroundlist() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        ArrayList<ImageBackground> data = new ArrayList<>();
        try {
            String sql = "SELECT [type]\n"
                    + "      ,[url]\n"
                    + "      ,[status]\n"
                    + "      ,[ID]\n"
                    + "  FROM [ShopBook88].[dbo].[Image_background]";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String type = (rs.getString(1));
                String url = rs.getString(2);

                int status = (rs.getInt(3));
                int id = rs.getInt(4);
                data.add(new ImageBackground(type, url, status, id));

            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;

    }

    public void addImage(ImageBackground ims) {

        ArrayList<ImageBackground> data = new ArrayList<>();
        data = getImageBackgroundlist();
        try {
            String sql = "INSERT INTO [dbo].[Image_background]\n"
                    + "           ([type]\n"
                    + "           ,[url]\n"
                    + "           ,[status]\n"
                    + "           ,[ID])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)\n"
                    + "";

            PreparedStatement stm = connection.prepareStatement(sql);
            int id = data.size() + ims.getId();
            stm.setString(1, ims.getName());
            stm.setString(2, ims.getUrl());
            stm.setInt(3, ims.getStatus());
            stm.setInt(4, id);
            stm.execute();
        } catch (Exception e) {
            System.out.println("addcode" + e.getMessage());
        }

    }

    public String getTime() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
       String date="";
        try {
            String sql = "SELECT [date]\n"
                    + "  FROM [ShopBook88].[dbo].[Time] ";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return date= rs.getString(1);
            }
               
               
                

            
        } catch (SQLException ex) {
            Logger.getLogger(HomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
