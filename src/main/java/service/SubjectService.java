package service;

import config.DbConfig;
import database.SubjectDb;
import dto.ConnectionDb;
import entity.model.Subject;
import entity.model.User;

import java.sql.*;
import java.util.Scanner;

public class SubjectService {
    public static final Scanner SCANNER_NUM = new Scanner(System.in);
    public static final Scanner SCANNER_STR = new Scanner(System.in);

    public static void SubjectCRUD(User user) {
        do {
            System.out.println("1.Craete Subject");
            System.out.println("2.Read Subject");
            System.out.println("3.Update Subject");
            System.out.println("4.Delete Subject");
            System.out.println("0.Go bac");
            byte operation = SCANNER_NUM.nextByte();
            switch (operation) {
                case 1: {
                    CreateSubject();
                }
                break;
                case 2: {
                    ReadSubject();
                }
                break;
                case 3: {
                    UpdateSubject();
                }
                break;
                case 4: {
                    DeleteSubject();
                }
                break;
                case 0: {
                    return;
                }
            }

        } while (true);
    }

    private static void CreateSubject() {
            String subjectName;
            do {
            boolean operation = false;
                System.out.println("Entre subject name: ");
                subjectName = SCANNER_STR.nextLine();

                for (Subject subject : SubjectDb.getSubject()) {
                    if (subject.getName().toLowerCase().equals(subjectName)) {
                        if (!subject.isActive()) {
                            System.out.println("This subject already exists");
                            System.out.println("But, this subject is not active");
                            System.out.println("Siz bu subjectni active qilishni istaysizmi?(1.yes, 2.no)");
                            String chek = SCANNER_STR.nextLine();
                            if (chek.toLowerCase().equals("1") || chek.toLowerCase().equals("yes")) {
                                Connection connection = DbConfig.getConnection();
                                try {
                                    Statement statement = connection.createStatement();
                                    boolean resultSet = statement.execute("UPDATE subject SET active = true WHERE id =" + subject.getId());
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }

                            }
                            operation=false;
                            break;
                        }
                        System.out.println("This subject already exists");
                        operation = true;
                        break;
                    }
                }
                if (!operation) {
                    Connection connection = DbConfig.getConnection();
                    try {
                        PreparedStatement preparedStatement = connection.prepareStatement("insert into subject (name, active) values (?,?)");
                        preparedStatement.setString(1, subjectName);
                        preparedStatement.setBoolean(2, true);
                        boolean resultSet = preparedStatement.execute();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                operation=true;
                System.out.println("New subject added");
                System.out.println("Press the '0' button to complete the operation");
                System.out.println("Davom etishini istasangiz istasangiz '1' ni bosing");
                byte chek = SCANNER_NUM.nextByte();
                if (chek == 0) {
                    return;
                }
            } while (true);
    }

    public static void ReadSubject() {
        int count = 0;
        for (Subject subject : SubjectDb.getSubject()) {
            count++;
            System.out.println("=============================>> SUBJECT " + count + (" <<============================="));
            System.out.println(subject);
            System.out.println();
        }
    }

    private static void UpdateSubject() {
        ReadSubject();
        System.out.println("Enter subject id: ");
        int subjectID = SCANNER_NUM.nextInt();
        for (Subject subject : SubjectDb.getSubject()) {
            if (subject.getId() == subjectID) {
                System.out.println("1.Update name");
                System.out.println("2.Update active");
                byte chek = SCANNER_NUM.nextByte();
                switch (chek) {
                    case 1: {
                        System.out.print("Enter new subject name: ");
                        String newSubjectName = SCANNER_STR.nextLine();

                        for (Subject subject1 : SubjectDb.getSubject()) {
                            if (subject1.getName().equals(newSubjectName)) {
                                System.out.println("This subject name already exist");
                                System.out.println("Amaliyotni yakunlashni istaysizmi? 1.yes, 2.no");
                                String tanlov = SCANNER_STR.nextLine();
                                if (tanlov.equals("1") || tanlov.toLowerCase().equals("yes")){
                                    return;
                                }
                                UpdateSubject();
                            }
                        }

                        Connection connection = DbConfig.getConnection();
                        try {
                            Statement statement = connection.createStatement();
                            boolean resultSet = statement.execute("update subject set name = '" + newSubjectName + "' where id =" + subject.getId());
                            System.out.println("Subject name updated");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    }
                    break;
                    case 2: {

//                        System.out.print("Enter subject id: ");
//                        int updateSubjectId = SCANNER_NUM.nextInt();
                        for (Subject subject1 : SubjectDb.getSubject()) {
                            if (subject1.getId() == subjectID) {
                                System.out.println("This subject hozir " + subject1.isActive());
                                System.out.println("Rostdan ham uni o`zgartirishni istaysizmi(1.yes, 2.no): ");
                                String chek1 = SCANNER_STR.nextLine();
                                if (chek1.equals("1") || chek1.toLowerCase().equals("yes")) {
                                    System.out.println("Endi u " + !subject1.isActive());

                                    Connection connection = DbConfig.getConnection();
                                    try {
                                        Statement statement = connection.createStatement();
                                        boolean resultSet = statement.execute("UPDATE subject SET active = " + !subject1.isActive() + " WHERE id =" + subjectID);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    return;
                                } else {
                                    return;
                                }
                            }
                        }
                        System.out.println("Bunday id dagi subject yo`q");
                    }break;
                }
                break;
            }

        }
    }

    private static void DeleteSubject() {
        ReadSubject();

        System.out.println("Enter subject id: ");
        int deletSubjectId = SCANNER_NUM.nextInt();

        for (Subject subject : SubjectDb.getSubject()) {
            if (subject.getId()==deletSubjectId){
                ConnectionDb.Connection("UPDATE subject SET active = " + false + " WHERE id =" + deletSubjectId);
                return;
            }
        }
    }
}
