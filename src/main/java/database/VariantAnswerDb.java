package database;

import config.DbConfig;
import entity.model.VariantAnswer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VariantAnswerDb {
//    public static List<VariantAnswer> getVariantAnswerList(){
//        List<VariantAnswer> variantAnswerList = new ArrayList<>();
//        Connection connection = DbConfig.getConnection();
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("select * from variant_answer");
//            while (resultSet.next()){
//                VariantAnswer variantAnswer = new VariantAnswer();
//                variantAnswer.setId(resultSet.getInt(1));
//                variantAnswer.setQuestionId(resultSet.getInt(2));
//                variantAnswer.setName(resultSet.getString(3));
//                variantAnswer.setIscorrect(resultSet.getBoolean(4));
//                variantAnswerList.add(variantAnswer);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//        return variantAnswerList;
//    }
    public static List<VariantAnswer> getVariantAnswerList(int questionId){
        List<VariantAnswer> variantAnswerList = new ArrayList<>();
        Connection connection = DbConfig.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from variant_answer where question_id = "+questionId);
            while (resultSet.next()){
                VariantAnswer variantAnswer = new VariantAnswer();
                variantAnswer.setId(resultSet.getInt(1));
                variantAnswer.setQuestionId(resultSet.getInt(2));
                variantAnswer.setName(resultSet.getString(3));
                variantAnswer.setIscorrect(resultSet.getBoolean(4));
                variantAnswerList.add(variantAnswer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return variantAnswerList;
    }


    
}
