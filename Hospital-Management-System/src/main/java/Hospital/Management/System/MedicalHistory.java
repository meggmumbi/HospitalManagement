package Hospital.Management.System;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "medicalHistory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalHistory {
    @Id
    private Long medicalHistoryId;

    @DBRef
    private Patient patient;
    private Long patientId;
    private String diagnosis;
    private String background;
    private String symptoms;
    private String remarks;
    private Date dateCreated;


}
