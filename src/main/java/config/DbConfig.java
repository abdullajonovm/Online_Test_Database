package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {
    public static Connection getConnection(){
        String url = "jdbc:postgresql://localhost:5432/test_database";
        String user = "postgres";
        String password = "123456qwerty";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
