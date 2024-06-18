/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Account;
import Models.Author;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS TUF
 */
public class AccountDAO extends DBContext {

    public Account getAccountById(int id) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "Select * From Account Where AccountID =?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                Account acc = new Account();
                acc.setAccountId(rs.getInt(1));
                acc.setFullName(rs.getString(2));
                acc.setUserName(rs.getString(3));
                acc.setPassWord(rs.getString(4));
                acc.setGender(rs.getString(5));
                acc.setEmail(rs.getString(6));
                acc.setPhoneNumber(rs.getString(7));
                acc.setAddress(rs.getString(8));
                acc.setRoleId(rs.getInt(9));
                acc.setImgAccount(rs.getString(10));

                return acc;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

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
                        rs.getString("ImgAccount"),
                        rs.getInt("status")
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
        String sql = "INSERT INTO [dbo].[Account] (FullName, Username, Password, Email, PhoneNumber, Address, RoleID,Status) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, fullName);
            st.setString(2, username);
            st.setString(3, password);
            st.setString(4, email);
            st.setString(5, phoneNumber);
            st.setString(6, address);
            st.setInt(7, 3); // Set RoleID to 3 by default
            st.setInt(8, 1); // Set status to 1 by default
            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Account> getAllAccount() {
        List<Account> listAcc = new ArrayList<>();
        String query = "Select * From Account";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account acc = new Account();
                acc.setAccountId(rs.getInt(1));
                acc.setFullName(rs.getString(2));
                acc.setUserName(rs.getString(3));
                acc.setPassWord(rs.getString(4));
                acc.setGender(rs.getString(5));
                acc.setEmail(rs.getString(6));
                acc.setPhoneNumber(rs.getString(7));
                acc.setAddress(rs.getString(8));
                acc.setRoleId(rs.getInt(9));
                acc.setImgAccount(rs.getString(10));
                acc.setStatus(rs.getInt(11));

                listAcc.add(acc);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listAcc;
    }

    public boolean updateAvatar(Account a, String img) {
        String sql = "UPDATE Account\n"
                + "SET ImgAccount = ?\n"
                + "WHERE AccountID = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, img);
            ps.setInt(2, a.getAccountId());
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAccountInfo(Account a) {
        String sql = "UPDATE Account\n"
                + "SET FullName = ?, Gender = ?, PhoneNumber = ?, Address = ?\n"
                + "WHERE AccountID  = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, a.getFullName());
            ps.setString(2, a.getGender());
            ps.setString(3, a.getPhoneNumber());
            ps.setString(4, a.getAddress());
            ps.setInt(5, a.getAccountId());
            int rowUpdate = ps.executeUpdate();
            return rowUpdate > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean changePassword(Account a) {
        String sql = "UPDATE Account SET Password = ? WHERE userName = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, a.getPassWord());
            ps.setString(2, a.getUserName());
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePassword(Account account) {
        String sql = "UPDATE Account SET Password = ? WHERE Email = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, account.getPassWord());
            pst.setString(2, account.getEmail());
            int rowCount = pst.executeUpdate();
            return rowCount > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Account getAccountByEmail(String email) {
        String sql = "SELECT * FROM [dbo].[Account] WHERE Email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setAccountId(rs.getInt("AccountID"));
                account.setFullName(rs.getString("FullName"));
                account.setUserName(rs.getString("Username"));
                account.setPassWord(rs.getString("Password"));
                account.setGender(rs.getString("Gender"));
                account.setEmail(rs.getString("Email"));
                account.setPhoneNumber(rs.getString("PhoneNumber"));
                account.setAddress(rs.getString("Address"));
                account.setRoleId(rs.getInt("RoleID"));
                account.setImgAccount(rs.getString("ImgAccount"));
                account.setStatus(rs.getInt("Status"));
                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        AccountDAO ac = new AccountDAO();
        List<Account> accounts = ac.getAllAccount();
        for (Account account : accounts) {
            System.out.println(accounts);
        }
    }

}
