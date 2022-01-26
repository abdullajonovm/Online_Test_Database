package service;

import database.QuestionDb;
import database.VariantAnswerDb;
import dto.ConnectionDb;
import entity.model.Question;
import entity.model.User;
import entity.model.VariantAnswer;

import java.util.Scanner;

public class VariantAnswerService {
    public static final Scanner SCANNER_NUM = new Scanner(System.in);
    public static final Scanner SCANNER_STR = new Scanner(System.in);

    public static void VariantAnswerCRUD(User user) {
        SubjectService.ReadSubject();
        System.out.println("Enter subject id: ");
        int subjectId = SCANNER_NUM.nextInt();
        boolean operation = true;
        QuestionService.ReadeQuestion(subjectId);
        System.out.println("Enter question id: ");
        int questionId = SCANNER_NUM.nextInt();

        while (operation) {

            System.out.println("1.Create variants");
            System.out.println("2.Reade variants");
            System.out.println("3.Update varinats");
            System.out.println("4.Delete variants");
            System.out.println("0.Go back");
            int chek = SCANNER_NUM.nextInt();
            switch (chek) {
                case 1: {
                    CreateVariant(questionId);
                }
                break;
                case 2: {
                    ReadVariant(questionId);
                }
                break;
                case 3: {
                    UpdateVariant(questionId);
                }
                break;
                case 4: {
                    DeleteVariant(questionId);
                }
                break;
                case 0: {
                    operation = false;
                }
            }

        }
    }

    private static void CreateVariant(int questionId) {
        String sql = "select count(*)  from variant_answer where question_id = " + questionId;
        int count = ConnectionDb.Connect(sql);
        if (count >= 4) {
            System.out.println("Eslatma bitta savolga 4 tadan ortiqcha bolmasligi kereak");
            return;
        }
        boolean iscorrect = true;
        for (VariantAnswer variantAnswer : VariantAnswerDb.getVariantAnswerList(questionId)) {
            if (variantAnswer.getIscorrect()) {
                iscorrect = false;
            }
        }


        while (count < 4) {
            count++;
            System.out.println("Enter variant answer: ");
            String answer = SCANNER_STR.nextLine();
            if (iscorrect) {
                System.out.println("Bu tog'g'ri variantmi ? (1.yes, 2.no)");
                String chek = SCANNER_STR.nextLine();
                if (chek.equals("1") || chek.toLowerCase().equals("yes")) {
                    iscorrect = false;
                    String sqll = "insert into variant_answer (question_id, name, iscorrect)\n" +
                            "values (" + questionId + ", '" + answer + "', " + true + ")";
                    ConnectionDb.Connection(sqll);
                    continue;
                }
            }
            String sqll = "insert into variant_answer (question_id, name, iscorrect)\n" +
                    "values (" + questionId + ", '" + answer + "', " + false + ")";

            ConnectionDb.Connection(sqll);
        }
    }

    private static void ReadVariant(int questionId) {
        int counter = 0;
        for (VariantAnswer variantAnswer : VariantAnswerDb.getVariantAnswerList(questionId)) {
            counter++;
            System.out.println(counter + "- " + variantAnswer);
        }
    }

    private static void UpdateVariant(int questionId) {
        ReadVariant(questionId);
        System.out.println("Enter answer id: ");
        int idAnswer = SCANNER_NUM.nextInt();

        System.out.println("1.Update answer text");
        System.out.println("2.Update answer questionId");
        System.out.println("3.Update answer isCorrect");
        System.out.println("0.Go back");
        byte chek = SCANNER_NUM.nextByte();
        switch (chek) {
            case 1: {
                UpdateAnsverText(idAnswer, questionId);
            }
            break;
            case 2: {
                UpdateAnsverQuestionId(idAnswer);

            }
            break;
            case 3: {
                UpdateAnsverIscorrect(idAnswer, questionId);

            }
            break;
            case 0: {
               return;
            }
        }

    }

    private static void UpdateAnsverText(int idAnswer, int questionId) {

        System.out.println("Enter new answer text: ");
        String newText = SCANNER_STR.nextLine();
        for (VariantAnswer variantAnswer : VariantAnswerDb.getVariantAnswerList(questionId)) {
            if (variantAnswer.getName().equals(newText)) {
                System.out.println("This variant already exist");
                return;
            }
        }

        String sql = "update variant_answer set name = '" + newText + "' where id = " + idAnswer;
        ConnectionDb.Connection(sql);
        System.out.println("Amaliyot muvafaqqiyatli amalga oshirildi");

    }

    private static void UpdateAnsverQuestionId(int idAnswer) {
        SubjectService.ReadSubject();
        System.out.print("Enter subject id: ");
        int subjectId = SCANNER_NUM.nextInt();
        QuestionService.ReadeQuestion(subjectId);
        System.out.print("Enter question id: ");
        int questionId = SCANNER_NUM.nextInt();
        String sql = "select count(*) from variant_answer where question_id = " + questionId;
        int count = ConnectionDb.Connect(sql);
        if (count >= 4) {
            System.out.println("Bu savolda variantlar yetarlicha siz bu questio idga o`zgartira olmaysiz");
            return;
        } else {
            sql = "update variant_answer set question_id = " + questionId + " where id = " + idAnswer;
            ConnectionDb.Connection(sql);
            System.out.println("Bu varianti question id si ozgardi");
        }
    }

    private static void UpdateAnsverIscorrect(int idAnswer, int questionId) {
        for (VariantAnswer variantAnswer : VariantAnswerDb.getVariantAnswerList(questionId)) {
            if (variantAnswer.getId() == idAnswer) {
                System.out.println("Bu answer iscorrecti = " + variantAnswer.getIscorrect() + "edi");
                String sql = "update variant_answer set iscorrect = " + !variantAnswer.getIscorrect() + " where id = " + idAnswer;
                ConnectionDb.Connection(sql);
                System.out.println("Amaliyot muvaffaqiyatli malga oshirildi");
                System.out.println("Bu answer iscorrecti = " + variantAnswer.getIscorrect() + " bo'ldi");

            }
        }

    }

    private static void DeleteVariant(int questionId) {
        for (VariantAnswer variantAnswer : VariantAnswerDb.getVariantAnswerList(questionId)) {
            System.out.println(variantAnswer);
        }

        System.out.println("Enter deleted variant id: ");
        int deletVariantId = SCANNER_NUM.nextInt();

        String sql = "delete from variant_answer where id=" + deletVariantId;
        ConnectionDb.Connection(sql);
        System.out.println("Amaliyot muvafaqqiyatli amalga oshirildi");


    }
}