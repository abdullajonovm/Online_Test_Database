package service;

import database.QuestionDb;
import database.VariantAnswerDb;
import dto.ConnectionDb;
import entity.model.Question;
import entity.model.User;
import entity.model.VariantAnswer;

import java.time.LocalDate;
import java.util.*;

public class TestService {

    public static final Scanner SCANNER_NUM = new Scanner(System.in);
    public static final Scanner SCANNER_STR = new Scanner(System.in);

    public static void DoingTest(int countTest, int subjectId, User user) {
        List<Question> userDoingQuestion = QuestionService.getUserDoingQuestion(countTest, subjectId);

        System.out.println("Doing test");
        int userTrueAnswer = 0;
        int userFalseAnswer = 0;
        for (Question question : userDoingQuestion) {
            System.out.println(question.getText());
            List<VariantAnswer> variantAnswerList = VariantAnswerDb.getVariantAnswerList(question.getId());
            int count = 0;
//            List<Integer> integerList = new ArrayList<>();
            for (int i = 0; i < variantAnswerList.size(); i++) {
                System.out.println(i + ") " + variantAnswerList.get(i).getName());
            }

            System.out.print("Enter your answer number: ");
            int userAnswerId = SCANNER_NUM.nextInt();

            String sql = "insert into user_answer (question_id, given_answer, users_id) VALUES (" +
                    question.getId() + ", '" + variantAnswerList.get(userAnswerId).getName() + "', " +
                    user.getId() + ")";
            ConnectionDb.Connection(sql);
            String str = "select id from user_answer where question_id = " +
                    question.getId() + " order by id limit 1";

            int user_answer_id = ConnectionDb.Connect(str);

            if (variantAnswerList.get(userAnswerId).getIscorrect()) {
                userTrueAnswer++;
                String sqlHistory = "insert into history (date, point, user_answer_id) VALUES ('"
                        + LocalDate.now() + "', " + 2 + ", " + user_answer_id + ")";
                ConnectionDb.Connection(sqlHistory);

            } else {
                userFalseAnswer++;
                String sqlHistory = "insert into history (date, point, user_answer_id) VALUES ('"
                        + LocalDate.now() + "', " + 0 + ", " + user_answer_id + ")";
                ConnectionDb.Connection(sqlHistory);
            }

        }

        System.out.println("Your tru answer: " + userTrueAnswer);
        System.out.println("Your false answer: " + userFalseAnswer);

    }
}
