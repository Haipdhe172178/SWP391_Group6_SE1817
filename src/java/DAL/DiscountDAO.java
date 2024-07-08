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

    public int getToralDiscount() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String query = "  select count(*) from Used_coupon";
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

    public int getToralDiscountbyname(String searchtext) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String query = "  select count(*) from Used_coupon where Used_coupon.Coupon_type like ?";
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

    public ArrayList<UsedCoupon> listcodepage(int indexx) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        ArrayList<UsedCoupon> data = new ArrayList<UsedCoupon>();
        try {
            String sql = "SELECT [CodeID]\n"
                    + "                        ,[CodeName]\n"
                    + "                   ,[Discount]\n"
                    + "                , [Coupon_type]\n"
                    + "                   ,[Quantity]\n"
                    + "                  ,[status]\n"
                    + "                  FROM [dbo].[Used_coupon]  order by CodeID\n"
                    + "  offset ?  rows fetch next 3 rows only";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, (indexx - 1) * 3);
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

    public ArrayList<UsedCoupon> listcodepageseachbytype(int indexx, String searchtext) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        ArrayList<UsedCoupon> data = new ArrayList<UsedCoupon>();
        try {
            String sql = "SELECT [CodeID]\n"
                    + "                        ,[CodeName]\n"
                    + "                   ,[Discount]\n"
                    + "                , [Coupon_type]\n"
                    + "                   ,[Quantity]\n"
                    + "                  ,[status]\n"
                    + "                  FROM [dbo].[Used_coupon]  where Used_coupon.Coupon_type like ?\n"
                    + "				  order by CodeID\n"
                    + "  offset ?  rows fetch next 3 rows only";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + searchtext + "%");
            stm.setInt(2, (indexx - 1) * 3);
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

    public int getToralDiscountbystatus(String status) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String query = "   select count(*) from Used_coupon where Used_coupon.status = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                return rs.getInt(1);
            }
        } catch (Exception e) {
        }

        return 0;

    }

    public ArrayList<UsedCoupon> listcodepageseachbystatus(int indexx, String status1) {
        ArrayList<UsedCoupon> data = new ArrayList<UsedCoupon>();
        try {
            String sql = "SELECT [CodeID]\n"
                    + "                        ,[CodeName]\n"
                    + "                   ,[Discount]\n"
                    + "                , [Coupon_type]\n"
                    + "                   ,[Quantity]\n"
                    + "                  ,[status]\n"
                    + "                  FROM [dbo].[Used_coupon]  where Used_coupon.status = ?\n"
                    + "				  order by CodeID\n"
                    + "  offset ?  rows fetch next 3 rows only";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, status1);
            stm.setInt(2, (indexx - 1) * 3);
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

    public boolean UpdateTime(String date_rawid, String date_rawup) {
        boolean isUpdated = false;
        try {
            String stSQL = "UPDATE [dbo].[Time]\n"
                    + "SET [date] = ?\n"
                    + "WHERE [date] = ?";
            PreparedStatement stm = connection.prepareStatement(stSQL);

            stm.setString(1, date_rawup);
            stm.setString(2, date_rawid);
            int rowsUpdated = stm.executeUpdate();

            if (rowsUpdated > 0) {
                isUpdated = true;
            }

        } catch (Exception e) {
            System.out.println("Error during update: " + e.getMessage());
        }
        return isUpdated;
    }

    public UsedCoupon getDiscountByCodeName(String codeName) {
        String query = "select * from Used_coupon where  CodeName = ?";
        UsedCoupon coupon = null;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, codeName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                coupon = new UsedCoupon();
                coupon.setCodeId(rs.getInt(1));
                coupon.setCodeName(rs.getString(2));
                coupon.setDiscount(rs.getInt(3));
                coupon.setCouponType(rs.getString(4));
                coupon.setQuantity(rs.getInt(5));
                coupon.setStatus(rs.getInt(6));
            }
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coupon;
    }

    public boolean checkHistoryCoupon(int accID, int codeID) {
        String query = "select * from History_coupons where  AccountID = ? AND CodeID = ?";
        boolean isExisted = false;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, accID);
            ps.setInt(2, codeID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                isExisted = true;
            }
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isExisted;
    }
}
