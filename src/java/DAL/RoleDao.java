/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Account;
import Models.Role;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huyca
 */
public class RoleDao extends DBContext {

    public List<Role> getAllRole() {
        List<Role> role = new ArrayList<>();
        String query = "Select * From Role";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Role roles = new Role();
                roles.setRoleId(rs.getInt("roleId"));
                roles.setRoleName(rs.getString("roleName"));
                role.add(roles);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return role;
    }
    public static void main(String[] args) {
        RoleDao roleDao = new  RoleDao();
        List<Role> roles = roleDao.getAllRole();
        for (Role role : roles) {
            System.out.println(role);
        }
    }
}
