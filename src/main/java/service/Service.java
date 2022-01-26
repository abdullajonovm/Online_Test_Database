package service;

import config.DbConfig;
import config.DbConfigUsers;
import entity.model.User;

import java.sql.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Service {

    public static final Scanner SCANNER_NUM = new Scanner(System.in);
    public static final Scanner SCANNER_STR = new Scanner(System.in);

    public static void SignUp() {
        User user = new User();

        System.out.println("Eter first name: ");
        String firstName = SCANNER_STR.nextLine();
        user.setFirstName(firstName);

        System.out.println("Enter last name: ");
        String lastName = SCANNER_STR.nextLine();
        user.setLastName(lastName);

        System.out.println("Enter phone number(+998881234567): ");
        String phoneNum = SCANNER_STR.nextLine();
        boolean chekNum = false;
        if (phoneNum.indexOf("+998") != 0 && phoneNum.length() != 13) {
            chekNum = true;
        } else {
            user.setPhone(phoneNum);
        }

        System.out.println("Enter email(ketmon@gmail.com)");
        String email = SCANNER_STR.nextLine();
        user.setEmail(email);
        boolean chekEmail = false;
        if (email.length() <= 10 || email.lastIndexOf("@gmail.com") == -1 || !ChekService.ChekEmail(email)) {
            chekEmail = true;
        }
        System.out.println("Enter password: ");
        String password = SCANNER_STR.nextLine();
        user.setPassword(password);
        if (chekNum){
            System.out.println("Wrong number");
        }
        if (chekEmail){
            System.out.println("Wrong email");
        }
        if (chekNum || chekEmail) {
            System.out.println(chekNum+ "N");
            System.out.println(chekEmail + "E");
            System.out.println("There is an error in your email or phone number, check and try again!");
            System.out.println("1.Please try again");
            System.out.println("2.Go back");
            byte operation = SCANNER_NUM.nextByte();

            switch (operation) {
                case 1: {
                    SignUp();
                }
                break;
                default:
                    return;
            }

        }

        if (!UserService.AddUser(user)) {
            System.out.println("You have successfully registered");
            UserService.UserPage(user);
        } else {

        }
    }

    public static void SignIn() {
        System.out.println("Enter email: ");
        String email = SCANNER_STR.nextLine();


        List<User> userList = DbConfigUsers.OpenUserDatabase();

        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                //User bolsa va uning activesi false bolsa qayta register qilmoqchi bolsa falsini true qib qoyaman
                if (!(user.isActive() && user.getRole().toLowerCase().equals("admin"))) {
                    Connection connection = DbConfig.getConnection();
                    try {
                        Statement statement = connection.createStatement();
                        boolean resultSet = statement.execute("UPDATE users SET active = true WHERE id =" + user.getId());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Enter password: ");
                String password = SCANNER_STR.nextLine();
                if (user.getPassword().equals(password)) {
                    if (user.getRole().toLowerCase().equals("admin")) {
                        if (user.isActive()) {
                            UserService.AdminPage(user);
                        } else {
                            System.out.println("You are not in an active state");
                            System.out.println("Contact the Supper administrator. His phone number : \n" +
                                    "+998950035369");
                        }
                    } else {
                        UserService.UserPage(user);
                    }
                } else {
                    System.out.println("Password no correct.");
                    System.out.println("Please try again");
                    System.out.println("Forgot your password?(1.yes, 2.no)");
                    String chek = SCANNER_STR.nextLine();
                    if (chek.equals("1") || chek.equals("yes")) {
                        boolean counter = true;
                        do {

                            System.out.println("The password was sent to:\n " + user.getPhone().substring(0, 5) + "....." +
                                    user.getPhone().substring(10) + "\n");
                            String randomParol = "" + new Random().nextInt(1000, 9999);
                            System.out.println("Password sent to number: " + randomParol);
                            System.out.println("Please enter a password: ");
                            String parol = SCANNER_STR.nextLine();
                            if (parol.equals(randomParol)) {
                                counter = false;
                            }
                        } while (counter);


                        //Userning passwordini o`zgartirish
                        System.out.println("Enter new password: ");
                        String newPassword = SCANNER_STR.nextLine();

                        Connection connection = DbConfig.getConnection();

                        try {
                            PreparedStatement preparedStatement = connection.prepareStatement(
                                    "UPDATE users SET password = ? WHERE id = ?;"
                            );

                            preparedStatement.setString(1, newPassword);
                            preparedStatement.setInt(2, user.getId());
                            boolean resultSet = preparedStatement.execute();
                            if (!resultSet) {
                                System.out.println("The internship was completed successfully");
                            } else {
                                System.out.println("The practice failed");
                                UserService.UserPage(user);
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }


                    } else {
                        System.out.println("Good by!");
                        return;
                    }

                }
            }
        }

    }
}
