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
//        List<Topic> listT = nd.getTopic();
//        for (Topic topic : listT) {
//            System.out.println(topic.getTopicName());
//        }
        List<News> listNews = nd.getNewsPagination(1);
        Collections.sort(listNews, new Comparator<News>() {
            @Override
            public int compare(News o1, News o2) {
                return o1.getDateUpload().compareTo(o2.getDateUpload());
            }
        });
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

    public List<News> getNewsPagination(int index) {
        List<News> listNews = new ArrayList<>();
        String query = " select n.[NewID], n.TopicID, t.TopicName,\n"
                + " n.Title, n.Content, n.Img1, n.Img2, n.DateUpload,\n"
                + " n.Source from News n join Topic t \n"
                + " on n.TopicID = t.TopicID ORDER BY [NewID] \n"
                + " OFFSET ?  Rows fetch next ? row only;";
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

    public List<News> getNewsPaginationByTopic(int index, int tid) {
        List<News> listNews = new ArrayList<>();
        String query = " select n.[NewID], n.TopicID, t.TopicName,\n"
                + " n.Title, n.Content, n.Img1, n.Img2, n.DateUpload,\n"
                + " n.Source from News n join Topic t \n"
                + " on n.TopicID = t.TopicID  Where t.TopicID = ? ORDER BY [NewID] \n"
                + " OFFSET ?  Rows fetch next ? row only;";
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
}
