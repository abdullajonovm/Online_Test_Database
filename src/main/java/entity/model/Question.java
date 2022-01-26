package entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private Integer id;
    private Integer subjecId;
    private String text;

    public Question(Integer subjecId, String text) {
        this.subjecId = subjecId;
        this.text = text;
    }
}
