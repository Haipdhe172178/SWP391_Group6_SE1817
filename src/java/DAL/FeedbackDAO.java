/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Feedback;
import java.util.*;
import java.lang.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedbackDAO extends DBContext {

    public List<Feedback> getFeedbackByProductId(int pid) {
        List<Feedback> listFeedback = new ArrayList<>();
        String query = "select * from Feedback where ProductID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, pid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Feedback fb = new Feedback();
                fb.setFeedbackId(rs.getInt(1));
                fb.setAccountId(rs.getInt(2));
                fb.setProductId(rs.getInt(3));
                fb.setRating(rs.getInt(4));
                fb.setComments(rs.getString(5));
                fb.setFeedbackDate(rs.getDate(6));

                listFeedback.add(fb);
            }
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listFeedback;
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
                fb.setAccountId(rs.getInt(2));
                fb.setProductId(rs.getInt(3));
                fb.setRating(rs.getInt(4));
                fb.setComments(rs.getString(5));
                fb.setFeedbackDate(rs.getDate(6));
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
            st.setInt(1, newFeedback.getAccountId());
            st.setInt(2, newFeedback.getProductId());
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
//        List<Feedback> list = fb.getFeedbackByProductId(1);
//        for (Feedback feedback : list) {
//            System.out.println(feedback.getComments());
//        }
//        System.out.println(fb.avgRating(1));
    }
}
