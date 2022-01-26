package config;

import entity.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbConfigUsers {
    public static List<User> OpenUserDatabase(){
        //                Subject subject = new Subject();
        //                subject.setId(resultSet.getInt(1));
        //                subject.setName(resultSet.getString(2));
        //                subject.setActive(resultSet.getBoolean(3));
        //
        //                subjectList.add(subject);
        //            }
        //        } catch (SQLException e) {
        //            e.printStackTrace();
        //        }
        //
        //        return subjectList;
        List<User> userList = new ArrayList<>();

        Connection connection = DbConfig.getConnection();

        Statement statement;

        {
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from users ");

                while (resultSet.next()){
                    User user = new User();
                    user.setId(resultSet.getInt(1));
                    user.setFirstName(resultSet.getString(2));
                    user.setLastName(resultSet.getString(3));
                    user.setPhone(resultSet.getString(4));
                    user.setPassword(resultSet.getString(5));
                    user.setActive(resultSet.getBoolean(6));
                    user.setRole(resultSet.getString(7));
                    user.setEmail(resultSet.getString(8));
                    userList.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userList;
    }

}
