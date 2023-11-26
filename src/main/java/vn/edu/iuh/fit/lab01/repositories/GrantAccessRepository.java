package vn.edu.iuh.fit.lab01.repositories;

import vn.edu.iuh.fit.lab01.entities.Account;
import vn.edu.iuh.fit.lab01.entities.GrantAccess;
import vn.edu.iuh.fit.lab01.entities.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GrantAccessRepository {
    private Connection connection;
    private RoleRepository roleRepository = new RoleRepository();
    private AccountRepository accountRepository = new AccountRepository();

    public GrantAccessRepository() {
        connection = ConnectDB.getInstance().getConnection();
    }

    public GrantAccess get(String roleID, String accountID){
        GrantAccess grantAccess = null;
        try {
            PreparedStatement ps = connection.prepareStatement("select * from grant_access where role_id = ? and account_id = ?");
            ps.setString(1, roleID);
            ps.setString(2, accountID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Role role = roleRepository.get(rs.getString("role_id"));
                Account account = accountRepository.get(rs.getString("account_id"));
                grantAccess = new GrantAccess(account, role, rs.getBoolean("is_grant"), rs.getString("note"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return grantAccess;
    }

    public ArrayList getAll(){
        ArrayList listGrantAccess = new ArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from grant_access");
            while (rs.next()){
                Role role = roleRepository.get(rs.getString("role_id"));
                Account account = accountRepository.get(rs.getString("account_id"));
                listGrantAccess.add(new GrantAccess(account, role, rs.getBoolean("is_grant"), rs.getString("note")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listGrantAccess;
    }

    public boolean add(GrantAccess grantAccess){
        int n = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("insert into grant_access(role_id, account_id, is_grant, note) values(?, ?, ?, ?)");
            ps.setString(1, grantAccess.getRole().getId());
            ps.setString(2, grantAccess.getAccount().getId());
            ps.setBoolean(3, grantAccess.isGrant());
            ps.setString(4, grantAccess.getNote());
            n = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return n > 0;
    }

    public boolean update(GrantAccess grantAccess){
        int n = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("update grant_access set is_grant = ?, note = ? where role_id = ? and account_id = ?");
            ps.setBoolean(1, grantAccess.isGrant());
            ps.setString(2, grantAccess.getNote());
            ps.setString(3, grantAccess.getRole().getId());
            ps.setString(4, grantAccess.getAccount().getId());
            n = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return n > 0;
    }

    public boolean delete(String roleID, String accountID){
        int n = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("delete grant_access where role_id = ? and account_id = ?");
            ps.setString(1, roleID);
            ps.setString(2, accountID);
            n = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return n > 0;
    }

    public ArrayList<GrantAccess> findGrantByAccountId(String id){
        ArrayList<GrantAccess> listGrantAccess = new ArrayList<GrantAccess>();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from grant_access where account_id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Role role = roleRepository.get(rs.getString("role_id"));
                Account account = accountRepository.get(rs.getString("account_id"));
                listGrantAccess.add(new GrantAccess(account, role, rs.getBoolean("is_grant"), rs.getString("note")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listGrantAccess;
    }
}
