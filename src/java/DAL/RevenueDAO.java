/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Revenue;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

/**
 *
 * @author huyca
 */
public class RevenueDAO extends DBContext {

    public List<Revenue> getRevenueByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Revenue> revenues = new ArrayList<>();
        String query = "SELECT order_year, order_month, order_day, SUM(total_revenue) AS total_revenue "
                + "FROM ("
                + "    SELECT YEAR(Date) AS order_year, MONTH(Date) AS order_month, DAY(Date) AS order_day, TotalPrice AS total_revenue "
                + "    FROM OrderGuest "
                + "    WHERE Date BETWEEN ? AND ? AND StatusID = 4 "
                + "    UNION ALL "
                + "    SELECT YEAR(Date) AS order_year, MONTH(Date) AS order_month, DAY(Date) AS order_day, TotalPrice AS total_revenue "
                + "    FROM OrderCustomer "
                + "    WHERE Date BETWEEN ? AND ? AND StatusID = 4 "
                + ") AS combined_orders "
                + "GROUP BY order_year, order_month, order_day "
                + "ORDER BY order_year, order_month, order_day";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setDate(1, java.sql.Date.valueOf(startDate));
            ps.setDate(2, java.sql.Date.valueOf(endDate));
            ps.setDate(3, java.sql.Date.valueOf(startDate));
            ps.setDate(4, java.sql.Date.valueOf(endDate));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int orderYear = rs.getInt("order_year");
                    int orderMonth = rs.getInt("order_month");
                    int orderDay = rs.getInt("order_day");
                    float totalRevenue = rs.getFloat("total_revenue");
                    Revenue revenue = new Revenue(orderYear, orderMonth, orderDay, totalRevenue);
                    revenues.add(revenue);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return revenues;
    }

    public List<Revenue> getRevenueByYear(int year) {
        List<Revenue> revenues = new ArrayList<>();
        String query = "SELECT order_month, SUM(total_revenue) AS total_revenue "
                + "FROM ( "
                + "    SELECT MONTH(Date) AS order_month, TotalPrice AS total_revenue "
                + "    FROM OrderGuest "
                + "    WHERE YEAR(Date) = ? AND StatusID = 4 "
                + "    UNION ALL "
                + "    SELECT MONTH(Date) AS order_month, TotalPrice AS total_revenue "
                + "    FROM OrderCustomer "
                + "    WHERE YEAR(Date) = ? AND StatusID = 4 "
                + ") AS combined_orders "
                + "GROUP BY order_month "
                + "ORDER BY order_month";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, year);
            ps.setInt(2, year);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int orderMonth = rs.getInt("order_month");
                    float totalRevenue = rs.getFloat("total_revenue");
                    Revenue revenue = new Revenue(year, orderMonth, totalRevenue);
                    revenues.add(revenue);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return revenues;
    }

    public List<Revenue> getOrderMonth(int year) {
        List<Revenue> revenues = new ArrayList<>();
        String query = "SELECT order_month, COUNT(*) AS total_orders "
                + " FROM ("
                + " SELECT MONTH(Date) AS order_month "
                + " FROM OrderGuest "
                + " WHERE YEAR(Date) = ?"
                + " UNION ALL "
                + " SELECT MONTH(Date) AS order_month "
                + " FROM OrderCustomer "
                + " WHERE YEAR(Date) = ?"
                + ") AS combined_orders "
                + " GROUP BY order_month"
                + " ORDER BY order_month;";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, year);
            ps.setInt(2, year);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int orderMonth = rs.getInt("order_month");
                    int totalOrders = rs.getInt("total_orders");
                    Revenue revenue = new Revenue(year, orderMonth, totalOrders);
                    revenues.add(revenue);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return revenues;
    }

    public List<Revenue> getOrdersWithCounts(LocalDate startDate, LocalDate endDate) {
        List<Revenue> ordersWithCounts = new ArrayList<>();
        String query = "SELECT order_year, order_month, order_day, COUNT(*) AS total_orders "
                + "FROM ("
                + "    SELECT YEAR(Date) AS order_year, MONTH(Date) AS order_month, DAY(Date) AS order_day "
                + "    FROM OrderGuest "
                + "    WHERE Date BETWEEN ? AND ? "
                + "    UNION ALL "
                + "    SELECT YEAR(Date) AS order_year, MONTH(Date) AS order_month, DAY(Date) AS order_day "
                + "    FROM OrderCustomer "
                + "    WHERE Date BETWEEN ? AND ? "
                + ") AS combined_orders "
                + "GROUP BY order_year, order_month, order_day "
                + "ORDER BY order_year, order_month, order_day";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setDate(1, java.sql.Date.valueOf(startDate));
            ps.setDate(2, java.sql.Date.valueOf(endDate));
            ps.setDate(3, java.sql.Date.valueOf(startDate));
            ps.setDate(4, java.sql.Date.valueOf(endDate));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int orderYear = rs.getInt("order_year");
                    int orderMonth = rs.getInt("order_month");
                    int orderDay = rs.getInt("order_day");
                    int totalOrders = rs.getInt("total_orders");
                    Revenue revenue = new Revenue(orderYear, orderMonth, orderDay, totalOrders);
                    ordersWithCounts.add(revenue);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ordersWithCounts;
    }

    public int getTotalOrdersCount(LocalDate startDate, LocalDate endDate) {
        String query = "SELECT COUNT(*) AS total_orders "
                + "FROM ("
                + "    SELECT Date "
                + "    FROM OrderGuest "
                + "    WHERE Date BETWEEN ? AND ? "
                + "    UNION ALL "
                + "    SELECT Date "
                + "    FROM OrderCustomer "
                + "    WHERE Date BETWEEN ? AND ? "
                + ") AS combined_orders";

        int totalOrders = 0;

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setDate(1, java.sql.Date.valueOf(startDate));
            ps.setDate(2, java.sql.Date.valueOf(endDate));
            ps.setDate(3, java.sql.Date.valueOf(startDate));
            ps.setDate(4, java.sql.Date.valueOf(endDate));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalOrders = rs.getInt("total_orders");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return totalOrders;
    }
    public float getTotalRevenue(LocalDate startDate, LocalDate endDate) {
    float totalRevenue = 0.0f;
    String query = "SELECT SUM(TotalPrice) AS total_revenue "
                 + "FROM ("
                 + "    SELECT TotalPrice "
                 + "    FROM OrderGuest "
                 + "    WHERE Date BETWEEN ? AND ? AND StatusID = 4 "
                 + "    UNION ALL "
                 + "    SELECT TotalPrice "
                 + "    FROM OrderCustomer "
                 + "    WHERE Date BETWEEN ? AND ? AND StatusID = 4 "
                 + ") AS combined_orders";

    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setDate(1, java.sql.Date.valueOf(startDate));
        ps.setDate(2, java.sql.Date.valueOf(endDate));
        ps.setDate(3, java.sql.Date.valueOf(startDate));
        ps.setDate(4, java.sql.Date.valueOf(endDate));

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                totalRevenue = rs.getFloat("total_revenue");
            }
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return totalRevenue;
}



}
