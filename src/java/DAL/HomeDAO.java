///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package DAL;
//
//import Models.ImageBackground;
//import Models.Product;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author USER
// */
//public class HomeDAO extends DBContext {
//
//   
//
//    public ArrayList<Product> get6sellmany() {
//        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//        ArrayList<Product> data = new ArrayList<>();
//
//        try {
//            String sql = "select top 6* from QualityofProductsell q INNER JOIN Product p on p.ProductID=q.ProducID  ORDER BY q.quality DESC";
//
//            PreparedStatement stm = connection.prepareStatement(sql);
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                int id = (rs.getInt(3));
//                String name = rs.getString(4);
//                float Price = (rs.getFloat(5));
//                int Quantity = (rs.getInt(6));
//                String Description = rs.getString(7);
//                int CategoryID = (rs.getInt(8));
//                int AuthorID = (rs.getInt(9));
//                String img = rs.getString(10);
//                int ageId = rs.getInt(9);
////                data.add(new Product(id, name, Price, Quantity, Description, CategoryID, AuthorID, img, ageId) );
//                 data.add(new Product(id, name, Price, Quantity, Description, CategoryID, AuthorID, img, ageId));
//
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(HomeDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return data;
//
//    }
//
//    public static void main(String[] args) {
//        HomeDAO dal = new HomeDAO();
//        ArrayList<ImageBackground> data = new ArrayList<>();
//        data = dal.getImageBackground();
//
//        System.out.println(data);
//    }
//
//    public ArrayList<Product> get3addnew(int i) {
//        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//        ArrayList<Product> data = new ArrayList<>();
//        try {
//            String sql = "SELECT top 3*\n"
//                    + "              FROM Product\n"
//                    + "WHere CategoryID = " + i + "\n"
//                    + "ORDER BY ProductID DESC";
//
//            PreparedStatement stm = connection.prepareStatement(sql);
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                int id = (rs.getInt(1));
//                String name = rs.getString(2);
//                float Price = (rs.getFloat(3));
//                int Quantity = (rs.getInt(4));
//                String Description = rs.getString(5);
//                int CategoryID = (rs.getInt(6));
//                int AuthorID = (rs.getInt(7));
//                String img = rs.getString(8);
//                int ageId = rs.getInt(9);
////                data.add(new Product(id, name, Price, Quantity, Description, CategoryID, AuthorID, img, ageId) );
//                data.add(new Product(id, name, Price, Quantity, Description, CategoryID, AuthorID, img, ageId));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(HomeDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return data;
//
//    }
//
//    public ArrayList<ImageBackground> getImageBackground() {
//        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//        ArrayList<ImageBackground> data = new ArrayList<>();
//        try {
//            String sql = "SELECT [type]\n"
//                    + "      ,[url]\n"
//                    + "      ,[status]\n"
//                    + "      ,[ID]\n"
//                    + "  FROM [ShopBook88].[dbo].[Image_background] ";
//
//            PreparedStatement stm = connection.prepareStatement(sql);
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                String type = (rs.getString(1));
//                String url = rs.getString(2);
//
//                int id = rs.getInt(4);
//                int status = (rs.getInt(3));
//                if (status == 1) {
//                    data.add(new ImageBackground(type, url, status, id));
//                }
//
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(HomeDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return data;
//
//    }
//
//    public ArrayList<ImageBackground> getImageBackgroundlist() {
//        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//        ArrayList<ImageBackground> data = new ArrayList<>();
//        try {
//            String sql = "SELECT [type]\n"
//                    + "      ,[url]\n"
//                    + "      ,[status]\n"
//                    + "      ,[ID]\n"
//                    + "  FROM [ShopBook88].[dbo].[Image_background]";
//
//            PreparedStatement stm = connection.prepareStatement(sql);
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                String type = (rs.getString(1));
//                String url = rs.getString(2);
//
//                int status = (rs.getInt(3));
//                int id = rs.getInt(4);
//                data.add(new ImageBackground(type, url, status, id));
//
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(HomeDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return data;
//
//    }
//
//    public void addImage(ImageBackground ims) {
//
//        ArrayList<ImageBackground> data = new ArrayList<>();
//        data = getImageBackgroundlist();
//        try {
//            String sql = "INSERT INTO [dbo].[Image_background]\n"
//                    + "           ([type]\n"
//                    + "           ,[url]\n"
//                    + "           ,[status]\n"
//                    + "           ,[ID])\n"
//                    + "     VALUES\n"
//                    + "           (?\n"
//                    + "           ,?\n"
//                    + "           ,?\n"
//                    + "           ,?)\n"
//                    + "";
//
//            PreparedStatement stm = connection.prepareStatement(sql);
//            int id = data.size() + ims.getId();
//            stm.setString(1, ims.getName());
//            stm.setString(2, ims.getUrl());
//            stm.setInt(3, ims.getStatus());
//            stm.setInt(4, id);
//            stm.execute();
//        } catch (Exception e) {
//            System.out.println("addcode" + e.getMessage());
//        }
//
//    }
//}
