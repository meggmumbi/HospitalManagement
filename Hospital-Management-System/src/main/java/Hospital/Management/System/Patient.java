package Hospital.Management.System;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "patients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Transient
    public static final String SEQUENCE_NAME = "patient_sequence";

    @Id
    private int PatientId;

    private int Age;
    private String Name;
    private String Gender;
    private String Allergies;
    private String Contacts;
    private double weight;
    private double height;
    private double Systolic;
    private  double diastolic;
    private String medicalHistorySummery;
    private String observations;

    private String Diagnosis;

    private String AssignedDoctor;
    private String InsuranceDetails;

    @DocumentReference
    private List<MedicalHistory> MedicalHistory;


    public Patient(int age, String name, String gender, String contacts,  String insuranceDetails) {
        Age = age;
        Name = name;
        Gender = gender;
        Contacts = contacts;
        InsuranceDetails = insuranceDetails;
    }
}
