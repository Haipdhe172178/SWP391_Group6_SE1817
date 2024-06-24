/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Account;
import Models.Author;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huyca
 */
public class AuthorDao extends DBContext {

    public List<Author> getallAuthors() {
        List<Author> authors = new ArrayList<>();
        String query = "Select * From Author";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Author author = new Author();
                author.setAuthorID(rs.getInt("authorID"));
                author.setAuthorName(rs.getString("authorName"));
                author.setDescription(rs.getString("description"));
                author.setStatus(rs.getInt("status"));
                authors.add(author);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return authors;
    }

    //Get author by authorID
    public Author getAuthorById(int id) {
        String query = "Select * From Author WHERE AuthorID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Author author = new Author();
                author.setAuthorID(rs.getInt("authorID"));
                author.setAuthorName(rs.getString("authorName"));
                author.setDescription(rs.getString("description"));
                author.setStatus(rs.getInt("status"));
                return author;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Author getAuthorByKeyword(String keyword) {
        String query = "SELECT * FROM Author WHERE AuthorName LIKE ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Author author = new Author();
                author.setAuthorID(rs.getInt("authorID"));
                author.setAuthorName(rs.getString("authorName"));
                author.setDescription(rs.getString("description"));
                return author;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Author getAuthorByName(String name) {
        String query = "SELECT * FROM Author WHERE AuthorName = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Author author = new Author();
                author.setAuthorID(rs.getInt("authorID"));
                author.setAuthorName(rs.getString("authorName"));
                author.setDescription(rs.getString("description"));
                return author;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
     public boolean getAuthor(String name) {
        String query = "SELECT 1 FROM Author WHERE AuthorName = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public List<Author> pagingAuthors(int index) {
        List<Author> list = new ArrayList<>();
        String query = "SELECT * FROM Author "
                + "ORDER BY AuthorID "
                + "OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, (index - 1) * 8);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Author author = new Author();
                author.setAuthorID(rs.getInt("authorID"));
                author.setAuthorName(rs.getString("authorName"));
                author.setDescription(rs.getString("description"));
                author.setStatus(rs.getInt("status"));
                list.add(author);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public int getTotalAuthors() {
        int total = 0;
        String query = "SELECT COUNT(*) FROM Author";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return total;
    }

    public void insertAuthor(Author author) {
        String query = "INSERT INTO Author (authorName, description, status) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, author.getAuthorName());
            ps.setString(2, author.getDescription());
            ps.setInt(3, author.getStatus());
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateAuthor(Author author) {
        String query = "UPDATE Author SET AuthorName = ?, description = ?, status = ? WHERE AuthorID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, author.getAuthorName());
            ps.setString(2, author.getDescription());
            ps.setInt(3, author.getStatus());
            ps.setInt(4, author.getAuthorID());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideAuthor(int authorId) {
        String query = "UPDATE author SET status = 0 WHERE authorID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, authorId);
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Author> pagingSearchAuthor(int index, String keyword) {
        List<Author> list = new ArrayList<>();
        String query = "SELECT * FROM Author WHERE AuthorName LIKE ? ORDER BY AuthorID OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setInt(2, (index - 1) * 8);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Author author = new Author();
                author.setAuthorID(rs.getInt("authorID"));
                author.setAuthorName(rs.getString("authorName"));
                author.setDescription(rs.getString("description"));
                author.setStatus(rs.getInt("status"));
                list.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getTotalAuthorsByKeyword(String keyword) {
        int total = 0;
        String query = "SELECT COUNT(*) FROM Author WHERE AuthorName LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public List<Author> getAuthorByStatus(int status, int index) {
        List<Author> authors = new ArrayList<>();
        String query = "Select * From Author Where Status = ?"
                + " Order By AuthorID OFFSET ? Rows Fetch Next 8 Rows Only";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, status);
            ps.setInt(2, (index - 1) * 8);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Author author = new Author();
                author.setAuthorID(rs.getInt("authorID"));
                author.setAuthorName(rs.getString("authorName"));
                author.setDescription(rs.getString("description"));
                author.setStatus(rs.getInt("status"));
                authors.add(author);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return authors;
    }

    public int getTotalBySatus(int status) {
         int total = 0;
         String query ="Select Count(*) From Author Where Status = ?";
         try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, status);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
}
