package database;

import config.DbConfig;
import entity.model.Subject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubjectDb {
    public static List<Subject> getSubject(){
        List<Subject> subjectList = new ArrayList<>();
        Connection connection = DbConfig.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from subject");
            while (resultSet.next()){
                Subject subject = new Subject();
                subject.setId(resultSet.getInt(1));
                subject.setName(resultSet.getString(2));
                subject.setActive(resultSet.getBoolean(3));
                subjectList.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjectList;
    }
}
