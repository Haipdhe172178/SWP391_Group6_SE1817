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
        String query = "SELECT * FROM Feedback WHERE ProductID = ? ORDER BY FeedbackID DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
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
                fb.setAccount(accDao.getAccountById(rs.getInt(2)));
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

    public List<Feedback> getFeedbackByProductId(int pid, int index, int filter) {
        List<Feedback> listFeedback = new ArrayList<>();
        String query = "SELECT * FROM Feedback WHERE ProductID = ? AND Rating = ? ORDER BY FeedbackID DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
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
                fb.setAccount(accDao.getAccountById(rs.getInt(2)));
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

    public int getQuantityFeedbacks(int pid, String rating) {
        int quantity = 0;
        String query = "SELECT Count(*)\n"
                + "FROM Feedback \n"
                + "WHERE ProductID = ?";
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
                fb.setAccount(accDao.getAccountById(rs.getInt(2)));
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
        String sql = "  INSERT INTO [dbo].[Feedback] (AccountID, ProductID, Rating, Comments) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, newFeedback.getAccount().getAccountId());
            st.setInt(2, newFeedback.getProduct().getProductId());
            st.setInt(3, newFeedback.getRating());
            st.setString(4, newFeedback.getComments());
            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //TEST FUNCTION
    public static void main(String[] args) {
        FeedbackDAO fb = new FeedbackDAO();
        List<Feedback> listF = fb.getFeedbackByProductId(1, 1);
        for (Feedback feedback : listF) {
            System.out.println(feedback.getAccount().getFullName());
        }
//        List<Feedback> list = fb.getFeedbackByProductId(1);
//        for (Feedback feedback : list) {
//            System.out.println(feedback.getComments());
//        }
//        System.out.println(fb.avgRating(1));
    }
}
