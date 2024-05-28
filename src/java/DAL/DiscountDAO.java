package DAL;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import Models.UsedCoupon;
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
public class DiscountDAO extends DBContext {

    public void addDiscount(String codename, String discount, String coupon_type, String quality) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            String sql = "INSERT INTO [dbo].[Used_coupon]\n"
                    + "           ([CodeName]\n"
                    + "           ,[Discount]\n"
                    + "           ,[Coupon_type]\n"
                    + "           ,[Quantity])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, codename);
            stm.setString(2, discount);
            stm.setString(3, coupon_type);
            stm.setString(4, quality);
            stm.execute();
        } catch (Exception e) {
            System.out.println("addcode" + e.getMessage());
        }

    }

    public ArrayList<UsedCoupon> listcode() {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        ArrayList<UsedCoupon> data = new ArrayList<UsedCoupon>();
        try {
            String sql = "SELECT [CodeID]\n"
                    + "      ,[CodeName]\n"
                    + "      ,[Discount]\n"
                    + "      ,[Coupon_type]\n"
                    + "      ,[Quantity]\n"
                    + "  FROM [dbo].[Used_coupon]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String codename = rs.getString(2);
                int discout = rs.getInt(3);
                String Coupon_type = rs.getString(4);
                int Quantity  = rs.getInt(5);   
             data.add(new UsedCoupon(id, codename, discout, Coupon_type, Quantity));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;

    }
    
    
    
    
   
    
    
    public static void main(String[] args) {
           ArrayList<UsedCoupon> data = new ArrayList<>();
        DiscountDAO dal = new DiscountDAO();  
        data = dal.listcode();
        System.out.println(data);
    }

    public void delete(String id) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    try {
            String stSQL = "DELETE FROM [dbo].[Used_coupon]\n" +
"      WHERE CodeID = ?";
                 PreparedStatement stm = connection.prepareStatement(stSQL);
               stm = connection.prepareStatement(stSQL);

            stm.setString(1, id);
            stm.execute();
        } catch (Exception e) {
            System.out.println("delete" + e.getMessage());
        }
    
    
    }
}
