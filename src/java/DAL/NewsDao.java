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

public class NewsDao extends DBContext {

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

    //Test newDAO
    public static void main(String[] args) {
        NewsDao nd = new NewsDao();
        List<News> listNews = nd.getAllNews();
        for (News listNew : listNews) {
            System.out.println(listNew.getNewId());
        }
    }
}
