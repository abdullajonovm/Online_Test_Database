package entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class History {
    private Integer id;
    private Date date;
    private Double point;
    private Integer userAnswerId;

    public History(Date date, Double point, Integer userAnswerId) {
        this.date = date;
        this.point = point;
        this.userAnswerId = userAnswerId;
    }
}
