package database;

import config.DbConfig;
import entity.model.UserAnswer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserAnswerDb {

    public static List<UserAnswer> getUserAnswerList() {
        List<UserAnswer> userAnswerList = new ArrayList<>();

        Connection connection = DbConfig.getConnection();

        Statement statement;

        {
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from user_answer");

                while (resultSet.next()) {
                    UserAnswer userAnswer = new UserAnswer();
                    userAnswer.setUserId(resultSet.getInt(1));
                    userAnswer.setQuestionId(resultSet.getInt(2));
                    userAnswer.setUserId(resultSet.getInt(3));
                    userAnswer.setGivenAnswer(resultSet.getString(4));
                    userAnswerList.add(userAnswer);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userAnswerList;
    }
    public static List<UserAnswer> getUserAnswerList(int user_id) {
        List<UserAnswer> userAnswerList = new ArrayList<>();

        Connection connection = DbConfig.getConnection();

        Statement statement;

        {
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from user_answer where users_id =" + user_id);

                while (resultSet.next()) {
                    UserAnswer userAnswer = new UserAnswer();
                    userAnswer.setUserId(resultSet.getInt(1));
                    userAnswer.setQuestionId(resultSet.getInt(2));
                    userAnswer.setGivenAnswer(resultSet.getString(3));
                    userAnswer.setUserId(resultSet.getInt(4));
                    userAnswerList.add(userAnswer);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userAnswerList;
    }
}
