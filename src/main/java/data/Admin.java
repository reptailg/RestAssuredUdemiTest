package data;

import lombok.*;

@Getter
@Setter
public class Admin {
    private String username;
    private String password;

    public Admin() {
        this.username = "admin";
        this.password = "password123";
    }
}
