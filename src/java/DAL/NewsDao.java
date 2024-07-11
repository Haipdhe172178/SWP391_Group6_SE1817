package DAL;

import Models.News;
import Models.Topic;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsDao extends DBContext {

    private static final int ELEMENTS_PER_PAGE = 6; // Number of news per page

    public List<News> getAllNews() {
        List<News> listNews = new ArrayList<>();
        String query = "SELECT n.NewID, n.TopicID, t.TopicName, n.Title, n.Content, n.Img, n.DateUpload, n.Source "
                + "FROM News n JOIN Topic t ON n.TopicID = t.TopicID";
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                News n = new News();
                n.setNewId(rs.getInt("NewID"));
                Topic topic = new Topic(rs.getInt("TopicID"), rs.getString("TopicName"));
                n.setTopic(topic);
                n.setTitle(rs.getString("Title"));
                n.setContent(rs.getString("Content"));
                n.setImgNews(rs.getString("Img"));
                n.setDateUpload(rs.getDate("DateUpload"));
                n.setSource(rs.getString("Source"));
                listNews.add(n);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listNews;
    }

    public List<News> getFourNewsLated() {
        List<News> listNews = new ArrayList<>();
        String query = "SELECT TOP 4 n.NewID, n.TopicID, t.TopicName, n.Title, n.Content, n.Img, n.DateUpload, n.Source "
                + "FROM News n JOIN Topic t ON n.TopicID = t.TopicID ORDER BY n.DateUpload DESC";
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                News n = new News();
                n.setNewId(rs.getInt("NewID"));
                Topic topic = new Topic(rs.getInt("TopicID"), rs.getString("TopicName"));
                n.setTopic(topic);
                n.setTitle(rs.getString("Title"));
                n.setContent(rs.getString("Content"));
                n.setImgNews(rs.getString("Img"));
                n.setDateUpload(rs.getDate("DateUpload"));
                n.setSource(rs.getString("Source"));
                listNews.add(n);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listNews;
    }

    public List<Topic> getTopic() {
        List<Topic> listTopic = new ArrayList<>();
        String query = "SELECT * FROM Topic";
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Topic t = new Topic();
                t.setTopicId(rs.getInt("TopicID"));
                t.setTopicName(rs.getString("TopicName"));
                listTopic.add(t);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listTopic;
    }

    public List<News> getNewsByCateId(int tid) {
        List<News> listNews = new ArrayList<>();
        String query = "SELECT n.NewID, n.TopicID, t.TopicName, n.Title, n.Content, n.Img, n.DateUpload, n.Source "
                + "FROM News n JOIN Topic t ON n.TopicID = t.TopicID WHERE t.TopicID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, tid);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    News n = new News();
                    n.setNewId(rs.getInt("NewID"));
                    Topic topic = new Topic(rs.getInt("TopicID"), rs.getString("TopicName"));
                    n.setTopic(topic);
                    n.setTitle(rs.getString("Title"));
                    n.setContent(rs.getString("Content"));
                    n.setImgNews(rs.getString("Img"));
                    n.setDateUpload(rs.getDate("DateUpload"));
                    n.setSource(rs.getString("Source"));
                    listNews.add(n);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listNews;
    }

    public List<News> searchNews(String searchTerm, int page, String sort) {
        List<News> listNews = new ArrayList<>();
        String query = "SELECT n.NewID, n.TopicID, t.TopicName, n.Title, n.Content, n.Img, n.DateUpload, n.Source, n.Status "
                + "FROM News n JOIN Topic t ON n.TopicID = t.TopicID "
                + "WHERE n.Title LIKE ? OR n.Content LIKE ? ORDER BY ";
        switch (sort) {
            case "decrease":
                query += "n.DateUpload DESC ";
                break;
            case "increase":
                query += "n.DateUpload ASC ";
                break;
            default:
                query += "n.DateUpload DESC ";
                break;
        }
        query += "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            String searchPattern = "%" + searchTerm + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            int offset = (page - 1) * ELEMENTS_PER_PAGE;
            ps.setInt(3, offset);
            ps.setInt(4, ELEMENTS_PER_PAGE);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    News n = new News();
                    n.setNewId(rs.getInt("NewID"));
                    Topic topic = new Topic(rs.getInt("TopicID"), rs.getString("TopicName"));
                    n.setTopic(topic);
                    n.setTitle(rs.getString("Title"));
                    n.setContent(rs.getString("Content"));
                    n.setImgNews(rs.getString("Img"));
                    n.setDateUpload(rs.getDate("DateUpload"));
                    n.setSource(rs.getString("Source"));
                    n.setStatus(rs.getBoolean("Status")); // Set status
                    listNews.add(n);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listNews;
    }

    public int getSearchCount(String searchTerm) {
        String query = "SELECT COUNT(*) AS total FROM News WHERE Title LIKE ? OR Content LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            String searchPattern = "%" + searchTerm + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public List<News> getNewsPagination(int page, String sort) {
        List<News> listNews = new ArrayList<>();
        String query = "SELECT n.NewID, n.TopicID, t.TopicName, n.Title, n.Content, n.Img, n.DateUpload, n.Source, n.Status "
                + "FROM News n JOIN Topic t ON n.TopicID = t.TopicID "
                + "WHERE n.Status = 1 "
                + // Add the WHERE clause here
                "ORDER BY ";
        switch (sort) {
            case "decrease":
                query += "n.DateUpload DESC ";
                break;
            case "increase":
                query += "n.DateUpload ASC ";
                break;
            default:
                query += "n.DateUpload DESC ";
                break;
        }
        query += "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            int offset = (page - 1) * ELEMENTS_PER_PAGE;
            ps.setInt(1, offset);
            ps.setInt(2, ELEMENTS_PER_PAGE);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    News n = new News();
                    n.setNewId(rs.getInt("NewID"));
                    Topic topic = new Topic(rs.getInt("TopicID"), rs.getString("TopicName"));
                    n.setTopic(topic);
                    n.setTitle(rs.getString("Title"));
                    n.setContent(rs.getString("Content"));
                    n.setImgNews(rs.getString("Img"));
                    n.setDateUpload(rs.getDate("DateUpload"));
                    n.setSource(rs.getString("Source"));
                    n.setStatus(rs.getBoolean("Status")); // Set status
                    listNews.add(n);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listNews;
    }

    public int getNewsCount() {
        String query = "SELECT COUNT(*) AS total FROM News";
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public List<News> getNewsPaginationByTopic(int index, int tid, String sort) {
        List<News> listNews = new ArrayList<>();
        String query = "SELECT n.NewID, n.TopicID, t.TopicName, n.Title, n.Content, n.Img, n.DateUpload, n.Source "
                + "FROM News n JOIN Topic t ON n.TopicID = t.TopicID WHERE t.TopicID = ? ORDER BY ";
        switch (sort) {
            case "decrease":
                query += "n.DateUpload DESC ";
                break;
            case "increase":
                query += "n.DateUpload ASC ";
                break;
            default:
                query += "n.DateUpload DESC ";
                break;
        }
        query += "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            int offset = (index - 1) * ELEMENTS_PER_PAGE;
            ps.setInt(1, tid);
            ps.setInt(2, offset);
            ps.setInt(3, ELEMENTS_PER_PAGE);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    News n = new News();
                    n.setNewId(rs.getInt("NewID"));
                    Topic topic = new Topic(rs.getInt("TopicID"), rs.getString("TopicName"));
                    n.setTopic(topic);
                    n.setTitle(rs.getString("Title"));
                    n.setContent(rs.getString("Content"));
                    n.setImgNews(rs.getString("Img"));
                    n.setDateUpload(rs.getDate("DateUpload"));
                    n.setSource(rs.getString("Source"));
                    listNews.add(n);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listNews;
    }

    public News getNewsById(int id) {
        News news = null;
        String query = "SELECT n.NewID, n.TopicID, t.TopicName, n.Title, n.Content, n.Img, n.DateUpload, n.Source "
                + "FROM News n JOIN Topic t ON n.TopicID = t.TopicID WHERE n.NewID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    news = new News();
                    news.setNewId(rs.getInt("NewID"));
                    Topic topic = new Topic(rs.getInt("TopicID"), rs.getString("TopicName"));
                    news.setTopic(topic);
                    news.setTitle(rs.getString("Title"));
                    news.setContent(rs.getString("Content"));
                    news.setImgNews(rs.getString("Img"));
                    news.setDateUpload(rs.getDate("DateUpload"));
                    news.setSource(rs.getString("Source"));

                    // Fetch tags
                    news.setTags(getTagsByNewsId(id));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return news;
    }

    private List<String> getTagsByNewsId(int newsId) {
        List<String> tags = new ArrayList<>();
        String query = "SELECT TagName FROM NewsTags WHERE NewsID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, newsId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tags.add(rs.getString("TagName"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tags;
    }

    public boolean insertNews(News news) {
        String query = "INSERT INTO News (TopicID, Title, Content, Img, DateUpload, Source, Status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, news.getTopic().getTopicId());
            ps.setString(2, news.getTitle());
            ps.setString(3, news.getContent());
            ps.setString(4, news.getImgNews());
            ps.setDate(5, (Date) news.getDateUpload());
            ps.setString(6, news.getSource());
            ps.setBoolean(7, news.isStatus());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean updateNews(News news) {
        String query = "UPDATE News SET TopicID = ?, Title = ?, Content = ?, Img = ?, DateUpload = ?, Source = ?, Status = ? WHERE NewID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, news.getTopic().getTopicId());
            ps.setString(2, news.getTitle());
            ps.setString(3, news.getContent());
            ps.setString(4, news.getImgNews());
            ps.setDate(5, (Date) news.getDateUpload());
            ps.setString(6, news.getSource());
            ps.setBoolean(7, news.isStatus());
            ps.setInt(8, news.getNewId());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deleteNews(int id) {
        String query = "DELETE FROM News WHERE NewID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public List<News> getFilteredNews(String status, int topicId, int page, String sort) {
        List<News> listNews = new ArrayList<>();
        String query = "SELECT n.NewID, n.TopicID, t.TopicName, n.Title, n.Content, n.Img, n.DateUpload, n.Source, n.Status "
                + "FROM News n JOIN Topic t ON n.TopicID = t.TopicID WHERE 1=1 ";

        if (status != null && !status.isEmpty()) {
            query += " AND n.Status = ? ";
        }
        if (topicId != -1) {
            query += " AND t.TopicID = ? ";
        }
        query += "ORDER BY ";
        switch (sort) {
            case "decrease":
                query += "n.DateUpload DESC ";
                break;
            case "increase":
                query += "n.DateUpload ASC ";
                break;
            default:
                query += "n.DateUpload DESC ";
                break;
        }
        query += "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            int paramIndex = 1;
            if (status != null && !status.isEmpty()) {
                ps.setBoolean(paramIndex++, Boolean.parseBoolean(status));
            }
            if (topicId != -1) {
                ps.setInt(paramIndex++, topicId);
            }
            int offset = (page - 1) * ELEMENTS_PER_PAGE;
            ps.setInt(paramIndex++, offset);
            ps.setInt(paramIndex++, ELEMENTS_PER_PAGE);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    News n = new News();
                    n.setNewId(rs.getInt("NewID"));
                    Topic topic = new Topic(rs.getInt("TopicID"), rs.getString("TopicName"));
                    n.setTopic(topic);
                    n.setTitle(rs.getString("Title"));
                    n.setContent(rs.getString("Content"));
                    n.setImgNews(rs.getString("Img"));
                    n.setDateUpload(rs.getDate("DateUpload"));
                    n.setSource(rs.getString("Source"));
                    n.setStatus(rs.getBoolean("Status")); // Set status
                    listNews.add(n);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listNews;
    }

    public int getFilteredNewsCount(String status, int topicId) {
        String query = "SELECT COUNT(*) AS total FROM News WHERE 1=1 ";
        if (status != null && !status.isEmpty()) {
            query += " AND Status = ? ";
        }
        if (topicId != -1) {
            query += " AND TopicID = ? ";
        }
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            int paramIndex = 1;
            if (status != null && !status.isEmpty()) {
                ps.setBoolean(paramIndex++, Boolean.parseBoolean(status));
            }
            if (topicId != -1) {
                ps.setInt(paramIndex++, topicId);
            }
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

}
