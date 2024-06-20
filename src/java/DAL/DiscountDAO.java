package DAL;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Models.Author;
import Models.Category;
import Models.ObjectAge;
import Models.Product;
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

    public void addDiscount(String codename, String discount, String coupon_type, String quality, String status) {
        try {
            String sql = "INSERT INTO [dbo].[Used_coupon]\n"
                    + "           ([CodeName]\n"
                    + "           ,[Discount]\n"
                    + "           ,[Coupon_type]\n"
                    + "           ,[Quantity]\n"
                    + "           ,[status])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, codename);
            stm.setString(2, discount);
            stm.setString(3, coupon_type);
            stm.setString(4, quality);
            stm.setString(5, status);
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
                    + "      ,[status]\n"
                    + "  FROM [dbo].[Used_coupon]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String codename = rs.getString(2);
                float discout = rs.getFloat(3);
                String Coupon_type = rs.getString(4);
                int Quantity = rs.getInt(5);

                int status = rs.getInt(6);

                data.add(new UsedCoupon(id, codename, discout, Coupon_type, Quantity, status));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;

    }

    public ArrayList<UsedCoupon> Displaycode() {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        ArrayList<UsedCoupon> data = new ArrayList<UsedCoupon>();
        try {
            String sql = "SELECT [CodeID]\n"
                    + "      ,[CodeName]\n"
                    + "      ,[Discount]\n"
                    + "      ,[Coupon_type]\n"
                    + "      ,[Quantity]\n"
                    + "      ,[status]\n"
                    + "  FROM [dbo].[Used_coupon]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String codename = rs.getString(2);
                float discout = rs.getFloat(3);
                String Coupon_type = rs.getString(4);
                int Quantity = rs.getInt(5);

                int status = rs.getInt(6);
                if (status == 1) {
                    data.add(new UsedCoupon(id, codename, discout, Coupon_type, Quantity, status));
                }

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
        System.out.println(data.size());
    }

    public UsedCoupon get1DistoUpdate(String id) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        PreparedStatement stm;//thuc hien cau lenh sql
        ResultSet rs;// lua tru du lieu duoc lay ve tu select

        try {
            String stSQL = "SELECT [CodeID]\n"
                    + "      ,[CodeName]\n"
                    + "      ,[Discount]\n"
                    + "      ,[Coupon_type]\n"
                    + "      ,[Quantity]\n"
                    + "      ,[status]\n"
                    + "  FROM [ShopBook88].[dbo].[Used_coupon]\n"
                    + "  where CodeID = ?";

            stm = connection.prepareStatement(stSQL);
            stm.setString(1, id);
            rs = stm.executeQuery();

            while (rs.next()) {

                int codeid = rs.getInt(1);
                String CodeName = rs.getString(2);
                float Discount = rs.getFloat(3);
                int quantity = rs.getInt(5);
                String Coupon_type = rs.getString(4);
                int status = rs.getInt(6);

                return new UsedCoupon(codeid, CodeName, Discount, Coupon_type, quantity, status);
                //   student.setId(rs.getInt("sid"));
            }
        } catch (Exception e) {
            System.out.println("getUpdate" + e.getMessage());
        }
        return null;

    }

    public void UpdateDiscount(String codename, String discountStr, String coupon_type, String qualityStr, String status, String id) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            String stSQL = "UPDATE [dbo].[Used_coupon]\n"
                    + "   SET [CodeName] = ?\n"
                    + "      ,[Discount] = ?\n"
                    + "      ,[Coupon_type] = ?\n"
                    + "      ,[Quantity] = ?\n"
                    + "      ,[status] = ?\n"
                    + " WHERE CodeID = ?";
            PreparedStatement stm = connection.prepareStatement(stSQL);
            stm = connection.prepareStatement(stSQL);
            stm.setString(1, codename);
            stm.setString(2, discountStr);
            stm.setString(3, coupon_type);
            stm.setString(4, qualityStr);
            stm.setString(5, status);
            stm.setString(6, id);
            int d = stm.executeUpdate();

        } catch (Exception e) {
            System.out.println("updateProduct" + e.getMessage());
        }

    }

}

