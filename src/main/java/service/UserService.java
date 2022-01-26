package service;

import config.DbConfig;
import database.HistoryDb;
import dto.ConnectionDb;
import entity.model.History;
import entity.model.User;

import java.sql.*;
import java.util.Scanner;

public class UserService {
    public static final Scanner SCANNER_NUM = new Scanner(System.in);
    public static final Scanner SCANNER_STR = new Scanner(System.in);

    public static boolean AddUser(User user) {
        Connection connection = DbConfig.getConnection();
        Statement statement = null;
        User user1 = null;

//        PreparedStatement preparedStatement = null;
        boolean resultSet = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into users (first_name, last_name, phone, password, active, role, email)\n" +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)\n");
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setBoolean(5, user.isActive());
            preparedStatement.setString(6, user.getRole());
            preparedStatement.setString(7, user.getEmail());
            resultSet = preparedStatement.execute();
//            ResultSet resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static void AdminPage(User user) {
        do {

            System.out.println("1.User CRUD");
            System.out.println("2.Subject CRUD");
            System.out.println("3.Question CRUD");
            System.out.println("4.Variant answer CRUD");
            System.out.println("5.History CRUD");
            System.out.println("6.User answer CRUD");
            System.out.println("0.Go bac");
            int operation = SCANNER_NUM.nextInt();
            switch (operation) {
                case 1: {
                    UserService.UserCRUD(user);
                }
                break;
                case 2: {
                    SubjectService.SubjectCRUD(user);
                }
                break;
                case 3: {
                    QuestionService.QuestionCRUD(user);
                }
                break;
                case 4: {
                    VariantAnswerService.VariantAnswerCRUD(user);
                }
                break;
                case 5: {
                    HistoryService.HistoryCRUD(user);
                }
                break;
                case 6: {
                    UserAnswerService.UseerAnsverCRUD(user);
                }
                break;
                case 0: {
                    Service.SignIn();
                }
            }

        } while (true);
    }

    private static void UserCRUD(User user) {
        do {
            System.out.println("1.Read user.");
            System.out.println("2.Delet user");
            System.out.println("0.Go bac");
            byte operation = SCANNER_NUM.nextByte();
            switch (operation) {
                case 1: {
                    ReadUser(user);
                }
                break;
                case 2: {
                    DeletUser(user);
                }
                break;
                case 0: {
                    AdminPage(user);
                }
                break;
            }
        } while (true);
    }

    private static void DeletUser(User user) {
        ReadUser(user);


        System.out.println("Enter user id: ");
        int userId = SCANNER_NUM.nextInt();
        Connection connection = DbConfig.getConnection();
        try {
            Statement statement = connection.createStatement();
            boolean resultSet = statement.execute("UPDATE users SET active = false WHERE id =" + userId);
            if (resultSet) {
                System.out.println("The practice failed");
            } else {
                System.out.println("The internship was completed successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        UserCRUD(user);


    }

    public static void ReadUser(User user) {
        Connection connection = DbConfig.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");
            int conut = 0;
            while (resultSet.next()) {
                conut++;
                System.out.println("=============================>> USER " + conut + (" <<============================="));
                System.out.println("Id: " + resultSet.getInt(1));
                System.out.println("FirstName: " + resultSet.getString(2));
                System.out.println("LastName: " + resultSet.getString(3));
                System.out.println("Phone number: " + resultSet.getString(4));
                System.out.println("Password: " + resultSet.getString(5));
                System.out.println("Active: " + resultSet.getBoolean(6));
                System.out.println("Role: " + resultSet.getString(7));
                System.out.println("Email: " + resultSet.getString(8));
                System.out.println();
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void UserPage(User user) {
        System.out.println("Bizning online test platformamizga hush kelibsiz");
        do {
            System.out.println("1.Doing test");
            System.out.println("2.My History test");
            System.out.println("3.Update password");
            System.out.println("0.Exit");
            byte chek = SCANNER_NUM.nextByte();
            switch (chek) {
                case 1: {
                    SubjectService.ReadSubject();
                    System.out.println("Enter subject id: ");
                    int subjectId = SCANNER_NUM.nextInt();
                    System.out.println("Ishlamoqchi bo'lgan testlaringizning sonini kiriting: ");
                    int countTest = SCANNER_NUM.nextInt();
                    TestService.DoingTest(countTest, subjectId, user);
                }
                break;
                case 2: {
                    String sql = "select * from history where user_answer_id = " + user.getId();
                    for (History history : HistoryDb.getHistory(sql)) {
                        System.out.println(history);
                    }
                }
                break;
                case 3:{
                    System.out.println("Enter new password: ");
                    String newPassword = SCANNER_STR.nextLine();
                    String updatePassword = "update users set password = '" + newPassword + "' where id = " + user.getId();
                    if (ConnectionDb.Connection(updatePassword)){
                        System.out.println("Amaliyot muvaffaqiyatli amalga oshirildi.");
                    }

                }break;
                case 0: {
                    return;
                }
            }
        } while (true);
    }

}
