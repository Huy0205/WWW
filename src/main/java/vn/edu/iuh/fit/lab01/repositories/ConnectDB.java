package vn.edu.iuh.fit.lab01.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static ConnectDB instance;
    private Connection connection;

    public ConnectDB(){
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/www_lab01", "root", "password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ConnectDB getInstance() {
        if(instance == null)
            instance = new ConnectDB();
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
