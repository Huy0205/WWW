package vn.edu.iuh.fit.lab01.repositories;

import vn.edu.iuh.fit.lab01.entities.Account;
import vn.edu.iuh.fit.lab01.entities.AccountStatus;

import java.sql.*;
import java.util.ArrayList;

public class AccountRepository {
    private Connection connection;

    public AccountRepository(){
        connection = ConnectDB.getInstance().getConnection();
    }
    public ArrayList getAll(){
        ArrayList accounts = new ArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs =  statement.executeQuery("select * from account");
            while (rs.next()){
                AccountStatus status;
                if(rs.getInt("status") == 1){
                     status = AccountStatus.ACTIVE;
                }else if(rs.getInt("status") == 0){
                     status = AccountStatus.DEACTIVE;
                }else{
                     status = AccountStatus.REMOVE;
                }
                accounts.add(new Account(rs.getString("account_id"), rs.getString("full_name"), rs.getString("password"), rs.getString("email"), rs.getString("phone"), status));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }

    public Account get(String accountID){
        Account account = null;
        try {
            PreparedStatement ps = connection.prepareStatement("select * from account where account_id = ?");
            ps.setString(1, accountID);
            ResultSet rs =  ps.executeQuery();
            while (rs.next()){
                AccountStatus status;
                if(rs.getInt("status") == 1){
                    status = AccountStatus.ACTIVE;
                }else if(rs.getInt("status") == 0){
                    status = AccountStatus.DEACTIVE;
                }else{
                    status = AccountStatus.REMOVE;
                }
                account = new Account(rs.getString("account_id"), rs.getString("full_name"), rs.getString("password"), rs.getString("email"), rs.getString("phone"), status);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return account;
    }

    public Account get(String email, String pass){
        Account account = null;
        System.out.println("ok");
        try {
            PreparedStatement ps = connection.prepareStatement("select * from account where email = ? and password = ?");
            ps.setString(1, email);
            ps.setString(2, pass);
            ResultSet rs =  ps.executeQuery();
            while (rs.next()){
                AccountStatus status;
                if(rs.getInt("status") == 1){
                    status = AccountStatus.ACTIVE;
                }else if(rs.getInt("status") == 0){
                    status = AccountStatus.DEACTIVE;
                }else{
                    status = AccountStatus.REMOVE;
                }
                account = new Account(rs.getString("account_id"), rs.getString("full_name"), rs.getString("password"), rs.getString("email"), rs.getString("phone"), status);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return account;
    }

    public boolean add(Account account){
        int n = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("insert into account(acount_id, full_name, password, email, phone, status) values(?,?,?,?,?,?,?)");
            ps.setString(1, account.getId());
            ps.setString(2, account.getFullName());
            ps.setString(3, account.getPassword());
            ps.setString(4, account.getEmail());
            ps.setString(5, account.getPhone());
            ps.setInt(6, account.getStatus().getValue());
            n = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    return n > 0;
    }

    public boolean update(Account account){
        int n = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("update account set full_name = ?, password = ?, email = ?, phone = ?, status = ? where account_id = ?");
            ps.setString(1, account.getFullName());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getEmail());
            ps.setString(4, account.getPhone());
            ps.setInt(5, account.getStatus().getValue());
            ps.setString(6, account.getId());
            n = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return n > 0;
    }

    public boolean delete(String accountID){
        int n = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("delete account where account_id = ?");
            ps.setString(1, accountID);
            n = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return n > 0;
    }

    public static void main(String[] args) {
        AccountRepository repository = new AccountRepository();
        ArrayList accounts = repository.getAll();
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(accounts.get(i));
        }
    }
}
