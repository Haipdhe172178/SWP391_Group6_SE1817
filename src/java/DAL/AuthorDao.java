/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Author;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
                return author;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        AuthorDao authorDao = new AuthorDao();
        List<Author> authors = authorDao.getallAuthors();
        for (Author author : authors) {
            System.out.println("Author id" + author.getAuthorID());
            System.out.println("Author name" + author.getAuthorName());
        }

        //Test method getAuthorByID()
        Author aut = authorDao.getAuthorById(1);
        System.out.println(aut.getAuthorName());
    }
}