package database;

import config.DbConfig;
import entity.model.History;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HistoryDb {
    public static List<History> getHistory(){
        List<History> historyList = new ArrayList<>();

        Connection connection = DbConfig.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from history");
            while (result.next()){
                History history = new History();
                history.setId(result.getInt(1));
                history.setDate(result.getDate(2));
                history.setPoint(result.getDouble(3));
                history.setUserAnswerId(result.getInt(4));
                historyList.add(history);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return historyList;
    }
    public static List<History> getHistory(int userId){
        List<History> historyList = new ArrayList<>();

        Connection connection = DbConfig.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from history where user_id" + userId);
            while (result.next()){
                History history = new History();
                history.setId(result.getInt(1));
                history.setDate(result.getDate(2));
                history.setPoint(result.getDouble(3));
                history.setUserAnswerId(result.getInt(4));
                historyList.add(history);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return historyList;
    }
    public static List<History> getHistory(String sql){
        List<History> historyList = new ArrayList<>();

        Connection connection = DbConfig.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                History history = new History();
                history.setId(result.getInt(1));
                history.setDate(result.getDate(2));
                history.setPoint(result.getDouble(3));
                history.setUserAnswerId(result.getInt(4));
                historyList.add(history);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return historyList;
    }
}
