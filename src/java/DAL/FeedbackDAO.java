/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Account;
import Models.Feedback;
import Models.Product;
import java.util.*;
import java.lang.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedbackDAO extends DBContext {

    private final AccountDAO accDao = new AccountDAO();
    private final ProductDao pDao = new ProductDao();
    private static final int ELEMENTS_PER_PAGE = 5;

    public List<Feedback> getFeedbackByProductId(int pid, int index) {
        List<Feedback> listFeedback = new ArrayList<>();
        String query = "SELECT * FROM Feedback WHERE Status = 1 AND ProductID = ? ORDER BY FeedbackID DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        try {
            int offset = (index - 1) * ELEMENTS_PER_PAGE;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, pid);
            ps.setInt(2, offset);
            ps.setInt(3, ELEMENTS_PER_PAGE);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Feedback fb = new Feedback();
                fb.setFeedbackId(rs.getInt(1));
                fb.setAccount(accDao.getAccountByid(rs.getInt(2)));
                fb.setProduct(pDao.getProductById(rs.getInt(3)));
                fb.setRating(rs.getInt(4));
                fb.setComments(rs.getString(5));
                fb.setFeedbackDate(rs.getDate(6));
                fb.setStatus(rs.getInt(7));
                listFeedback.add(fb);
            }

            rs.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listFeedback;
    }

    public List<Feedback> getAllFeedback(int index) {
        List<Feedback> listFeedback = new ArrayList<>();
        String query = "SELECT * FROM Feedback ORDER BY FeedbackID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

        try {
            int offset = (index - 1) * ELEMENTS_PER_PAGE;
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, offset);
            ps.setInt(2, ELEMENTS_PER_PAGE);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Feedback fb = new Feedback();
                fb.setFeedbackId(rs.getInt(1));
                fb.setAccount(accDao.getAccountByid(rs.getInt(2)));
                fb.setProduct(pDao.getProductById(rs.getInt(3)));
                fb.setRating(rs.getInt(4));
                fb.setComments(rs.getString(5));
                fb.setFeedbackDate(rs.getDate(6));
                fb.setStatus(rs.getInt(7));
                listFeedback.add(fb);
            }

            rs.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listFeedback;
    }

    public List<Feedback> getAllFeedbackByStatus(int index, String status, String search, String filter) {
        List<Feedback> listFeedback = new ArrayList<>();
        String query = "SELECT * FROM Feedback f JOIN Product p ON f.ProductID = p.ProductID WHERE f.Status = ? \n";

        if (search != null && !search.isEmpty()) {
            query += "AND p.Name LIKE ?\n";
        }

        if (filter != null && !filter.isEmpty()) {
            query += "AND f.Rating = ? \n ";
        }
        query += " ORDER BY FeedbackID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

        try {
            int offset = (index - 1) * ELEMENTS_PER_PAGE;
            PreparedStatement ps = connection.prepareStatement(query);

            // Set status parameter
            switch (status) {
                case "accept":
                    ps.setInt(1, 1);
                    break;
                case "reject":
                    ps.setInt(1, -1);
                    break;
                case "pending":
                    ps.setInt(1, 0);
                    break;
                default:
                    throw new AssertionError();
            }

            int paramIndex = 2;

            if (search != null && !search.isEmpty()) {
                ps.setString(paramIndex++, "%" + search + "%");
            }

            if (filter != null && !filter.isEmpty()) {
                ps.setInt(paramIndex++, Integer.parseInt(filter));
            }

            ps.setInt(paramIndex++, offset);
            ps.setInt(paramIndex, ELEMENTS_PER_PAGE);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Feedback fb = new Feedback();
                fb.setFeedbackId(rs.getInt("FeedbackID"));
                // Replace accDao.getAccountById and pDao.getProductById with your DAO methods
                fb.setAccount(accDao.getAccountByid(rs.getInt("AccountID")));
                fb.setProduct(pDao.getProductById(rs.getInt("ProductID")));
                fb.setRating(rs.getInt("Rating"));
                fb.setComments(rs.getString("Comments"));
                fb.setFeedbackDate(rs.getDate("FeedbackDate"));
                fb.setStatus(rs.getInt("Status"));
                listFeedback.add(fb);
            }

            rs.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listFeedback;
    }

    public List<Feedback> getFeedbackByProductId(int pid, int index, int filter) {
        List<Feedback> listFeedback = new ArrayList<>();
        String query = "SELECT * FROM Feedback WHERE Status = 1 AND ProductID = ? AND Rating = ? ORDER BY FeedbackID DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        try {
            int offset = (index - 1) * ELEMENTS_PER_PAGE;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, pid);
            ps.setInt(2, filter);
            ps.setInt(3, offset);
            ps.setInt(4, ELEMENTS_PER_PAGE);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Feedback fb = new Feedback();
                fb.setFeedbackId(rs.getInt(1));
                fb.setAccount(accDao.getAccountByid(rs.getInt(2)));
                fb.setProduct(pDao.getProductById(rs.getInt(3)));
                fb.setRating(rs.getInt(4));
                fb.setComments(rs.getString(5));
                fb.setFeedbackDate(rs.getDate(6));
                fb.setStatus(rs.getInt(7));
                listFeedback.add(fb);
            }

            rs.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listFeedback;
    }

    public int getQuantityFeedbacks(String status, String search, String filter) {
        int quantity = 0;
        String query = "select count(*) from Feedback f join Product p on f.ProductID = p.ProductID";
        switch (status) {
            case "accept":
                query += " where f.Status = 1";
                break;
            case "pending":
                query += " where f.Status = 0";
                break;
            case "reject":
                query += " where f.Status = -1";
                break;
            default:
        }
        if (search != null && !search.isEmpty()) {
            query += " and p.Name like ?";
        }
        if (filter != null && !filter.isEmpty()) {
            query += " and f.Rating = ?";
        }
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            int param = 1;
            if (search != null && !search.isEmpty()) {
                ps.setString(param++, "%" + search + "%");
            }
            if (filter != null && !filter.isEmpty()) {
                ps.setInt(param, Integer.parseInt(filter));
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                quantity = rs.getInt(1); // Lấy kết quả của Count(*)
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return quantity; // Trả về số lượng phản hồi
    }

    public int getQuantityFeedbacksByPid(int pid, String rating) {
        int quantity = 0;
        String query = "SELECT Count(*)\n"
                + "FROM Feedback \n"
                + "WHERE ProductID = ? AND Status = 1 ";
        if (rating != null && !rating.isEmpty()) {
            query += " AND Rating = ?";
        }
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, pid);
            if (rating != null && !rating.isEmpty()) {
                ps.setInt(2, Integer.parseInt(rating)); // Chuyển đổi rating thành int
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                quantity = rs.getInt(1); // Lấy kết quả của Count(*)
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return quantity; // Trả về số lượng phản hồi
    }

    //Lấy những đánh giá cao từ khách hàng
    public List<Feedback> getFeedbackMostRating() {
        List<Feedback> listFeedback = new ArrayList<>();
        String query = "select * from Feedback where Rating = 5 and Comments is not null";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Feedback fb = new Feedback();
                fb.setFeedbackId(rs.getInt(1));
                fb.setAccount(accDao.getAccountByid(rs.getInt(2)));
                fb.setProduct(pDao.getProductById(rs.getInt(3)));
                fb.setRating(rs.getInt(4));
                fb.setComments(rs.getString(5));
                fb.setFeedbackDate(rs.getDate(6));
                fb.setStatus(rs.getInt(7));
                listFeedback.add(fb);
            }
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listFeedback;
    }

    //tính trung bình rating của một sản phẩm
    public int avgRating(int pid) {
        int totalRating = 0;
        int countFeedback = 0;
        String sumQuery = "SELECT SUM(Rating) AS TotalRating FROM Feedback WHERE ProductID = ?";
        String countQuery = "SELECT COUNT(FeedbackID) AS CountFeedback FROM Feedback WHERE ProductID = ?";

        try {
            PreparedStatement psSum = connection.prepareStatement(sumQuery);
            psSum.setInt(1, pid);
            ResultSet rsSum = psSum.executeQuery();
            if (rsSum.next()) {
                totalRating = rsSum.getInt(1);
            }
            rsSum.close();
            psSum.close();

            PreparedStatement psCount = connection.prepareStatement(countQuery);
            psCount.setInt(1, pid);
            ResultSet rsCount = psCount.executeQuery();
            if (rsCount.next()) {
                countFeedback = rsCount.getInt(1);
            }
            rsCount.close();
            psCount.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (countFeedback > 0) {
            return totalRating / countFeedback;
        } else {
            return 0;
        }
    }

    public boolean addFeedback(Feedback newFeedback) {
        String sql = "  INSERT INTO [dbo].[Feedback] (AccountID, ProductID, Rating, Comments,Status) VALUES (?, ?, ?, ?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, newFeedback.getAccount().getAccountId());
            st.setInt(2, newFeedback.getProduct().getProductId());
            st.setInt(3, newFeedback.getRating());
            st.setString(4, newFeedback.getComments());
            st.setInt(5, 0);
            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateStatusFeedback(int feedbackId, int status) {
        String sql = "  Update Feedback\n"
                + "  Set Status = ?\n"
                + "  where FeedbackID=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, feedbackId);
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteFeedback(int feedbackId) {
        String sql = " delete from Feedback\n"
                + "  where FeedbackID=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, feedbackId);
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //TEST FUNCTION
    public static void main(String[] args) {
        FeedbackDAO fb = new FeedbackDAO();
        List<Feedback> listF = fb.getAllFeedbackByStatus(5, "pending", "", "5");
        for (Feedback feedback : listF) {
            System.out.println(feedback.getFeedbackId());
        }
        System.out.println(fb.getQuantityFeedbacks("pending", "", "5"));
//        List<Feedback> list = fb.getFeedbackByProductId(1);
//        for (Feedback feedback : list) {
//            System.out.println(feedback.getComments());
//        }
//        System.out.println(fb.avgRating(1));
    }
}
