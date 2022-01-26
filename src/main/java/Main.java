import config.DbConfigUsers;
import entity.model.User;
import service.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static final Scanner SCANNER_NUM = new Scanner(System.in);
    public static final Scanner SCANNER_STR = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(LocalDate.now());
        boolean operation = true;

        do {

        System.out.println("Welcome to the program");
        System.out.println("1.Sign up");
        System.out.println("2.Sign in");
        System.out.println("0.Complete the program");
        byte chek = SCANNER_NUM.nextByte();

        switch (chek){
            case 1:{
                Service.SignUp();
            } break;
            case 2:{
                Service.SignIn();
            }break;
            default: {
                operation = false;
            }
        }
        }while (operation);
    }
}
