package dto;

import config.DbConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDb {
    public static boolean Connection(String sql){
        Connection connection = DbConfig.getConnection();
        boolean resultSet = false;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }
    public static int Connect(String sql){
        Connection connection = DbConfig.getConnection();
        int count = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
               count = resultSet.getInt(1);
            }
            //            resultSet = statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }
}
