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
    private ObjectId MedicalHistoryId;

    @DBRef
    private Patient patient;
    private String Diagnosis;
    private String Background;
    private String Symptoms;
    private String Remarks;
    private Date Date;


}
