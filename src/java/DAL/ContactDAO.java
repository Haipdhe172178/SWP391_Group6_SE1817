package DAL;

import Models.Contact;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO extends DBContext {
    public List<Contact> getAll() {
        List<Contact> contacts = new ArrayList<>();
        String query = "Select * From Contact";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setUserName(rs.getString("userName"));
                contact.setEmail(rs.getString("email"));
                contact.setPhoneNumber(rs.getString("phoneNumber"));
                contact.setTopic(rs.getString("topic"));
                contact.setMessage(rs.getString("message"));
                contacts.add(contact);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return contacts;
    }

    public boolean insertContact(Contact contact) {
        String query = "INSERT INTO Contact (userName, email, phoneNumber, topic, message) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, contact.getUserName());
            ps.setString(2, contact.getEmail());
            ps.setString(3, contact.getPhoneNumber());
            ps.setString(4, contact.getTopic());
            ps.setString(5, contact.getMessage());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
