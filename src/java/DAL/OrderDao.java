/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Account;
import Models.CategorySales;
import Models.Item;
import Models.OrderCustomer;
import Models.OrderDetailCustomer;
import Models.OrderDetailGuest;
import Models.OrderGuest;
import Models.OrderStatus;
import Models.Orders;
import Models.Product;
import Models.ProductSales;

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

//    public List<Product> getMostPurchasedProducts() {
//        List<Product> products = new ArrayList<>();
//        String query = "SELECT p.ProductID, p.Name, p.Price, p.imgProduct, \n"
//                + "       (COALESCE(SUM(odc.Quantity), 0) + COALESCE(SUM(odg.Quantity), 0)) AS TotalQuantity \n"
//                + "FROM Product p \n"
//                + "LEFT JOIN OrderDetailCustomer odc ON p.ProductID = odc.ProductID \n"
//                + "LEFT JOIN OrderDetailGuest odg ON p.ProductID = odg.ProductID \n"
//                + "GROUP BY p.ProductID, p.Name, p.Price, p.imgProduct\n"
//                + "HAVING (COALESCE(SUM(odc.Quantity), 0) + COALESCE(SUM(odg.Quantity), 0)) > 0\n"
//                + "ORDER BY TotalQuantity DESC;";
//        try {
//            PreparedStatement ps = connection.prepareStatement(query);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                Product product = new Product();
//                product.setProductId(rs.getInt("ProductID"));
//                product.setName(rs.getString("Name"));
//                product.setPrice(rs.getFloat("Price"));
//                product.setImgProduct(rs.getString("imgProduct"));
//                product.setQuantity(rs.getInt("TotalQuantity"));
//                products.add(product);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return products;
//    }
    public int getQuantitySoldByCategoryId(int categoryId) {
        int quantitySold = 0;
        String query = "SELECT COALESCE(SUM(total_quantity), 0) AS TotalQuantity "
                + "FROM ("
                + "    SELECT SUM(odc.Quantity) AS total_quantity "
                + "    FROM OrderDetailCustomer odc "
                + "    INNER JOIN Product p ON odc.ProductID = p.ProductID "
                + "    WHERE p.CategoryID = ? "
                + "    UNION ALL "
                + "    SELECT SUM(odg.Quantity) AS total_quantity "
                + "    FROM OrderDetailGuest odg "
                + "    INNER JOIN Product p ON odg.ProductID = p.ProductID "
                + "    WHERE p.CategoryID = ?"
                + ") AS combined_quantities";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, categoryId);
            ps.setInt(2, categoryId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    quantitySold = rs.getInt("TotalQuantity");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quantitySold;
    }

    public List<CategorySales> getCategorySales() {
        List<CategorySales> categorySalesList = new ArrayList<>();
        String query = "SELECT c.CategoryID, c.CategoryName, COALESCE(SUM(odc.Quantity), 0) + COALESCE(SUM(odg.Quantity), 0) AS TotalQuantity "
                + "FROM Product p "
                + "JOIN Category c ON p.CategoryID = c.CategoryID "
                + "LEFT JOIN OrderDetailCustomer odc ON p.ProductID = odc.ProductID "
                + "LEFT JOIN OrderDetailGuest odg ON p.ProductID = odg.ProductID "
                + "GROUP BY c.CategoryID, c.CategoryName";

        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int categoryId = rs.getInt("categoryId");
                String categoryName = rs.getString("categoryName");
                int totalQuantitySold = rs.getInt("TotalQuantity");

                CategorySales categorySales = new CategorySales(categoryId, categoryName, totalQuantitySold);
                categorySalesList.add(categorySales);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categorySalesList;
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

    public int addOrderCustomer(int accID, int addressID, float totalPrice, int statusID, int paymentStatus, List<Item> listItem) {
        String sqlOrder = "INSERT INTO OrderCustomer (AccountID, AddressID, TotalPrice, StatusID, PaymentStatus) VALUES (?,?,?,?,?)";
        String sqlOrderDetail = "INSERT INTO OrderDetailCustomer (OrderCID, ProductID, Quantity, UnitPrice) VALUES (?,?,?,?)";
        String sqlCheckStock = "SELECT Quantity FROM Product WHERE ProductID = ?";

        PreparedStatement psOrder = null;
        PreparedStatement psOrderDetail = null;
        PreparedStatement psCheckStock = null;
        ResultSet rs = null;

        try {
            connection.setAutoCommit(false);

            // Insert into OrderCustomer
            psOrder = connection.prepareStatement(sqlOrder, PreparedStatement.RETURN_GENERATED_KEYS);
            psOrder.setInt(1, accID);
            psOrder.setInt(2, addressID);
            psOrder.setFloat(3, totalPrice);
            psOrder.setInt(4, statusID);
            psOrder.setInt(5, paymentStatus);
            int rowsInserted = psOrder.executeUpdate();

            int orderCID = -1;
            if (rowsInserted > 0) {
                rs = psOrder.getGeneratedKeys();
                if (rs.next()) {
                    orderCID = rs.getInt(1);
                }
            }

            // Check stock and insert into OrderDetailCustomer
            psOrderDetail = connection.prepareStatement(sqlOrderDetail);
            psCheckStock = connection.prepareStatement(sqlCheckStock);

            for (Item item : listItem) {
                // Check stock availability
                psCheckStock.setInt(1, item.getProduct().getProductId());
                rs = psCheckStock.executeQuery();

                if (rs.next()) {
                    int availableStock = rs.getInt("Quantity");
                    if (availableStock < item.getQuantity()) {
                        connection.rollback();
                        return -1; // Not enough stock
                    }
                } else {
                    connection.rollback();
                    return -1; // Product not found
                }

                // Insert order details
                psOrderDetail.setInt(1, orderCID);
                psOrderDetail.setInt(2, item.getProduct().getProductId());
                psOrderDetail.setInt(3, item.getQuantity());
                psOrderDetail.setDouble(4, item.getPrice());
                psOrderDetail.executeUpdate();
            }

            connection.commit();
            return orderCID;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return -1;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (psOrder != null) {
                    psOrder.close();
                }
                if (psOrderDetail != null) {
                    psOrderDetail.close();
                }
                if (psCheckStock != null) {
                    psCheckStock.close();
                }
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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

    public int addOrderGuestWithDetail(String fullname, String email, String phoneNumber, String address, Float totalPrice, int statusID, int paymentStatus, List<Item> listItem) {
        String sqlOrder = "INSERT INTO OrderGuest(FullName, Email, PhoneNumber, Address, TotalPrice, StatusID, PaymentStatus) values (?,?,?,?,?,?,?)";
        String sqlOrderDetail = "INSERT INTO OrderDetailGuest(OrderGID, ProductID, Quantity, UnitPrice) values (?,?,?,?)";
        String sqlCheckStock = "SELECT Quantity FROM Product WHERE ProductID = ?";

        PreparedStatement psOrder = null;
        PreparedStatement psOrderDetail = null;
        PreparedStatement psCheckStock = null;
        ResultSet rs = null;

        try {
            connection.setAutoCommit(false);

            // Insert into OrderGuest
            psOrder = connection.prepareStatement(sqlOrder, PreparedStatement.RETURN_GENERATED_KEYS);
            psOrder.setString(1, fullname);
            psOrder.setString(2, email);
            psOrder.setString(3, phoneNumber);
            psOrder.setString(4, address);
            psOrder.setFloat(5, totalPrice);
            psOrder.setInt(6, statusID);
            psOrder.setInt(7, paymentStatus);
            int rowsInserted = psOrder.executeUpdate();

            int orderGID = -1;
            if (rowsInserted > 0) {
                rs = psOrder.getGeneratedKeys();
                if (rs.next()) {
                    orderGID = rs.getInt(1);
                }
            }

            // Check stock and insert into OrderDetailGuest
            psOrderDetail = connection.prepareStatement(sqlOrderDetail);
            psCheckStock = connection.prepareStatement(sqlCheckStock);

            for (Item item : listItem) {
                // Check stock availability
                psCheckStock.setInt(1, item.getProduct().getProductId());
                rs = psCheckStock.executeQuery();

                if (rs.next()) {
                    int availableStock = rs.getInt("Quantity");
                    if (availableStock < item.getQuantity()) {
                        connection.rollback();
                        return -1; // Not enough stock
                    }
                } else {
                    connection.rollback();
                    return -1; // Product not found
                }

                // Insert order details
                psOrderDetail.setInt(1, orderGID);
                psOrderDetail.setInt(2, item.getProduct().getProductId());
                psOrderDetail.setInt(3, item.getQuantity());
                psOrderDetail.setDouble(4, item.getPrice());
                psOrderDetail.executeUpdate();
            }

            connection.commit();
            return orderGID;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return -1;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (psOrder != null) {
                    psOrder.close();
                }
                if (psOrderDetail != null) {
                    psOrderDetail.close();
                }
                if (psCheckStock != null) {
                    psCheckStock.close();
                }
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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

    public List<Orders> getOrderByStatus(int index, String status) {
        List<Orders> list = new ArrayList<>();
        String query = "SELECT RowNumber,OrderID,FullName,Email,PhoneNumber,Address,TotalPrice,Date,StatusID,PaymentStatus,AccountID\n"
                + "FROM (\n"
                + "SELECT \n"
                + "ROW_NUMBER() OVER (ORDER BY Date DESC) AS RowNumber, OrderID,FullName,Email,PhoneNumber,Address,TotalPrice,Date,StatusID, PaymentStatus,AccountID\n"
                + "FROM (\n"
                + "SELECT o.OrderCID AS OrderID,a.FullName, a.Email,sa.PhoneNumber, sa.Address, o.TotalPrice,o.Date,o.StatusID,o.PaymentStatus,o.AccountID\n"
                + "FROM [OrderCustomer] o\n"
                + "JOIN [Account] a ON o.AccountID = a.AccountID\n"
                + "JOIN [ShopBook88].[dbo].[ShippingAddress] sa ON o.AddressID = sa.AddressID\n"
                + "WHERE  o.StatusID = ?\n"
                + "UNION ALL\n"
                + "SELECT og.OrderGID AS OrderID,og.FullName,og.Email,og.PhoneNumber,og.Address,og.TotalPrice,og.Date,og.StatusID,og.PaymentStatus,\n"
                + "NULL AS AccountID\n"
                + "FROM [dbo].[OrderGuest] og\n"
                + "WHERE og.StatusID = ?\n"
                + ") AS CombinedOrders\n"
                + ") AS NumberedOrders\n"
                + "ORDER BY RowNumber\n"
                + "OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY;";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, Integer.parseInt(status));
            ps.setInt(2, Integer.parseInt(status));
            ps.setInt(3, (index - 1) * 5);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Orders order = new Orders();
                    order.setStt(rs.getInt("RowNumber"));
                    order.setOrderID(rs.getInt("OrderID"));
                    order.setFullName(rs.getString("FullName"));
                    order.setEmail(rs.getString("Email"));
                    order.setPhoneNumber(rs.getString("PhoneNumber"));
                    order.setAddress(rs.getString("Address"));
                    order.setTotalPrice(rs.getFloat("TotalPrice"));
                    order.setDate(rs.getDate("Date"));
                    order.setStatus(rs.getInt("StatusID"));
                    order.setPaymentStatus(rs.getInt("PaymentStatus"));
                    order.setAccountID(rs.getInt("AccountID"));
                    list.add(order);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public int getTotalOrdersByStatus(String statusId) {
        int totalOrders = 0;
        String query = "SELECT COUNT(*) AS TotalOrders\n"
                + "FROM (\n"
                + "SELECT  o.OrderCID AS OrderID,  a.FullName, a.Email, sa.PhoneNumber, sa.Address, "
                + "o.TotalPrice, o.Date, o.StatusID, o.PaymentStatus, o.AccountID\n"
                + "FROM [OrderCustomer] o\n"
                + "JOIN [Account] a ON o.AccountID = a.AccountID\n"
                + "JOIN [ShippingAddress] sa ON o.AddressID = sa.AddressID\n"
                + "WHERE o.StatusID = ?\n"
                + "UNION ALL\n"
                + "SELECT  og.OrderGID AS OrderID, og.FullName, og.Email, og.PhoneNumber, "
                + "og.Address, og.TotalPrice, og.Date,og.StatusID,og.PaymentStatus,\n"
                + "NULL AS AccountID\n"
                + "FROM[OrderGuest] og\n"
                + "WHERE og.StatusID = ?\n"
                + ") AS CombinedOrders";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(statusId));
            ps.setInt(2, Integer.parseInt(statusId));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalOrders = rs.getInt("TotalOrders");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return totalOrders;
    }

    public List<Orders> getOrderNameByStatus(int index, String status, String searchName) {
        List<Orders> list = new ArrayList<>();
        String query = "SELECT RowNumber, OrderID, FullName, Email, PhoneNumber, Address, TotalPrice, Date, StatusID, PaymentStatus, AccountID\n"
                + "FROM (\n"
                + "    SELECT \n"
                + "    ROW_NUMBER() OVER (ORDER BY Date DESC) AS RowNumber, OrderID, FullName, Email, PhoneNumber, Address, TotalPrice, Date, StatusID, PaymentStatus, AccountID\n"
                + "    FROM (\n"
                + "        SELECT o.OrderCID AS OrderID, a.FullName, a.Email, sa.PhoneNumber, sa.Address, o.TotalPrice, o.Date, o.StatusID, o.PaymentStatus, o.AccountID\n"
                + "        FROM [OrderCustomer] o\n"
                + "        JOIN [Account] a ON o.AccountID = a.AccountID\n"
                + "        JOIN [ShopBook88].[dbo].[ShippingAddress] sa ON o.AddressID = sa.AddressID\n"
                + "        WHERE o.StatusID = ? AND (a.FullName LIKE ?)\n"
                + "        UNION ALL\n"
                + "        SELECT og.OrderGID AS OrderID, og.FullName, og.Email, og.PhoneNumber, og.Address, og.TotalPrice, og.Date, og.StatusID, og.PaymentStatus, NULL AS AccountID\n"
                + "        FROM [dbo].[OrderGuest] og\n"
                + "        WHERE og.StatusID = ? AND (og.FullName LIKE ?)\n"
                + "    ) AS CombinedOrders\n"
                + ") AS NumberedOrders\n"
                + "ORDER BY RowNumber\n"
                + "OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY;";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, Integer.parseInt(status));
            ps.setString(2, "%" + searchName + "%");
            ps.setInt(3, Integer.parseInt(status));
            ps.setString(4, "%" + searchName + "%");
            ps.setInt(5, (index - 1) * 5);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Orders order = new Orders();
                    order.setStt(rs.getInt("RowNumber"));
                    order.setOrderID(rs.getInt("OrderID"));
                    order.setFullName(rs.getString("FullName"));
                    order.setEmail(rs.getString("Email"));
                    order.setPhoneNumber(rs.getString("PhoneNumber"));
                    order.setAddress(rs.getString("Address"));
                    order.setTotalPrice(rs.getFloat("TotalPrice"));
                    order.setDate(rs.getDate("Date"));
                    order.setStatus(rs.getInt("StatusID"));
                    order.setPaymentStatus(rs.getInt("PaymentStatus"));
                    order.setAccountID(rs.getInt("AccountID"));
                    list.add(order);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public int getTotalOrdersName(String statusId, String searchName) {
        int totalOrders = 0;
        String query = "SELECT COUNT(*) AS TotalOrders\n"
                + "FROM (\n"
                + "    SELECT  o.OrderCID AS OrderID,  a.FullName, a.Email, sa.PhoneNumber, sa.Address, "
                + "    o.TotalPrice, o.Date, o.StatusID, o.PaymentStatus, o.AccountID\n"
                + "    FROM [OrderCustomer] o\n"
                + "    JOIN [Account] a ON o.AccountID = a.AccountID\n"
                + "    JOIN [ShippingAddress] sa ON o.AddressID = sa.AddressID\n"
                + "    WHERE o.StatusID = ? AND (a.FullName LIKE ?)\n"
                + "    UNION ALL\n"
                + "    SELECT  og.OrderGID AS OrderID, og.FullName, og.Email, og.PhoneNumber, "
                + "    og.Address, og.TotalPrice, og.Date, og.StatusID, og.PaymentStatus,\n"
                + "    NULL AS AccountID\n"
                + "    FROM [OrderGuest] og\n"
                + "    WHERE og.StatusID = ? AND (og.FullName LIKE ?)\n"
                + ") AS CombinedOrders";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(statusId));
            ps.setString(2, "%" + searchName + "%");
            ps.setInt(3, Integer.parseInt(statusId));
            ps.setString(4, "%" + searchName + "%");

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalOrders = rs.getInt("TotalOrders");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return totalOrders;
    }

    public List<Orders> getOrderByStatusSorted(int index, String status, String sortBy, String sortDirection) {
        List<Orders> list = new ArrayList<>();

        String query = "SELECT RowNumber, OrderID, FullName, Email, PhoneNumber, Address, TotalPrice, Date, StatusID, PaymentStatus, AccountID\n"
                + "FROM (\n"
                + "    SELECT \n"
                + "        ROW_NUMBER() OVER (ORDER BY " + sortBy + " " + sortDirection + ") AS RowNumber, OrderID, FullName, Email, PhoneNumber, Address, TotalPrice, Date, StatusID, PaymentStatus, AccountID\n"
                + "    FROM (\n"
                + "        SELECT o.OrderCID AS OrderID, a.FullName, a.Email, sa.PhoneNumber, sa.Address, o.TotalPrice, o.Date, o.StatusID, o.PaymentStatus, o.AccountID\n"
                + "        FROM [OrderCustomer] o\n"
                + "        JOIN [Account] a ON o.AccountID = a.AccountID\n"
                + "        JOIN [ShopBook88].[dbo].[ShippingAddress] sa ON o.AddressID = sa.AddressID\n"
                + "        WHERE o.StatusID = ?\n"
                + "        UNION ALL\n"
                + "        SELECT og.OrderGID AS OrderID, og.FullName, og.Email, og.PhoneNumber, og.Address, og.TotalPrice, og.Date, og.StatusID, og.PaymentStatus,\n"
                + "            NULL AS AccountID\n"
                + "        FROM [dbo].[OrderGuest] og\n"
                + "        WHERE og.StatusID = ?\n"
                + "    ) AS CombinedOrders\n"
                + ") AS NumberedOrders\n"
                + "ORDER BY RowNumber\n"
                + "OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY;";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, Integer.parseInt(status));
            ps.setInt(2, Integer.parseInt(status));
            ps.setInt(3, (index - 1) * 5);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Orders order = new Orders();
                    order.setStt(rs.getInt("RowNumber"));
                    order.setOrderID(rs.getInt("OrderID"));
                    order.setFullName(rs.getString("FullName"));
                    order.setEmail(rs.getString("Email"));
                    order.setPhoneNumber(rs.getString("PhoneNumber"));
                    order.setAddress(rs.getString("Address"));
                    order.setTotalPrice(rs.getFloat("TotalPrice"));
                    order.setDate(rs.getDate("Date"));
                    order.setStatus(rs.getInt("StatusID"));
                    order.setPaymentStatus(rs.getInt("PaymentStatus"));
                    order.setAccountID(rs.getInt("AccountID"));
                    list.add(order);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public List<Orders> getOrder() {
        List<Orders> list = new ArrayList<>();
        String query = "SELECT RowNumber, OrderID, FullName, Email, PhoneNumber, Address, TotalPrice, Date, StatusID, PaymentStatus, AccountID\n"
                + "FROM (\n"
                + "    SELECT ROW_NUMBER() OVER (ORDER BY Date DESC) AS RowNumber, OrderID, FullName, Email, PhoneNumber, Address, TotalPrice, Date, StatusID, PaymentStatus, AccountID\n"
                + "    FROM (\n"
                + "        SELECT o.OrderCID AS OrderID, a.FullName, a.Email, sa.PhoneNumber, sa.Address, o.TotalPrice, o.Date, o.StatusID, o.PaymentStatus, o.AccountID\n"
                + "        FROM [OrderCustomer] o\n"
                + "        JOIN [Account] a ON o.AccountID = a.AccountID\n"
                + "        JOIN [ShopBook88].[dbo].[ShippingAddress] sa ON o.AddressID = sa.AddressID\n"
                + "        UNION ALL\n"
                + "        SELECT og.OrderGID AS OrderID, og.FullName, og.Email, og.PhoneNumber, og.Address, og.TotalPrice, og.Date, og.StatusID, og.PaymentStatus,\n"
                + "        NULL AS AccountID\n"
                + "        FROM [dbo].[OrderGuest] og\n"
                + "    ) AS CombinedOrders\n"
                + ") AS NumberedOrders\n"
                + "ORDER BY RowNumber";

        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Orders order = new Orders();
                order.setStt(rs.getInt("RowNumber"));
                order.setOrderID(rs.getInt("OrderID"));
                order.setFullName(rs.getString("FullName"));
                order.setEmail(rs.getString("Email"));
                order.setPhoneNumber(rs.getString("PhoneNumber"));
                order.setAddress(rs.getString("Address"));
                order.setTotalPrice(rs.getFloat("TotalPrice"));
                order.setDate(rs.getDate("Date"));
                order.setStatus(rs.getInt("StatusID"));
                order.setPaymentStatus(rs.getInt("PaymentStatus"));
                order.setAccountID(rs.getInt("AccountID"));
                list.add(order);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public List<OrderStatus> getOrderStatusCounts() {
        List<OrderStatus> orderStatusCounts = new ArrayList<>();

        String query = "SELECT so.StatusID, so.StatusName, COALESCE(o.TotalOrderCount, 0) AS TotalOrderCount\n"
                + "FROM StatusOrder so\n"
                + "LEFT JOIN (\n"
                + "    SELECT StatusID, SUM(OrderCount) AS TotalOrderCount\n"
                + "    FROM (\n"
                + "        SELECT StatusID, 'OrderCustomer' AS TableName, COUNT(*) AS OrderCount FROM OrderCustomer GROUP BY StatusID\n"
                + "        UNION ALL\n"
                + "        SELECT StatusID, 'OrderGuest' AS TableName, COUNT(*) AS OrderCount FROM OrderGuest GROUP BY StatusID\n"
                + "    ) AS o\n"
                + "    WHERE o.StatusID IN (?, ?, ?, ?, ?)\n"
                + "    GROUP BY o.StatusID\n"
                + ") AS o ON so.StatusID = o.StatusID\n"
                + "ORDER BY so.StatusID;";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, 1);
            ps.setInt(2, 2);
            ps.setInt(3, 3);
            ps.setInt(4, 4);
            ps.setInt(5, 5);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int statusId = rs.getInt("StatusID");
                String statusName = rs.getString("StatusName");
                int count = rs.getInt("TotalOrderCount");
                orderStatusCounts.add(new OrderStatus(statusId, statusName, count));
            }

            rs.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return orderStatusCounts;
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

    public boolean updateProductQuantity(int productId, int quantity, String toanTu) {
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

    public List<Orders> getallOrderbyStatus(int indexx, String status) {
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
                + "        WHERE\n"
                + "            o.StatusID = ?\n"
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
                + "        WHERE\n"
                + "            og.StatusID = ?\n"
                + "    ) AS CombinedOrders\n"
                + ") AS NumberedOrders\n"
                + "ORDER BY RowNumber\n"
                + "\n"
                + "\n"
                + "\n"
                + "OFFSET ? ROWS\n"
                + "FETCH NEXT 10 ROWS ONLY;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, status);
            ps.setString(2, status);

            ps.setInt(3, (indexx - 1) * 10);
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

    public int getToralOrderByStatus(String status) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        int totalOrders = 0;
        String query = "SELECT COUNT(*) AS TotalOrders\n"
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
                + "        WHERE\n"
                + "            o.StatusID = ?\n"
                + "        \n"
                + "        UNION ALL\n"
                + "        \n"
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
                + "        WHERE\n"
                + "            og.StatusID = ?\n"
                + "    ) AS CombinedOrders\n"
                + ") AS NumberedOrders;";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, status);
            ps.setString(2, status);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalOrders = rs.getInt("TotalOrders");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return totalOrders;

    }

    public int getToralOrderbysearch(String text) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        int totalOrders = 0;
        String query = "SELECT COUNT(*) AS TotalOrders\n"
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
                + "        WHERE\n"
                + "            a.FullName like ?\n"
                + "        \n"
                + "        UNION ALL\n"
                + "        \n"
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
                + "        WHERE\n"
                + "           og.FullName like ?\n"
                + "    ) AS CombinedOrders\n"
                + ") AS NumberedOrders;";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + text + "%");
            ps.setString(2, "%" + text + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalOrders = rs.getInt("TotalOrders");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return totalOrders;

    }

    public List<Orders> getallOrderbyText(int indexx, String text) {
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
                + "        WHERE\n"
                + "               a.FullName like ?\n"
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
                + "        WHERE\n"
                + "                 og.FullName like ?\n"
                + "    ) AS CombinedOrders\n"
                + ") AS NumberedOrders\n"
                + "ORDER BY RowNumber\n"
                + "OFFSET ? ROWS\n"
                + "FETCH NEXT 10 ROWS ONLY;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + text + "%");
            ps.setString(2, "%" + text + "%");

            ps.setInt(3, (indexx - 1) * 10);
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

    public boolean deleteOrderCustomer(int orderCID) {
        String sqlDeleteOrderDetail = "DELETE FROM OrderDetailCustomer\n"
                + " WHERE OrderCID = ?";
        String sqlDeleteOrder = "DELETE FROM OrderCustomer\n"
                + " WHERE OrderCID = ?";
        PreparedStatement psOrderDetail = null;
        PreparedStatement psOrder = null;
        try {
            psOrderDetail = connection.prepareStatement(sqlDeleteOrderDetail);
            psOrder = connection.prepareStatement(sqlDeleteOrder);

            psOrderDetail.setInt(1, orderCID);
            int rowsDeletedOrderDetail = psOrderDetail.executeUpdate();

            psOrder.setInt(1, orderCID);
            int rowsDeletedOrder = psOrder.executeUpdate();

            return rowsDeletedOrderDetail > 0 && rowsDeletedOrder > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (psOrderDetail != null) {
                    psOrderDetail.close();
                }
                if (psOrder != null) {
                    psOrder.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean deleteOrderGuest(int orderGID) {
        String sqlDeleteOrderDetail = "DELETE FROM OrderDetailGuest\n"
                + " WHERE OrderGID = ?";
        String sqlDeleteOrder = "DELETE FROM OrderGuest\n"
                + " WHERE OrderGID = ?";
        PreparedStatement psOrderDetail = null;
        PreparedStatement psOrder = null;
        try {
            psOrderDetail = connection.prepareStatement(sqlDeleteOrderDetail);
            psOrder = connection.prepareStatement(sqlDeleteOrder);

            psOrderDetail.setInt(1, orderGID);
            int rowsDeletedOrderDetail = psOrderDetail.executeUpdate();

            psOrder.setInt(1, orderGID);
            int rowsDeletedOrder = psOrder.executeUpdate();

            return rowsDeletedOrderDetail > 0 && rowsDeletedOrder > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (psOrderDetail != null) {
                    psOrderDetail.close();
                }
                if (psOrder != null) {
                    psOrder.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public List<ProductSales> getProductsByCategory(int categoryId, int index) {
        List<ProductSales> productSalesList = new ArrayList<>();
        String query = "SELECT p.ProductID, p.Name, COALESCE(SUM(odc.Quantity), 0) + COALESCE(SUM(odg.Quantity), 0) AS TotalQuantity "
                + "FROM Product p "
                + "LEFT JOIN OrderDetailCustomer odc ON p.ProductID = odc.ProductID "
                + "LEFT JOIN OrderDetailGuest odg ON p.ProductID = odg.ProductID "
                + "WHERE p.CategoryID = ? "
                + "GROUP BY p.ProductID, p.Name "
                + "HAVING COALESCE(SUM(odc.Quantity), 0) + COALESCE(SUM(odg.Quantity), 0) > 0 "
                + "ORDER BY p.Name "
                + "OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, categoryId);
            ps.setInt(2, (index - 1) * 5);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int productId = rs.getInt("productId");
                    String name = rs.getString("name");
                    int totalQuantitySold = rs.getInt("totalQuantity");

                    ProductSales productSales = new ProductSales(productId, name, totalQuantitySold);
                    productSalesList.add(productSales);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productSalesList;
    }

    public int countProductsByCategory(int categoryId) {
        int count = 0;
        String query = "SELECT COUNT(*) AS TotalCount "
                + "FROM ( "
                + "    SELECT p.ProductID "
                + "    FROM Product p "
                + "    LEFT JOIN OrderDetailCustomer odc ON p.ProductID = odc.ProductID "
                + "    LEFT JOIN OrderDetailGuest odg ON p.ProductID = odg.ProductID "
                + "    WHERE p.CategoryID = ? "
                + "    GROUP BY p.ProductID "
                + "    HAVING COALESCE(SUM(odc.Quantity), 0) + COALESCE(SUM(odg.Quantity), 0) > 0 "
                + ") AS SubQuery";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, categoryId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("TotalCount");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public String getCategoryNameById(int categoryId) {
        String categoryName = "";
        String query = "SELECT CategoryName FROM Category WHERE CategoryID = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, categoryId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    categoryName = rs.getString("CategoryName");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryName;
    }

    public List<ProductSales> sortProductsByCategory(int categoryId, int index, String sort) {
        List<ProductSales> productSalesList = new ArrayList<>();
        String sortOrder = "p.Name"; // Default sort by name

        if ("priceasc".equalsIgnoreCase(sort)) {
            sortOrder = "TotalQuantity ASC";
        } else if ("pricedesc".equalsIgnoreCase(sort)) {
            sortOrder = "TotalQuantity DESC";
        }

        String query = "SELECT ProductID, Name, TotalQuantity "
                + "FROM ( "
                + "    SELECT p.ProductID, p.Name, COALESCE(SUM(odc.Quantity), 0) + COALESCE(SUM(odg.Quantity), 0) AS TotalQuantity "
                + "    FROM Product p "
                + "    LEFT JOIN OrderDetailCustomer odc ON p.ProductID = odc.ProductID "
                + "    LEFT JOIN OrderDetailGuest odg ON p.ProductID = odg.ProductID "
                + "    WHERE p.CategoryID = ? "
                + "    GROUP BY p.ProductID, p.Name "
                + "    HAVING COALESCE(SUM(odc.Quantity), 0) + COALESCE(SUM(odg.Quantity), 0) > 0 "
                + ") AS SubQuery "
                + "ORDER BY " + sortOrder + " "
                + "OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, categoryId);
            ps.setInt(2, (index - 1) * 5);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int productId = rs.getInt("ProductID");
                    String name = rs.getString("Name");
                    int totalQuantitySold = rs.getInt("TotalQuantity");

                    ProductSales productSales = new ProductSales(productId, name, totalQuantitySold);
                    productSalesList.add(productSales);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productSalesList;
    }

    public List<ProductSales> searchProductsByCategory(int categoryId, int index, String searchTerm) {
        List<ProductSales> productSalesList = new ArrayList<>();

        String query = "SELECT p.ProductID, p.Name, COALESCE(SUM(odc.Quantity), 0) + COALESCE(SUM(odg.Quantity), 0) AS TotalQuantity \n"
                + "FROM Product p \n"
                + "LEFT JOIN OrderDetailCustomer odc ON p.ProductID = odc.ProductID \n"
                + "LEFT JOIN OrderDetailGuest odg ON p.ProductID = odg.ProductID \n"
                + "WHERE p.CategoryID = ?\n"
                + "AND p.Name LIKE ?\n"
                + "GROUP BY p.ProductID, p.Name \n"
                + "HAVING COALESCE(SUM(odc.Quantity), 0) + COALESCE(SUM(odg.Quantity), 0) > 0 \n"
                + "ORDER BY p.ProductID \n"
                + "OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY;";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, categoryId);
            ps.setString(2, "%" + searchTerm + "%");
            ps.setInt(3, (index - 1) * 5);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int productId = rs.getInt("ProductID");
                    String name = rs.getString("Name");
                    int totalQuantitySold = rs.getInt("TotalQuantity");

                    ProductSales productSales = new ProductSales(productId, name, totalQuantitySold);
                    productSalesList.add(productSales);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productSalesList;
    }

    public int countProductsSearch(int categoryId, String searchTerm) {
        int count = 0;

        String query = "SELECT COUNT(*) AS TotalCount "
                + "FROM ( "
                + "    SELECT p.ProductID "
                + "    FROM Product p "
                + "    LEFT JOIN OrderDetailCustomer odc ON p.ProductID = odc.ProductID "
                + "    LEFT JOIN OrderDetailGuest odg ON p.ProductID = odg.ProductID "
                + "    WHERE p.CategoryID = ? "
                + "    AND p.Name LIKE ? "
                + "    GROUP BY p.ProductID "
                + "    HAVING COALESCE(SUM(odc.Quantity), 0) + COALESCE(SUM(odg.Quantity), 0) > 0 "
                + ") AS SubQuery";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, categoryId);
            ps.setString(2, "%" + searchTerm + "%"); // Search term for name

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("TotalCount");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}
