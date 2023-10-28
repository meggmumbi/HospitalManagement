package Hospital.Management.System;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private ObjectId id;

    private String username;

    private String password;

    private String gender;

    private String email;

    private String role;

    private String contacts;

    public User(String username, String password, String gender, String email, String role, String contacts) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.email = email;
        this.role = role;
        this.contacts = contacts;
    }
}
