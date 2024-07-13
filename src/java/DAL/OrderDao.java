/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Account;
import Models.Item;
import Models.OrderCustomer;
import Models.OrderDetailCustomer;
import Models.OrderDetailGuest;
import Models.OrderGuest;
import Models.Orders;
import Models.Product;

//import Models.Status;
import SendEmail.SendEmail;

import Models.StatusOrder;

import Models.StatusOrder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                StatusOrder status = new StatusOrder();
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
                StatusOrder status = new StatusOrder();
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
        String query = "SELECT oc.OrderCID, oc.AccountID, oc.TotalPrice, oc.Date, oc.StatusID, s.StatusName, "
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
                StatusOrder status = new StatusOrder();
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
        String query = "SELECT og.OrderGID,og.FullName,og.Email,"
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
                StatusOrder status = new StatusOrder();
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
        String query = "SELECT p.ProductID, p.Name, p.Price, p.imgProduct, \n"
                + "       (COALESCE(SUM(odc.Quantity), 0) + COALESCE(SUM(odg.Quantity), 0)) AS TotalQuantity \n"
                + "FROM Product p \n"
                + "LEFT JOIN OrderDetailCustomer odc ON p.ProductID = odc.ProductID \n"
                + "LEFT JOIN OrderDetailGuest odg ON p.ProductID = odg.ProductID \n"
                + "GROUP BY p.ProductID, p.Name, p.Price, p.imgProduct\n"
                + "HAVING (COALESCE(SUM(odc.Quantity), 0) + COALESCE(SUM(odg.Quantity), 0)) > 0\n"
                + "ORDER BY TotalQuantity DESC;";
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

//    public List<Map<String, Object>> getTopBuyers() {
//        List<Map<String, Object>> topBuyers = new ArrayList<>();
//        String query = "SELECT Top 5 a.AccountID, a.FullName, a.Email, a.PhoneNumber, a.Address, SUM(oc.TotalPrice) AS TotalSpent "
//                + "FROM Account a "
//                + "JOIN OrderCustomer oc ON a.AccountID = oc.AccountID "
//                + "GROUP BY a.AccountID, a.FullName, a.Email, a.PhoneNumber, a.Address "
//                + "ORDER BY TotalSpent DESC";
//        try {
//            PreparedStatement ps = connection.prepareStatement(query);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                Map<String, Object> buyer = new HashMap<>();
//                buyer.put("accountId", rs.getInt("AccountID"));
//                buyer.put("fullName", rs.getString("FullName"));
//                buyer.put("email", rs.getString("Email"));
//                buyer.put("phoneNumber", rs.getString("PhoneNumber"));
//                buyer.put("address", rs.getString("Address"));
//                buyer.put("totalSpent", rs.getFloat("TotalSpent"));
//                topBuyers.add(buyer);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return topBuyers;
//    }
    public List<OrderCustomer> getOrdersByStatusForCustomers(String statusId) {
        List<OrderCustomer> orders = new ArrayList<>();
        String query = "SELECT oc.OrderCID, oc.AccountID, oc.TotalPrice, oc.Date, oc.StatusID, s.StatusName, a.FullName, a.Email, a.PhoneNumber, a.Address "
                + "FROM OrderCustomer oc "
                + "JOIN StatusOrder s ON oc.StatusID = s.StatusID "
                + "JOIN Account a ON oc.AccountID = a.AccountID "
                + "WHERE oc.StatusID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(statusId));
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
                StatusOrder status = new StatusOrder();
                status.setStatusId(rs.getInt("StatusID"));
                status.setStatusName(rs.getString("StatusName"));
                List<OrderDetailCustomer> orderDetails = getOrderDetailCustomers(orderCId);
                OrderCustomer order = new OrderCustomer(orderDetails, account, totalPrice, date, status);
                orders.add(order);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return orders;
    }

    public List<OrderGuest> getOrdersByStatusForGuests(String statusId) {
        List<OrderGuest> orders = new ArrayList<>();
        String query = "SELECT og.OrderGID, og.FullName, og.Email, og.PhoneNumber, og.Address, og.TotalPrice, og.Date, og.StatusID, s.StatusName "
                + "FROM OrderGuest og "
                + "JOIN StatusOrder s ON og.StatusID = s.StatusID "
                + "WHERE og.StatusID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(statusId));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderGId = rs.getInt("OrderGID");
                String fullName = rs.getString("FullName");
                String email = rs.getString("Email");
                String phoneNumber = rs.getString("PhoneNumber");
                String address = rs.getString("Address");
                float totalPrice = rs.getFloat("TotalPrice");
                Date date = rs.getDate("Date");
                StatusOrder status = new StatusOrder();
                status.setStatusId(rs.getInt("StatusID"));
                status.setStatusName(rs.getString("StatusName"));
                List<OrderDetailGuest> orderDetails = getOrderDetailGuests(orderGId);
                OrderGuest order = new OrderGuest(orderDetails, fullName, email, phoneNumber, address, totalPrice, date, status);
                orders.add(order);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return orders;
    }

    public int OrderCount() {
        int totalOrders = 0;
        String query = "SELECT SUM(TotalOrders) AS TotalOrders\n"
                + "FROM (\n"
                + "    SELECT COUNT(*) AS TotalOrders\n"
                + "    FROM OrderGuest\n"
                + "    \n"
                + "    UNION ALL\n"
                + "    \n"
                + "    SELECT COUNT(*) AS TotalOrders\n"
                + "    FROM OrderCustomer\n"
                + ") AS TotalOrdersCombined";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalOrders = rs.getInt("TotalOrders");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return totalOrders;
    }

    public List<Map<String, Object>> getTopBuyers() {
        List<Map<String, Object>> topBuyers = new ArrayList<>();
        String query = "SELECT a.AccountID, a.FullName, a.Email, a.PhoneNumber, a.Address, SUM(oc.TotalPrice) AS TotalSpent "
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

    public int AddOrderCustomer(int accID, int addressID, float totalPrice, int statusID, int paymentStatus) {
        String sql = "INSERT INTO OrderCustomer (AccountID,AddressID, TotalPrice, StatusID, PaymentStatus) values (?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, accID);
            ps.setInt(2, addressID);
            ps.setFloat(3, totalPrice);
            ps.setInt(4, statusID);
            ps.setInt(5, paymentStatus);
            int rowsInserted = ps.executeUpdate();
            int orderCID = -1;
            if (rowsInserted > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    orderCID = rs.getInt(1);
                }
            }
            ps.close();
            return orderCID;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void AddOrderCustomerDetails(int orderCID, List<Item> listItem) {
        String sql = "INSERT INTO OrderDetailCustomer(OrderCID, ProductID, Quantity, UnitPrice) values (?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            for (Item item : listItem) {
                ps.setInt(1, orderCID);
                ps.setInt(2, item.getProduct().getProductId());
                ps.setInt(3, item.getQuantity());
                ps.setDouble(4, item.getPrice());
                ps.execute();
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int AddOrderGuest(String fullname, String email, String phoneNumber, String address, Float totalPrice, int statusID, int paymentStatus) {
        String sql = "INSERT INTO OrderGuest(FullName, Email, PhoneNumber, Address, TotalPrice, StatusID, PaymentStatus) values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, fullname);
            ps.setString(2, email);
            ps.setString(3, phoneNumber);
            ps.setString(4, address);
            ps.setFloat(5, totalPrice);
            ps.setInt(6, statusID);
            ps.setInt(7, paymentStatus);
            int rowsInserted = ps.executeUpdate();
            int orderGID = -1;
            if (rowsInserted > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    orderGID = rs.getInt(1);
                }
            }
            ps.close();
            return orderGID;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void AddOrderGuestDetails(int orderGID, List<Item> listItem) {
        String sql = "INSERT INTO OrderDetailGuest(OrderGID, ProductID, Quantity, UnitPrice) values (?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            for (Item item : listItem) {
                ps.setInt(1, orderGID);
                ps.setInt(2, item.getProduct().getProductId());
                ps.setInt(3, item.getQuantity());
                ps.setDouble(4, item.getPrice());
                ps.execute();
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean AddOrderGuest() {

        return false;
    }

    public List<OrderCustomer> getOrderCustomersByAccountId(int accountId) {
        List<OrderCustomer> listOrderCustomers = new ArrayList<>();
        String query = "SELECT oc.OrderCID, oc.AccountID, a.FullName, a.Email,"
                + " a.Address, a.PhoneNumber, oc.TotalPrice, oc.Date, oc.StatusID, s.StatusName "
                + "FROM OrderCustomer oc "
                + "JOIN StatusOrder s ON s.StatusID = oc.StatusID "
                + "JOIN Account a ON a.AccountID = oc.AccountID "
                + "WHERE oc.AccountID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, accountId);
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
                StatusOrder status = new StatusOrder();
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

    public List<OrderCustomer> getOrderCustomersByAccountIdAndStatus(int accountId, int statusId) {
        List<OrderCustomer> listOrderCustomers = new ArrayList<>();
        String query = "SELECT oc.OrderCID, oc.AccountID, a.FullName, a.Email, "
                + "a.Address, a.PhoneNumber, oc.TotalPrice, oc.Date, oc.StatusID, s.StatusName "
                + "FROM OrderCustomer oc "
                + "JOIN StatusOrder s ON s.StatusID = oc.StatusID "
                + "JOIN Account a ON a.AccountID = oc.AccountID "
                + "WHERE oc.AccountID = ? AND oc.StatusID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, accountId);
            ps.setInt(2, statusId);
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
                StatusOrder status = new StatusOrder();
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

    public int getOrderCountByStatusForCustomers(int accountId, int statusId) {
        int orderCount = 0;
        String query = "SELECT COUNT(*) AS OrderCount "
                + "FROM OrderCustomer "
                + "WHERE AccountID = ? AND StatusID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, accountId);
            ps.setInt(2, statusId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                orderCount = rs.getInt("OrderCount");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return orderCount;
    }

    public int getAllOrderCountForCustomers(int accountId) {
        int orderCount = 0;
        String query = "SELECT COUNT(*) AS OrderCount "
                + "FROM OrderCustomer "
                + "WHERE AccountID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                orderCount = rs.getInt("OrderCount");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return orderCount;
    }

    public boolean cancelOrder(int orderId) {
        String query = "UPDATE OrderCustomer SET StatusID =5 WHERE OrderCID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, orderId);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateStatusById(int orderId, int status) {
        String sql = "  Update OrderCustomer\n"
                + "  set StatusID = ? \n"
                + "  where OrderCID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, orderId);
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Map<String, Integer> getStatusCounts(int accountId) {
        Map<String, Integer> statusCounts = new HashMap<>();
        String query = "SELECT StatusID, COUNT(*) as Count "
                + "FROM OrderCustomer "
                + "WHERE AccountID = ? "
                + "GROUP BY StatusID";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int statusId = rs.getInt("StatusID");
                int count = rs.getInt("Count");
                switch (statusId) {
                    case 1:
                        statusCounts.put("pending", count);
                        break;
                    case 2:
                        statusCounts.put("confirmed", count);
                        break;
                    case 3:
                        statusCounts.put("shipping", count);
                        break;
                    case 4:
                        statusCounts.put("completed", count);
                        break;
                    case 5:
                        statusCounts.put("canceled", count);
                        break;
                    default:
                        break;
                }
            }
            String allOrdersQuery = "SELECT COUNT(*) as Count FROM OrderCustomer WHERE AccountID = ?";
            ps = connection.prepareStatement(allOrdersQuery);
            ps.setInt(1, accountId);
            rs = ps.executeQuery();
            if (rs.next()) {
                statusCounts.put("all", rs.getInt("Count"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return statusCounts;
    }

    public OrderCustomer getOrderCustomerById(int orderId) {
        OrderCustomer orderCustomer = null;
        String query = "SELECT oc.OrderCID, oc.AccountID, oc.TotalPrice, oc.Date, oc.StatusID, s.StatusName, "
                + "oc.paymentStatus, "
                + "a.FullName, a.Email, a.PhoneNumber, a.Address "
                + "FROM OrderCustomer oc "
                + "JOIN StatusOrder s ON oc.StatusID = s.StatusID "
                + "JOIN Account a ON oc.AccountID = a.AccountID "
                + "WHERE oc.OrderCID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int orderCId = rs.getInt("OrderCID");
                Account account = new Account();
                account.setAccountId(rs.getInt("AccountID"));
                account.setFullName(rs.getString("FullName"));
                account.setEmail(rs.getString("Email"));
                account.setPhoneNumber(rs.getString("PhoneNumber"));
                account.setAddress(rs.getString("Address"));
                float totalPrice = rs.getFloat("TotalPrice");
                Date date = rs.getDate("Date");
                StatusOrder status = new StatusOrder();
                status.setStatusId(rs.getInt("StatusID"));
                status.setStatusName(rs.getString("StatusName"));
                int paymentStatus = rs.getInt("paymentStatus");
                List<OrderDetailCustomer> orderDetails = getOrderDetailCustomers(orderCId);
                orderCustomer = new OrderCustomer(orderDetails, account, totalPrice, date, status);
                orderCustomer.setPaymentStatus(paymentStatus);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return orderCustomer;
    }

    public int getTotalQuantityByOrderCId(int orderCId) {
        int totalQuantity = 0;
        String query = "SELECT SUM(Quantity) AS TotalQuantity "
                + "FROM OrderDetailCustomer "
                + "WHERE OrderCID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, orderCId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalQuantity = rs.getInt("TotalQuantity");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return totalQuantity;
    }

    public OrderGuest getOrderGuestByID(int orderGID) {
        String query = "  SELECT og.OrderGID,og.FullName, og.Email, og.PhoneNumber, og.Address,"
                + " og.TotalPrice, og.Date, og.StatusID, so.StatusName, og.PaymentStatus "
                + "FROM OrderGuest og JOIN StatusOrder so ON og.StatusID = so.StatusID "
                + "WHERE OrderGID=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, orderGID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int orderGId = rs.getInt(1);
                String fullName = rs.getString(2);
                String email = rs.getString(3);
                String phoneNumber = rs.getString(4);
                String address = rs.getString(5);
                float totalPrice = rs.getFloat(6);
                Date date = rs.getDate(7);
                StatusOrder status = new StatusOrder();
                status.setStatusId(rs.getInt(8));
                status.setStatusName(rs.getString(9));
                List<OrderDetailGuest> orderDetails = getOrderDetailGuests(orderGId);
                OrderGuest orderGuest = new OrderGuest(orderDetails, fullName, email, phoneNumber, address, totalPrice, date, status);
                return orderGuest;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Orders> getallOrder(int indexx) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        List<Orders> list = new ArrayList<>();

        String query = "SELECT \n"
                + "    RowNumber,\n"
                + "    OrderID,\n"
                + "    FullName,\n"
                + "    Email,\n"
                + "    PhoneNumber,\n"
                + "    Address,\n"
                + "    TotalPrice,\n"
                + "    Date,\n"
                + "    StatusID,\n"
                + "    PaymentStatus,\n"
                + "    AccountID\n"
                + "FROM (\n"
                + "    SELECT \n"
                + "        ROW_NUMBER() OVER (ORDER BY Date DESC) AS RowNumber,\n"
                + "        OrderID,\n"
                + "        FullName,\n"
                + "        Email,\n"
                + "        PhoneNumber,\n"
                + "        Address,\n"
                + "        TotalPrice,\n"
                + "        Date,\n"
                + "        StatusID,\n"
                + "        PaymentStatus,\n"
                + "        AccountID\n"
                + "    FROM (\n"
                + "        SELECT \n"
                + "            o.OrderCID AS OrderID,  \n"
                + "            a.FullName,\n"
                + "            a.Email,\n"
                + "            sa.PhoneNumber, \n"
                + "            sa.Address,\n"
                + "            o.TotalPrice,\n"
                + "            o.Date,\n"
                + "            o.StatusID,\n"
                + "            o.PaymentStatus,\n"
                + "            o.AccountID\n"
                + "        FROM \n"
                + "            [dbo].[OrderCustomer] o\n"
                + "        JOIN \n"
                + "            [ShopBook88].[dbo].[Account] a ON o.AccountID = a.AccountID\n"
                + "        JOIN \n"
                + "            [ShopBook88].[dbo].[ShippingAddress] sa ON o.AddressID = sa.AddressID\n"
                + "\n"
                + "        UNION ALL\n"
                + "\n"
                + "        SELECT \n"
                + "            og.OrderGID AS OrderID,\n"
                + "            og.FullName,\n"
                + "            og.Email,\n"
                + "            og.PhoneNumber,\n"
                + "            og.Address,\n"
                + "            og.TotalPrice,\n"
                + "            og.Date,\n"
                + "            og.StatusID,\n"
                + "            og.PaymentStatus,\n"
                + "            NULL AS AccountID\n"
                + "        FROM \n"
                + "            [dbo].[OrderGuest] og\n"
                + "    ) AS CombinedOrders\n"
                + ") AS NumberedOrders\n"
                + "ORDER BY RowNumber\n"
                + "OFFSET ? ROWS\n"
                + "FETCH NEXT 10 ROWS ONLY;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, (indexx - 1) * 10);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Orders order = new Orders();
                order.setStt(rs.getInt(1));
                order.setOrderID(rs.getInt(2));

                order.setFullName(rs.getString(3));
                order.setEmail(rs.getString(4));
                order.setPhoneNumber(rs.getString(5));
                order.setAddress(rs.getString(6));
                order.setTotalPrice(rs.getFloat(7));
                order.setDate(rs.getDate(8));
                order.setStatus(rs.getInt(9));
                order.setPaymentStatus(rs.getInt(10));
                order.setAccountID(rs.getInt(11));
                list.add(order);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;

    }

    public int getToralOrder() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        int totalOrders = 0;
        String query = "SELECT COUNT(*) AS TotalOrders\n"
                + "FROM (\n"
                + "    SELECT \n"
                + "        o.OrderCID AS OrderID,  \n"
                + "        a.FullName,\n"
                + "        a.Email,\n"
                + "        sa.PhoneNumber, \n"
                + "        sa.Address,\n"
                + "        o.TotalPrice,\n"
                + "        o.Date,\n"
                + "        o.StatusID,\n"
                + "        o.PaymentStatus,\n"
                + "        o.AccountID\n"
                + "    FROM \n"
                + "        [dbo].[OrderCustomer] o\n"
                + "    JOIN \n"
                + "        [ShopBook88].[dbo].[Account] a ON o.AccountID = a.AccountID\n"
                + "    JOIN \n"
                + "        [ShopBook88].[dbo].[ShippingAddress] sa ON o.AddressID = sa.AddressID\n"
                + "\n"
                + "    UNION ALL\n"
                + "\n"
                + "    SELECT \n"
                + "        og.OrderGID AS OrderID,\n"
                + "        og.FullName,\n"
                + "        og.Email,\n"
                + "        og.PhoneNumber,\n"
                + "        og.Address,\n"
                + "        og.TotalPrice,\n"
                + "        og.Date,\n"
                + "        og.StatusID,\n"
                + "        og.PaymentStatus,\n"
                + "        NULL AS AccountID\n"
                + "    FROM \n"
                + "        [dbo].[OrderGuest] og\n"
                + ") AS CombinedOrders;";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalOrders = rs.getInt("TotalOrders");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return totalOrders;

    }

    public Orders getOrderCusById(int orderGID) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String query = "  SELECT \n"
                + "    o.OrderCID AS OrderID,\n"
                + "    a.FullName,\n"
                + "    a.Email,\n"
                + "    sa.PhoneNumber, \n"
                + "    sa.Address,\n"
                + "    o.TotalPrice,\n"
                + "    o.Date,\n"
                + "    o.StatusID,\n"
                + "    o.PaymentStatus,\n"
                + "    o.AccountID\n"
                + "FROM \n"
                + "    [dbo].[OrderCustomer] o\n"
                + "JOIN \n"
                + "    [ShopBook88].[dbo].[Account] a ON o.AccountID = a.AccountID\n"
                + "JOIN \n"
                + "    [ShopBook88].[dbo].[ShippingAddress] sa ON o.AddressID = sa.AddressID\n"
                + "WHERE \n"
                + "    o.OrderCID = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, orderGID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Orders order = new Orders();

                order.setOrderID(rs.getInt(1));

                order.setFullName(rs.getString(2));
                order.setEmail(rs.getString(3));
                order.setPhoneNumber(rs.getString(4));
                order.setAddress(rs.getString(5));
                order.setTotalPrice(rs.getFloat(6));
                order.setDate(rs.getDate(7));
                order.setStatus(rs.getInt(8));
                order.setPaymentStatus(rs.getInt(9));
                order.setAccountID(rs.getInt(10));
                return order;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;

    }

    public Orders getOrderGetByID(int orderGID) {
        String query = "  SELECT \n"
                + "    og.OrderGID AS OrderID,\n"
                + "    og.FullName,\n"
                + "    og.Email,\n"
                + "    og.PhoneNumber,\n"
                + "    og.Address,\n"
                + "    og.TotalPrice,\n"
                + "    og.Date,\n"
                + "    og.StatusID,\n"
                + "    og.PaymentStatus,\n"
                + "    NULL AS AccountID\n"
                + "FROM \n"
                + "    [dbo].[OrderGuest] og\n"
                + "WHERE \n"
                + "    og.OrderGID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, orderGID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Orders order = new Orders();
                order.setOrderID(rs.getInt(1));

                order.setFullName(rs.getString(2));
                order.setEmail(rs.getString(3));
                order.setPhoneNumber(rs.getString(4));
                order.setAddress(rs.getString(5));
                order.setTotalPrice(rs.getFloat(6));
                order.setDate(rs.getDate(7));
                order.setStatus(rs.getInt(8));
                order.setPaymentStatus(rs.getInt(9));
                order.setAccountID(rs.getInt(10));
                return order;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void updatePaymentSuccess(String type, int orderID) {
        String tableName = "";
        String columnName = "";
        if ("customer".equals(type)) {
            tableName = "OrderCustomer";
            columnName = "OrderCID";
        } else if ("guest".equalsIgnoreCase(type)) {
            tableName = "OrderGuest";
            columnName = "OrderGID";
        }

        String sql = "UPDATE " + tableName + "\n"
                + "SET PaymentStatus = 1\n"
                + "WHERE " + columnName + " = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderID);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateOrderCustomerStatus(int orderCID, int newStatusID) {
        String query = "UPDATE OrderCustomer SET StatusID = ? WHERE OrderCID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, newStatusID);
            ps.setInt(2, orderCID);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;

        }

    }
     public StatusOrder getStatusOrderById(int orderId) {
        StatusOrder statusOrder = null;
        String query = "SELECT s.StatusID, s.StatusName FROM StatusOrder s "
                     + "JOIN OrderCustomer oc ON s.StatusID = oc.StatusID "
                     + "WHERE oc.OrderCID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int statusId = rs.getInt("StatusID");
                String statusName = rs.getString("StatusName");
                statusOrder = new StatusOrder(statusId, statusName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statusOrder;
    }
       public static void main(String[] args) {
        OrderDao orderDao = new OrderDao();
        int testOrderId = 8;
        StatusOrder statusOrder = orderDao.getStatusOrderById(testOrderId);
        if (statusOrder != null) {
            System.out.println("Status ID: " + statusOrder.getStatusId());
            System.out.println("Status Name: " + statusOrder.getStatusName());
        } else {
            System.out.println("No status found for Order ID: " + testOrderId);
        }
    }



    public boolean updateOrderStaff(int orderID, int aId) {
        String tableName = aId == 0 ? "OrderGuest" : "OrderCustomer";
        String param = aId == 0 ? "OrderGID" : "OrderCID";
        String query = "UPDATE " + tableName + " SET StatusID = 2 WHERE " + param + " = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, orderID);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;

        }

    }

    public List<OrderDetailGuest> getAllByOrderId(int orderID, int aId) {
        List<OrderDetailGuest> list = new ArrayList<>();
        String tableName = aId == 0 ? "OrderDetailGuest" : "OrderDetailCustomer";
        String param = aId == 0 ? "OrderGID" : "OrderCID";
        String query = "Select * from " + tableName + " WHERE " + param + " = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetailGuest od = new OrderDetailGuest();
                od.setOrderGId(rs.getInt(1));
                od.setProductId(rs.getInt("productId"));
                od.setQuantity(rs.getInt("Quantity"));
                od.setUnitPrice(rs.getFloat(4));
                list.add(od);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public boolean updateProductQuantityStaff(int productId, int quantity, String toanTu) {
        String query = "update Product \n"
                + "set Quantity = (select Quantity from Product where ProductID = ?) " + toanTu + " ? \n"
                + "where ProductID = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, productId);
            ps.setInt(2, quantity);
            ps.setInt(3, productId);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public String getEmailByOrderId(int orderID, int aId) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        String query;
        if (aId == 0) {
            query = "Select * from  OrderGuest  WHERE  OrderGID = ?";
        } else {
            query = "Select * from  Account  WHERE  AccountID = ?";
        }

        try {

            PreparedStatement ps = connection.prepareStatement(query);
            if (aId == 0) {
                ps.setInt(1, orderID);
            } else {
                ps.setInt(1, aId);
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("email");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public void updateOrder(Orders orders, String status) {

        String query;
        if (orders.getAccountID() == 0) {
            query = "UPDATE [dbo].[OrderGuest]\n"
                    + "   SET [FullName] = ?\n"
                    + "      ,[Email] = ?\n"
                    + "      ,[PhoneNumber] = ?\n"
                    + "      ,[Address] = ?\n"
                    + "      ,[TotalPrice] = ?\n"
                    + "      ,[StatusID] = ?\n"
                    + "      ,[PaymentStatus] = ?\n"
                    + " WHERE OrderGID = ?";
        } else {
            query = "UPDATE [dbo].[OrderCustomer]\n"
                    + "   SET \n"
                    + "      [StatusID] = ?\n"
                    + "      ,[PaymentStatus] = ?\n"
                    + " WHERE OrderCID=?";
        }

        try {

            PreparedStatement ps = connection.prepareStatement(query);

            if (orders.getAccountID() == 0) {

                ps.setString(1, orders.getFullName());
                ps.setString(2, orders.getEmail());
                ps.setString(3, orders.getPhoneNumber());
                ps.setString(4, orders.getAddress());
                ps.setFloat(5, orders.getTotalPrice());
                ps.setInt(6, orders.getStatus());

                ps.setInt(7, orders.getPaymentStatus());
                ps.setInt(8, orders.getOrderID());

            } else {
                ps.setInt(1, orders.getStatus());
                ps.setInt(2, orders.getPaymentStatus());
                ps.setInt(3, orders.getOrderID());
            }
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}
