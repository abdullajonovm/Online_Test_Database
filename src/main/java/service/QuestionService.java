package service;

import database.QuestionDb;
import dto.ConnectionDb;
import entity.model.Question;
import entity.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class QuestionService {

    public static final Scanner SCANNER_NUM = new Scanner(System.in);
    public static final Scanner SCANNER_STR = new Scanner(System.in);

    public static void QuestionCRUD(User user) {
        do {
            SubjectService.ReadSubject();
            System.out.println("Eneter subject id: ");
            int subjectId = SCANNER_NUM.nextInt();
           boolean operation = true;
            do {
                System.out.println("\n\n>>>>>>>>>>>>===================================================<<<<<<<<<<<<\n");
                System.out.println("1.Creade question.");
                System.out.println("2.Read question.");
                System.out.println("3.Update question.");
                System.out.println("4.Delete question.");
                System.out.println("0.Go back");
                byte chek = SCANNER_NUM.nextByte();
                switch (chek) {
                    case 1: {
                        CreateQuestion(subjectId);
                    }
                    break;
                    case 2: {
                        ReadeQuestion(subjectId);
                    }
                    break;
                    case 3: {
                        UpdateQuestion(subjectId);
                    }
                    break;
                    case 4: {
                        DeleteQuestion(subjectId);
                    }
                    break;
                    case 0: {
                        operation = false;
                    }
                }
            } while (operation);
            System.out.println("Amaliyotni yakunlashni istaysizmi? 1.yes, 2.no");
            String chek = SCANNER_STR.nextLine();
            if (chek.equals("1") || chek.toLowerCase().equals("yes")){
                return;
            }
        } while (true);

    }

    private static void CreateQuestion(int subjectId) {
        do {
            System.out.println("Enter question: ");
            String question = SCANNER_STR.nextLine();
            for (Question i : QuestionDb.getQuestion(subjectId)) {
                if (i.getText().equals(question)) {
                    System.out.println("This question alredy exist");
                    return;
                }
            }

            String sql = "insert into question (subject_id, text) VALUES (" + subjectId + ", '" + question + "')";
            ConnectionDb.Connection(sql);
            System.out.println("Amaliyot muvaffaqiyatli amalga oshirildi." +
                    " Amalyotni davomettirishni istaysizmi? (1.no, 2.yes");
            String chek = SCANNER_STR.nextLine();
            if (chek.equals("1") || chek.toLowerCase().equals("no")) {
                return;
            }
        } while (true);
    }

    public static void ReadeQuestion(int subjectId) {
        int i = 0;
        for (Question question : QuestionDb.getQuestion(subjectId)) {
            i++;
            System.out.println("=====================>>> " + i + "- QUESTION <<<=====================");
            System.out.println(question);
        }
    }

    private static void UpdateQuestion(int subjectId) {
        ReadeQuestion(subjectId);
        System.out.println("Enter question id: ");
        int updateQuestionId = SCANNER_NUM.nextInt();
        System.out.println("1.Update question text.");
        System.out.println("2.Update subject id.");
        System.out.println("0.Go back");
        byte chek = SCANNER_NUM.nextByte();
        switch (chek) {
            case 1: {
                System.out.println("Enter new text: ");
                String newQuestionText = SCANNER_STR.nextLine();
                for (Question i : QuestionDb.getQuestion(subjectId)) {
                    if (i.getText().equals(newQuestionText)) {
                        System.out.println("This question already exist.");
                        UpdateQuestion(subjectId);
                    }
                }
                String sql = "update question set text='" + newQuestionText + "' where id=" + updateQuestionId;
                ConnectionDb.Connection(sql);
                System.out.println("Amaliyot muvaffaqiyatli amalga oshirildi");
            }
            break;
            case 2: {
                SubjectService.ReadSubject();
                System.out.println("Enter subject id: ");
                int updateSubjectId = SCANNER_NUM.nextInt();
                String sql = "update question set subject_id = " + updateSubjectId + " where id=" + updateQuestionId;
                ConnectionDb.Connection(sql);
                System.out.println("Amaliyot muvaffaqiyatli amalga oshirildi");
            }
            break;
            case 3: {
                return;
            }
        }

    }

    private static void DeleteQuestion(int subjectId) {
        ReadeQuestion(subjectId);
        System.out.println("Enter deledet question id: ");
        int deletQuestionId = SCANNER_NUM.nextInt();
        String sql = "delete from question where id = " + deletQuestionId;
        ConnectionDb.Connection(sql);
        System.out.println("Amaliyot muvaffaqiyatli amalga oshirildi");
    }

    public static List<Question> getUserDoingQuestion(int questionCount, int subjectId){
        List<Question> questionList = new ArrayList<>();
        List<Question> question = QuestionDb.getQuestion(subjectId);
        List<Integer> addQUestion = new ArrayList<>();
        boolean chek = false;
        int count = 0;

//Userga savollarni random qilib toplaydi

        while (count < questionCount){
            chek = false;
            int randomNum = new Random().nextInt(0, 10);
            System.out.println(randomNum);
            for (Integer integer : addQUestion) {
                if (integer == randomNum){
                    chek=true;
                    break;
                }
            }
            if (chek){
                continue;
            }
            addQUestion.add(randomNum);
            for (Question question1 : question) {
                if (question1.getId() == randomNum){
                    questionList.add(question1);
                    count++;
                }
            }

        }
        return questionList;
    }
}
