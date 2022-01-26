package database;

import config.DbConfig;
import entity.model.Question;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestionDb {
    public static List<Question> getQuestion(){
        List<Question> questionList = new ArrayList<>();
        Connection connection = DbConfig.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from question");
            while (resultSet.next()){
                Question question = new Question();
                question.setId(resultSet.getInt(1));
                question.setSubjecId(resultSet.getInt(2));
                question.setText(resultSet.getString(3));
                questionList.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return questionList;
    }
    public static List<Question> getQuestion(int subjectId){
        List<Question> questionList = new ArrayList<>();
        Connection connection = DbConfig.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from question where subject_id = " + subjectId);
            while (resultSet.next()){
                Question question = new Question();
                question.setId(resultSet.getInt(1));
                question.setSubjecId(resultSet.getInt(2));
                question.setText(resultSet.getString(3));
                questionList.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return questionList;
    }
}
