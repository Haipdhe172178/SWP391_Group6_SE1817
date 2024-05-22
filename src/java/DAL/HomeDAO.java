/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

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
public class HomeDAO extends DBContext{

    public ArrayList<Product> get3radum() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
     ArrayList<Product> data = new ArrayList<>();
        try {
            String sql = "SELECT top 3* \n" +
            " FROM [Product] order by newId() ";
           
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int id = (rs.getInt(1));
                String name = rs.getString(2);
                float Price = (rs.getFloat(3));
                 int Quantity = (rs.getInt(4));
                String Description = rs.getString(5);
                int CategoryID = (rs.getInt(6));
                int AuthorID = (rs.getInt(7));              
                String img = rs.getString(8);
                int ageId = rs.getInt(9);
                data.add(new Product(id, name, Price, Quantity, Description, CategoryID, AuthorID, img, ageId) );

            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;

    
    
    
    }

    public ArrayList<Product> get6sellmany() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
         ArrayList<Product> data = new ArrayList<>();
        
        try {
            String sql = "select top 6* from QualityofProductsell q INNER JOIN Product p on p.ProductID=q.ProducID  ORDER BY q.quality DESC";
            
           
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int id = (rs.getInt(3));
                String name = rs.getString(4);
                float Price = (rs.getFloat(5));
                 int Quantity = (rs.getInt(6));
                String Description = rs.getString(7);
                int CategoryID = (rs.getInt(8));
                int AuthorID = (rs.getInt(9));              
                String img = rs.getString(10);
                int ageId = rs.getInt(9);
                data.add(new Product(id, name, Price, Quantity, Description, CategoryID, AuthorID, img, ageId) );

            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    
    
    }
    
    public static void main(String[] args) {
        HomeDAO dal = new HomeDAO();
        ArrayList<Product> data = new ArrayList<>();
        data = dal.get6sellmany();
        
        
        System.out.println(data);
    }
}
