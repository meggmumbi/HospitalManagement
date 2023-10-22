package Hospital.Management.System;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "medicalHistory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalHistory {
    @Id
    private ObjectId Medicalhitoryid;
    private ObjectId PatientId;
    private String Diagnosis;
    private String Background;
    private String Symptoms;
    private String Remarks;

    public MedicalHistory(String diagnosis, String background, String symptoms, String remarks) {
        Diagnosis = diagnosis;
        Background = background;
        Symptoms = symptoms;
        Remarks = remarks;
    }
}
