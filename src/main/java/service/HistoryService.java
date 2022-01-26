package service;

import config.DbConfigUsers;
import database.HistoryDb;
import database.UserDb;
import entity.model.User;

import java.util.Scanner;

public class HistoryService {

    public static final Scanner SCANNER_NUM = new Scanner(System.in);
    public static final Scanner SCANNER_STR = new Scanner(System.in);

    public static void HistoryCRUD(User user) {
        System.out.println("1.Reade history");
        System.out.println("2.Delet history");
        System.out.println("0.Go back");
        byte chek = SCANNER_NUM.nextByte();
        switch (chek) {
            case 1: {
                System.out.println("1.All user history reade");
                System.out.println("2.One user history reade");
                byte operation = SCANNER_NUM.nextByte();
                if (operation == 1) {
                    for (int i = 0; i < HistoryDb.getHistory().size(); i++) {
                        System.out.println("\n\n===================>>> " + i + " - USER <<<===================\n");
                        HistoryDb.getHistory().get(i);
                    }
                }
            }
            break;
            case 2: {
                System.out.println("Bu hozircha ishlamaydi uzur!!");
//                for (int i = 0; i < DbConfigUsers.OpenUserDatabase().size(); i++) {
//                    System.out.println("\n\n===================>>> " + i + " - USER <<<===================\n");
//                    System.out.println("User Id = " + DbConfigUsers.OpenUserDatabase().get(i).getId());
//                    System.out.println("User first name = " + DbConfigUsers.OpenUserDatabase().get(i).getFirstName());
//                    System.out.println("User email = " + DbConfigUsers.OpenUserDatabase().get(i).getEmail());
//                }
//
//                System.out.println("Enter user id: ");
//                int userId = SCANNER_NUM.nextInt();
//

            }
            break;
            case 0: {
                return;
            }
        }
        HistoryCRUD(user);
    }
}
