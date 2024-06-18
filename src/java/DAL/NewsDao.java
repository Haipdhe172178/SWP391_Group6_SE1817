/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Account;
import Models.News;
import Models.Topic;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.Comparator;

public class NewsDao extends DBContext {

    private static final int ELEMENTS_PER_PAGE = 6; // Số lượng sinh viên mỗi trang

    public List<News> getAllNews() {
        List<News> listNews = new ArrayList<>();
        String query = " select n.[NewID], n.TopicID, t.TopicName, n.Title, \n"
                + " n.Content, n.Img1, n.Img2, n.DateUpload, n.Source from News n \n"
                + " join Topic t on n.TopicID = t.TopicID";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                News n = new News();
                n.setNewId(rs.getInt(1));
                Topic topic = new Topic(rs.getInt(2), rs.getString(3));
                n.setTopic(topic);
                n.setTitle(rs.getString(4));
                n.setContent(rs.getString(5));
                n.setImgNews1(rs.getString(6));
                n.setImgNews2(rs.getString(7));
                n.setDateUpload(rs.getDate(8));
                n.setSource(rs.getString(9));

                listNews.add(n);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listNews;
    }

    public List<News> getFourNewsLated() {
        List<News> listNews = new ArrayList<>();
        String query = "  select Top 4 n.[NewID], n.TopicID, t.TopicName, n.Title, \n"
                + " n.Content, n.Img1, n.Img2, n.DateUpload, n.Source from News n \n"
                + " join Topic t on n.TopicID = t.TopicID\n"
                + " order by DateUpload desc";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                News n = new News();
                n.setNewId(rs.getInt(1));
                Topic topic = new Topic(rs.getInt(2), rs.getString(3));
                n.setTopic(topic);
                n.setTitle(rs.getString(4));
                n.setContent(rs.getString(5));
                n.setImgNews1(rs.getString(6));
                n.setImgNews2(rs.getString(7));
                n.setDateUpload(rs.getDate(8));
                n.setSource(rs.getString(9));

                listNews.add(n);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listNews;
    }

    public List<Topic> getTopic() {
        List<Topic> listTopic = new ArrayList<>();
        String query = "  select * from Topic";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Topic t = new Topic();
                t.setTopicId(rs.getInt(1));
                t.setTopicName(rs.getString(2));

                listTopic.add(t);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listTopic;
    }

    //Test newDAO
    public static void main(String[] args) {
        NewsDao nd = new NewsDao();
        List<News> listNews = nd.getNewsPagination(1, "increase");
        for (News listNew : listNews) {
            System.out.println(listNew.getDateUpload());
        }

    }

    public List<News> getNewsByCateId(int tid) {
        List<News> listNews = new ArrayList<>();
        String query = " select n.[NewID], n.TopicID, t.TopicName, n.Title, \n"
                + " n.Content, n.Img1, n.Img2, n.DateUpload, n.Source from News n \n"
                + " join Topic t on n.TopicID = t.TopicID WHERE t.TopicID =?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, tid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                News n = new News();
                n.setNewId(rs.getInt(1));
                Topic topic = new Topic(rs.getInt(2), rs.getString(3));
                n.setTopic(topic);
                n.setTitle(rs.getString(4));
                n.setContent(rs.getString(5));
                n.setImgNews1(rs.getString(6));
                n.setImgNews2(rs.getString(7));
                n.setDateUpload(rs.getDate(8));
                n.setSource(rs.getString(9));

                listNews.add(n);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listNews;
    }

    public List<News> getNewsPagination(int index, String sort) {
        List<News> listNews = new ArrayList<>();
        String query = "SELECT n.NewID, n.TopicID, t.TopicName, n.Title, n.Content, n.Img1, n.Img2, n.DateUpload, n.Source "
                + "FROM News n JOIN Topic t ON n.TopicID = t.TopicID "
                + "ORDER BY ";
        switch (sort) {
            case "decrease":
                query += "n.DateUpload DESC ";
                break;
            case "increase":
                query += "n.DateUpload ASC ";
                break;
            default:
                // Default sorting or handle invalid sort parameter
                query += "n.DateUpload DESC ";
                break;
        }
        query += "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        try {
            int offset = (index - 1) * ELEMENTS_PER_PAGE;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, offset);
            ps.setInt(2, ELEMENTS_PER_PAGE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                News n = new News();
                n.setNewId(rs.getInt(1));
                Topic topic = new Topic(rs.getInt(2), rs.getString(3));
                n.setTopic(topic);
                n.setTitle(rs.getString(4));
                n.setContent(rs.getString(5));
                n.setImgNews1(rs.getString(6));
                n.setImgNews2(rs.getString(7));
                n.setDateUpload(rs.getDate(8));
                n.setSource(rs.getString(9));

                listNews.add(n);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listNews;
    }

    public List<News> getNewsPaginationByTopic(int index, int tid, String sort) {
        List<News> listNews = new ArrayList<>();
        String query = "SELECT n.NewID, n.TopicID, t.TopicName, n.Title, n.Content, n.Img1, n.Img2, n.DateUpload, n.Source "
                + "FROM News n JOIN Topic t ON n.TopicID = t.TopicID "
                + "WHERE t.TopicID = ? "
                + "ORDER BY ";
        switch (sort) {
            case "decrease":
                query += "n.DateUpload DESC ";
                break;
            case "increase":
                query += "n.DateUpload ASC ";
                break;
            default:
                // Default sorting or handle invalid sort parameter
                query += "n.DateUpload DESC ";
                break;
        }
        query += "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        try {
            int offset = (index - 1) * ELEMENTS_PER_PAGE;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, tid);
            ps.setInt(2, offset);
            ps.setInt(3, ELEMENTS_PER_PAGE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                News n = new News();
                n.setNewId(rs.getInt(1));
                Topic topic = new Topic(rs.getInt(2), rs.getString(3));
                n.setTopic(topic);
                n.setTitle(rs.getString(4));
                n.setContent(rs.getString(5));
                n.setImgNews1(rs.getString(6));
                n.setImgNews2(rs.getString(7));
                n.setDateUpload(rs.getDate(8));
                n.setSource(rs.getString(9));

                listNews.add(n);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listNews;
    }
 public News getNewsById(int id) {
        News news = null;
        String query = "SELECT n.NewID, n.TopicID, t.TopicName, n.Title, n.Content, n.Img1, n.Img2, n.DateUpload, n.Source "
                     + "FROM News n JOIN Topic t ON n.TopicID = t.TopicID WHERE n.NewID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                news = new News();
                news.setNewId(rs.getInt(1));
                Topic topic = new Topic(rs.getInt(2), rs.getString(3));
                news.setTopic(topic);
                news.setTitle(rs.getString(4));
                news.setContent(rs.getString(5));
                news.setImgNews1(rs.getString(6));
                news.setImgNews2(rs.getString(7));
                news.setDateUpload(rs.getDate(8));
                news.setSource(rs.getString(9));

                // Fetch tags
                news.setTags(getTagsByNewsId(id));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return news;
    }

    private List<String> getTagsByNewsId(int newsId) {
        List<String> tags = new ArrayList<>();
        String query = "SELECT TagName FROM NewsTags WHERE NewsID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, newsId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tags.add(rs.getString("TagName"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tags;
    }

            }
