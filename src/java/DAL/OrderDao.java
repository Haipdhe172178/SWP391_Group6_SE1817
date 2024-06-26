/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Account;
import Models.OrderCustomer;
import Models.OrderDetailCustomer;
import Models.OrderDetailGuest;
import Models.OrderGuest;
import Models.Product;
import Models.Status;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author huyca
 */
public class OrderDao extends DBContext {

    public List<Account> getAllAccount() {
        List<Account> listAcc = new ArrayList<>();
        String query = "Select * From Account Where RoleID = 3";
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

    public List<OrderCustomer> getAllOrderCustomers() {
        List<OrderCustomer> listOrderCustomers = new ArrayList<>();
        String query = "SELECT oc.OrderCID, oc.AccountID, a.FullName, a.Email,"
                + " a.Address, a.PhoneNumber, oc.TotalPrice, oc.Date, oc.StatusID, s.StatusName "
                + "FROM OrderCustomer oc "
                + "JOIN StatusOrder s ON s.StatusID = oc.StatusID "
                + "JOIN Account a ON a.AccountID = oc.AccountID";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderCId = rs.getInt("OrderCID");
                Account account = new Account();
                account.setAccountId(rs.getInt("AccountID"));
                account.setFullName(rs.getString("FullName"));
                account.setEmail(rs.getString("Email"));
                account.setPhoneNumber(rs.getString("PhoneNumber"));
                account.setAddress(rs.getString("Address"));
                float totalPrice = rs.getFloat("TotalPrice");
                Date date = rs.getDate("Date");
                Status status = new Status();
                status.setStatusId(rs.getInt("StatusID"));
                status.setStatusName(rs.getString("StatusName"));
                List<OrderDetailCustomer> orderDetails = getOrderDetailCustomers(orderCId);
                OrderCustomer orderCustomer = new OrderCustomer(orderDetails, account, totalPrice, date, status);
                listOrderCustomers.add(orderCustomer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listOrderCustomers;
    }

    // Lấy tất cả các chi tiết đơn hàng của khách hàng
    public List<OrderDetailCustomer> getOrderDetailCustomers(int orderCId) {
        List<OrderDetailCustomer> listOrderDetailCustomers = new ArrayList<>();
        String query = "SELECT * FROM OrderDetailCustomer WHERE OrderCID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, orderCId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("ProductID");
                int quantity = rs.getInt("Quantity");
                float unitPrice = rs.getFloat("UnitPrice");
                OrderDetailCustomer orderDetailCustomer = new OrderDetailCustomer(orderCId, productId, quantity, unitPrice);
                listOrderDetailCustomers.add(orderDetailCustomer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listOrderDetailCustomers;
    }

    public List<OrderGuest> getAllOrderGuests() {
        List<OrderGuest> listOrderGuests = new ArrayList<>();
        String query = "Select og.OrderGID,og.FullName,og.Email,"
                + "og.PhoneNumber,og.Address , og.TotalPrice, og.Date, "
                + "og.StatusID ,s.StatusName\n"
                + "  From OrderGuest og \n"
                + "  Join StatusOrder s On s.StatusID = og.StatusID";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderGId = rs.getInt("OrderGID");
                String fullName = rs.getString("FullName");
                String email = rs.getString("Email");
                String phoneNumber = rs.getString("PhoneNumber");
                String address = rs.getString("Address");
                float totalPrice = rs.getFloat("TotalPrice");
                Date date = rs.getDate("Date");
                Status status = new Status();
                status.setStatusId(rs.getInt("StatusID"));
                status.setStatusName(rs.getString("statusName"));
                List<OrderDetailGuest> orderDetails = getOrderDetailGuests(orderGId);
                OrderGuest orderGuest = new OrderGuest(orderDetails, fullName, email, phoneNumber, address, totalPrice, date, status);
                listOrderGuests.add(orderGuest);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listOrderGuests;
    }

    public List<OrderDetailGuest> getOrderDetailGuests(int orderGId) {
        List<OrderDetailGuest> listOrderDetailGuests = new ArrayList<>();
        String query = "SELECT * FROM OrderDetailGuest WHERE OrderGID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, orderGId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("ProductID");
                int quantity = rs.getInt("Quantity");
                float unitPrice = rs.getFloat("UnitPrice");
                OrderDetailGuest orderDetailGuest = new OrderDetailGuest(orderGId, productId, quantity, unitPrice);
                listOrderDetailGuests.add(orderDetailGuest);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listOrderDetailGuests;
    }

    public List<OrderCustomer> getRecentOrderCustomers() {
        List<OrderCustomer> listOrderCustomers = new ArrayList<>();
        String query = "SELECT TOP 5 oc.OrderCID, oc.AccountID, oc.TotalPrice, oc.Date, oc.StatusID, s.StatusName, "
                + "a.FullName, a.Email, a.PhoneNumber, a.Address "
                + "FROM OrderCustomer oc "
                + "JOIN StatusOrder s ON oc.StatusID = s.StatusID "
                + "JOIN Account a ON a.AccountID = oc.AccountID "
                + "ORDER BY oc.Date DESC;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderCId = rs.getInt("OrderCID");
                Account account = new Account();
                account.setAccountId(rs.getInt("AccountID"));
                account.setFullName(rs.getString("FullName"));
                account.setEmail(rs.getString("Email"));
                account.setPhoneNumber(rs.getString("PhoneNumber"));
                account.setAddress(rs.getString("Address"));
                float totalPrice = rs.getFloat("TotalPrice");
                Date date = rs.getDate("Date");
                Status status = new Status();
                status.setStatusId(rs.getInt("StatusID"));
                status.setStatusName(rs.getString("StatusName"));
                List<OrderDetailCustomer> orderDetails = getOrderDetailCustomers(orderCId);
                OrderCustomer orderCustomer = new OrderCustomer(orderDetails, account, totalPrice, date, status);
                listOrderCustomers.add(orderCustomer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listOrderCustomers;
    }

    public List<OrderGuest> getRecentOrderGuests() {
        List<OrderGuest> listOrderGuests = new ArrayList<>();
        String query = "SELECT TOP 5 og.OrderGID,og.FullName,og.Email,"
                + "og.PhoneNumber,og.Address , og.TotalPrice, og.Date, og.StatusID ,s.StatusName\n"
                + "  FROM OrderGuest og\n"
                + "  Join StatusOrder s On s.StatusID = og.StatusID\n"
                + "  ORDER BY Date DESC";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderGId = rs.getInt("OrderGID");
                String fullName = rs.getString("FullName");
                String email = rs.getString("Email");
                String phoneNumber = rs.getString("PhoneNumber");
                String address = rs.getString("Address");
                float totalPrice = rs.getFloat("TotalPrice");
                Date date = rs.getDate("Date");
                Status status = new Status();
                status.setStatusId(rs.getInt("StatusID"));
                status.setStatusName(rs.getString("statusName"));
                List<OrderDetailGuest> orderDetails = getOrderDetailGuests(orderGId);
                OrderGuest orderGuest = new OrderGuest(orderDetails, fullName, email, phoneNumber, address, totalPrice, date, status);
                listOrderGuests.add(orderGuest);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listOrderGuests;
    }

    public List<Product> getMostPurchasedProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT TOP 5 p.ProductID, p.Name, p.Price, p.imgProduct, "
                + "(COALESCE(SUM(odc.Quantity), 0) + COALESCE(SUM(odg.Quantity), 0)) AS TotalQuantity "
                + "FROM Product p "
                + "LEFT JOIN OrderDetailCustomer odc ON p.ProductID = odc.ProductID "
                + "LEFT JOIN OrderDetailGuest odg ON p.ProductID = odg.ProductID "
                + "GROUP BY p.ProductID, p.Name, p.Price, p.imgProduct "
                + "ORDER BY TotalQuantity DESC";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("ProductID"));
                product.setName(rs.getString("Name"));
                product.setPrice(rs.getFloat("Price"));
                product.setImgProduct(rs.getString("imgProduct"));
                product.setQuantity(rs.getInt("TotalQuantity"));
                products.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return products;
    }
    public List<Map<String, Object>> getTopBuyers() {
    List<Map<String, Object>> topBuyers = new ArrayList<>();
    String query = "SELECT Top 5 a.AccountID, a.FullName, a.Email, a.PhoneNumber, a.Address, SUM(oc.TotalPrice) AS TotalSpent "
                 + "FROM Account a "
                 + "JOIN OrderCustomer oc ON a.AccountID = oc.AccountID "
                 + "GROUP BY a.AccountID, a.FullName, a.Email, a.PhoneNumber, a.Address "
                 + "ORDER BY TotalSpent DESC";
    try {
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Map<String, Object> buyer = new HashMap<>();
            buyer.put("accountId", rs.getInt("AccountID"));
            buyer.put("fullName", rs.getString("FullName"));
            buyer.put("email", rs.getString("Email"));
            buyer.put("phoneNumber", rs.getString("PhoneNumber"));
            buyer.put("address", rs.getString("Address"));
            buyer.put("totalSpent", rs.getFloat("TotalSpent"));
            topBuyers.add(buyer);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return topBuyers;
}

}
