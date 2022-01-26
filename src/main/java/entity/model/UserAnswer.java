package entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswer {
    private Integer id;
    private Integer questionId;
    private Integer userId;
    private String givenAnswer;

    public UserAnswer(Integer questionId, Integer userId, String givenAnswer) {
        this.questionId = questionId;
        this.userId = userId;
        this.givenAnswer = givenAnswer;
    }
}
