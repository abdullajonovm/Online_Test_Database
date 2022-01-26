package service;

import database.UserAnswerDb;
import entity.model.User;
import entity.model.UserAnswer;

import java.util.Scanner;

public class UserAnswerService {

    public static final Scanner SCANNER_NUM = new Scanner(System.in);
    public static final Scanner SCANNER_STR = new Scanner(System.in);

    public static void UseerAnsverCRUD(User user) {
        UserService.ReadUser(new User());
        System.out.print("Enter user id: ");
        int userId = SCANNER_NUM.nextInt();
        for (UserAnswer userAnswer : UserAnswerDb.getUserAnswerList(userId)) {
            System.out.println(userAnswer);
        }


    }
}
