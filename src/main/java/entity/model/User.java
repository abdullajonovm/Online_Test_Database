package entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;
    private String password;
    private boolean active = true;
    private String role = "user";
    private String email;

    public User(String firstName, String lastName, String phone, String password, boolean active, String role, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.password = password;
        this.active = active;
        this.role = role;
        this.email = email;
    }
}
