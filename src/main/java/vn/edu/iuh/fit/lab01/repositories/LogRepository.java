package vn.edu.iuh.fit.lab01.repositories;

import vn.edu.iuh.fit.lab01.entities.Account;
import vn.edu.iuh.fit.lab01.entities.Log;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class LogRepository {
    private Connection connection;
    private AccountRepository accountRepository;

    public LogRepository() {
        connection = ConnectDB.getInstance().getConnection();
    }

    public Log get(String accountID){
        accountRepository = new AccountRepository();
        Log log = null;
        try {
            PreparedStatement ps = connection.prepareStatement("select * from log where account_id = ?");
            ps.setString(1, accountID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Account account = accountRepository.get(rs.getString("account_id"));
                LocalDateTime loginTime = LocalDateTime.of(rs.getDate("login_time").toLocalDate(), rs.getTime("login_time").toLocalTime());
                LocalDateTime logoutTime = LocalDateTime.of(rs.getDate("logout_time").toLocalDate(), rs.getTime("logout_time").toLocalTime());
                log = new Log(rs.getLong("id"), account, loginTime, logoutTime, rs.getString("note"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return log;
    }

    public ArrayList getAll(){
        accountRepository = new AccountRepository();
        ArrayList logs = new ArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from log");
            while (rs.next()){
                Account account = accountRepository.get(rs.getString("account_id"));
                LocalDateTime loginTime = LocalDateTime.of(rs.getDate("login_time").toLocalDate(), rs.getTime("login_time").toLocalTime());
                LocalDateTime logoutTime = LocalDateTime.of(rs.getDate("logout_time").toLocalDate(), rs.getTime("logout_time").toLocalTime());
                logs.add(new Log(rs.getLong("id"), account, loginTime, logoutTime, rs.getString("note")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return logs;
    }

    public boolean add(Log log){
        int n = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("insert into Log(id, account_id, login_time, logout_time, notes) values(?, ?, ?, ?, ?)");
            ps.setLong(1, log.getId());
            ps.setString(2, log.getAccount().getId());
            ps.setString(3, log.getLoginDateTime().toString());
            ps.setString(4, log.getLogoutDateTime().toString());
            ps.setString(5, log.getNote());
            n = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return n > 0;
    }

    public boolean update(Log log){
        int n = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("update log set notes = ? where id = ?");
            ps.setString(1, log.getNote());
            ps.setLong(2, log.getId());
            n = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return n > 0;
    }
}
