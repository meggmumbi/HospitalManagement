package Hospital.Management.System;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;
import java.util.List;

@Document(collection = "patients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Transient
    public static final String SEQUENCE_NAME = "patient_sequence";

    @Id
    private long patientId;

    private int age;
    private String name;
    private String gender;
    private String allergies;
    private String contacts;
    private double weight;
    private double height;
    private double systolic;
    private  double diastolic;
    private String medicalHistorySummery;
    private String observations;
    private String status;
    private Date registrationDate;
    private  String address;
    private String diagnosis;
    private String assignedDoctor;
    private String insuranceDetails;
    private String prescription;

    @DBRef
    private List<MedicalHistory> medicalHistory;

    ///////////////Labs///////////
    private String sampleDetails;
    private Date dateSampleTaken;
    private String sampleType;
    private String testTypes;
    private String additionalTests;
    private String clinicalInformation;
    private String conclusion;






    public Patient(long PatientId,int age, String name, String gender, String contacts,  String insuranceDetails, String address, Date registrationDate) {
        patientId = PatientId;
        this.age = age;
        this.name = name;
        this.gender = gender;
        this.contacts = contacts;
        this.insuranceDetails = insuranceDetails;
        this.address = address;
        this.registrationDate = registrationDate;
    }
}
