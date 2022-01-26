package entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    private Integer id;
    private  String name;
    private boolean active;

    public Subject(String name, boolean active) {
        this.name = name;
        this.active = active;
    }
}
