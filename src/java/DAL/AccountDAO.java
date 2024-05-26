/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ASUS TUF
 */
public class AccountDAO extends DBContext {
 
    public Account check(String username, String password) {
        String sql = "SELECT * FROM [dbo].[Account] WHERE Username=? AND Password=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Account a = new Account(
                        rs.getInt("AccountID"),
                        rs.getString("FullName"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Gender"),
                        rs.getString("Email"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Address"),
                        rs.getInt("RoleID"),
                        rs.getString("ImgAccount")
                );
                return a;
            }
        } catch (SQLException e) {
            // Handle or log the exception appropriately
            e.printStackTrace();
        }
        return null;
    }
   public boolean checkUserNameExists(String username) {
        String sql = "SELECT 1 FROM [dbo].[Account] WHERE Username=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
     public boolean checkEmailExists(String email) {
        String sql = "SELECT 1 FROM [dbo].[Account] WHERE Email=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean createUser(String fullName, String username, String password, String email, String phoneNumber, String address) {
        String sql = "INSERT INTO [dbo].[Account] (FullName, Username, Password, Email, PhoneNumber, Address) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, fullName);
            st.setString(2, username);
            st.setString(3, password);
            st.setString(4, email);
            st.setString(5, phoneNumber);
            st.setString(6, address);
            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}