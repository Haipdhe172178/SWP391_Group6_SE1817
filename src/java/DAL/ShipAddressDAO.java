/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author ASUS TUF
 */
import Models.ShipAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShipAddressDAO extends DBContext{

    private DBContext dbContext;

    public ShipAddressDAO() {
        dbContext = new DBContext();
    }

    // Insert ShipAddress
    public void insertShipAddress(ShipAddress address) throws SQLException {
        String sql = "INSERT INTO ShippingAddress (AccID, [Address], PhoneNumber, isDefault) VALUES (?, ?, ?, ?)";
        try (Connection connection = dbContext.connection; PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, address.getAccID());
            preparedStatement.setString(2, address.getAddress());
            preparedStatement.setString(3, address.getPhoneNumber());
            preparedStatement.setBoolean(4, address.isDefault());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    // Select ShipAddress by ID
    public ShipAddress selectShipAddress(int addressID) {
        ShipAddress address = null;
        String sql = "SELECT AddressID, AccID, Address, PhoneNumber, isDefault FROM ShippingAddress WHERE AddressID = ?";
        try (Connection connection = dbContext.connection; PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, addressID);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int accID = rs.getInt("AccID");
                String addr = rs.getString("Address");
                String phoneNumber = rs.getString("PhoneNumber");
                boolean isDefault = rs.getBoolean("isDefault");
                address = new ShipAddress(addressID, accID, addr, phoneNumber, isDefault);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return address;
    }

    // Select all ShipAddresses
    public List<ShipAddress> selectAllShipAddresses() {
        List<ShipAddress> addresses = new ArrayList<>();
        String sql = "SELECT * FROM ShippingAddress";
        try (Connection connection = dbContext.connection; PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int addressID = rs.getInt("AddressID");
                int accID = rs.getInt("AccID");
                String addr = rs.getString("Address");
                String phoneNumber = rs.getString("PhoneNumber");
                boolean isDefault = rs.getBoolean("isDefault");
                addresses.add(new ShipAddress(addressID, accID, addr, phoneNumber, isDefault));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return addresses;
    }

    // Delete ShipAddress
    public boolean deleteShipAddress(int addressID) throws SQLException {
        boolean rowDeleted;
        String sql = "DELETE FROM ShippingAddress WHERE AddressID = ?";
        try (Connection connection = dbContext.connection; PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, addressID);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    // Update ShipAddress
    public boolean updateShipAddress(ShipAddress address) throws SQLException {
        boolean rowUpdated;
        String sql = "UPDATE ShippingAddress SET AccID = ?, Address = ?, PhoneNumber = ?, isDefault = ? WHERE AddressID = ?";
        try (Connection connection = dbContext.connection; PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, address.getAccID());
            preparedStatement.setString(2, address.getAddress());
            preparedStatement.setString(3, address.getPhoneNumber());
            preparedStatement.setBoolean(4, address.isDefault());
            preparedStatement.setInt(5, address.getAddressID());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
            rowUpdated = false;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public List<ShipAddress> selectShipAddressesByAccID(int accID) {
        List<ShipAddress> addresses = new ArrayList<>();
        String sql = "SELECT * FROM ShippingAddress WHERE AccID = ?";
        try (Connection connection = dbContext.connection; PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, accID);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int addressID = rs.getInt("AddressID");
                String addr = rs.getString("Address");
                String phoneNumber = rs.getString("PhoneNumber");
                boolean isDefault = rs.getBoolean("isDefault");
                addresses.add(new ShipAddress(addressID, accID, addr, phoneNumber, isDefault));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return addresses;
    }

    public List<ShipAddress> getUserAddress(int accountId) {
        List<ShipAddress> addresses = new ArrayList<>();
        String sql = "SELECT * FROM ShippingAddress WHERE [AccID] = ? ";
        try (Connection connection = dbContext.connection; PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, accountId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int addressID = rs.getInt("AddressID");
                int accID = rs.getInt("AccID");
                String addr = rs.getString("Address");
                String phoneNumber = rs.getString("PhoneNumber");
                boolean isDefault = rs.getBoolean("isDefault");
                addresses.add(new ShipAddress(addressID, accID, addr, phoneNumber, isDefault));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return addresses;
    }

    public boolean addShippingAddress(ShipAddress address) {
        boolean isCompleted = false;
        String sql = "INSERT INTO ShippingAddress (AccID, [Address], PhoneNumber, isDefault) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, address.getAccID());
            ps.setString(2, address.getAddress());
            ps.setString(3, address.getPhoneNumber());
            ps.setBoolean(4, false);
            int row = ps.executeUpdate();
            if (row > 0) {
                isCompleted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            isCompleted = false;
        }
        return isCompleted;
    }

    public boolean updateShippingAddress(ShipAddress address) {
        boolean isCompleted = false;
        String sql = "UPDATE ShippingAddress SET AccID = ?, Address = ?, PhoneNumber = ?, isDefault = ? WHERE AddressID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, address.getAccID());
            ps.setString(2, address.getAddress());
            ps.setString(3, address.getPhoneNumber());
            ps.setBoolean(4, address.isDefault());
            ps.setInt(5, address.getAddressID());
            int row = ps.executeUpdate();
            if (row > 0) {
                isCompleted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            isCompleted = false;
        }
        return isCompleted;
    }
}
