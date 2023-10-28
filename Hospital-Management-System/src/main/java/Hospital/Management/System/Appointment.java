package Hospital.Management.System;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "appointments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id
    private ObjectId id;

    private String UID;

    private int patientId;

    private String doctorUsername;

    private String dateTime;

    public Appointment(String UID, int patientId, String doctorUsername, String dateTime) {
        this.UID = UID;
        this.patientId = patientId;
        this.doctorUsername = doctorUsername;
        this.dateTime = dateTime;
    }
}
