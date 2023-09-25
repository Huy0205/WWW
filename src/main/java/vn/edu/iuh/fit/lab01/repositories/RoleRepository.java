package vn.edu.iuh.fit.lab01.repositories;

import vn.edu.iuh.fit.lab01.entities.AccountStatus;
import vn.edu.iuh.fit.lab01.entities.Role;
import vn.edu.iuh.fit.lab01.entities.RoleStatus;

import java.sql.*;
import java.util.ArrayList;

public class RoleRepository {
    private Connection connection;

    public RoleRepository() {
        connection = ConnectDB.getInstance().getConnection();
    }

    public Role get(String roleID){
        Role role = null;
        try {
            PreparedStatement ps = connection.prepareStatement("select * from role where role_id = ?");
            ps.setString(1, roleID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                RoleStatus status;
                if(rs.getInt("status") == 1){
                    status = RoleStatus.ACTIVE;
                }else if(rs.getInt("status") == 0){
                    status = RoleStatus.DEACTIVE;
                }else{
                    status = RoleStatus.REMOVE;
                }
                role = new Role(rs.getString("role_id"), rs.getString("role_name"), rs.getString("discription"), status);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return role;
    }

    public ArrayList getAll(){
        ArrayList roles = new ArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from role");
            while (rs.next()){
                RoleStatus status;
                if(rs.getInt("status") == 1){
                    status = RoleStatus.ACTIVE;
                }else if(rs.getInt("status") == 0){
                    status = RoleStatus.DEACTIVE;
                }else{
                    status = RoleStatus.REMOVE;
                }
                roles.add(new Role(rs.getString("role_id"), rs.getString("role_name"), rs.getString("discription"), status));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roles;
    }

    public boolean add(Role role){
        int n = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("insert into role(role_id, role_name, description, status) values(?, ?, ? ?)");
            ps.setString(1, role.getId());
            ps.setString(2, role.getName());
            ps.setString(3, role.getDescription());
            ps.setInt(4, role.getStatus().getValue());
            n = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return n > 0;
    }

    public boolean update(Role role){
        int n = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("update role set role_name = ?, description = ?, status = ? where role_id = ?");
            ps.setString(1, role.getName());
            ps.setString(2, role.getDescription());
            ps.setInt(3, role.getStatus().getValue());
            ps.setString(4, role.getId());
            n = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return n > 0;
    }

    public boolean delete(String roleID){
        int n = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("delete role where role_id = ?");
            ps.setString(1, roleID);
            n = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return n > 0;
    }
}
