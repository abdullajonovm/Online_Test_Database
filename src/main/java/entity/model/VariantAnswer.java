package entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariantAnswer {
    private Integer id;
    private Integer questionId;
    private String name;
    private Boolean iscorrect;

    public VariantAnswer(Integer questionId, String name, Boolean iscorrect) {
        this.questionId = questionId;
        this.name = name;
        this.iscorrect = iscorrect;
    }
}
