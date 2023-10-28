package Hospital.Management.System;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "doctor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor extends User{


    private String specialization;
    private String schedule;


    public Doctor(String username, String password, String gender, String email, String role, String contacts, String specialization, String schedule) {
        super(username, password, gender, email, role, contacts);
        this.specialization = specialization;
        this.schedule = schedule;
    }
}
