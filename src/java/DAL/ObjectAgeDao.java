/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.ObjectAge;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author huyca
 */
public class ObjectAgeDao extends DBContext{
    //Hiển thị toàn bộ objectAge
     public List<ObjectAge> getallObjectAges() {
        List<ObjectAge> objectAges = new ArrayList<>();
        String query = "Select * From ObjectAge";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ObjectAge objectAge = new ObjectAge();
                objectAge.setAgeId(rs.getInt("ageId"));
                objectAge.setAge(rs.getString("age"));
                objectAges.add(objectAge);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return objectAges;
    }
     
     //Lấy object age theo ageID
     public ObjectAge getObjectAgesByID(int id) {
        List<ObjectAge> objectAges = new ArrayList<>();
        String query = "Select * From ObjectAge WHERE AgeID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ObjectAge objectAge = new ObjectAge();
                objectAge.setAgeId(rs.getInt("ageId"));
                objectAge.setAge(rs.getString("age"));
                return objectAge;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
     
     public static void main(String[] args) {
        //test Lấy object age theo ageID
        ObjectAgeDao od = new ObjectAgeDao();
        ObjectAge o = od.getObjectAgesByID(1);
         System.out.println(o.getAge());
    }
}
