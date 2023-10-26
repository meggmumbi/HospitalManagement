package Hospital.Management.System;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {

    @Autowired
    private PatientsRepository _patientRepository;

    @Autowired
    private MongoTemplate mongoTemplate;
    public List<Patient> allPatients(){
        return  _patientRepository.findAll();
    }

    public Optional<Patient> singelPatient(ObjectId id){
        return  _patientRepository.findByPatientId(id);
    }

    public  Patient createPatient(int Age, String Name, String Gender, String Contacts,String InsuranceDetails) {
        Patient newPatient = _patientRepository.insert(new Patient(Age, Name, Gender, Contacts,InsuranceDetails));
        return  newPatient;
    }

    public  Patient updatePatientInformation(ObjectId patientId, Patient updatedPatientInfo){
        Query query = new Query(Criteria.where("_id").is(patientId));
        Update update = new Update();

        if(updatedPatientInfo.getAllergies() != null){
            update.set("Allergies",updatedPatientInfo.getAllergies());
        }
        if (updatedPatientInfo.getWeight() != 0) {
            update.set("weight", updatedPatientInfo.getWeight());
        }
        if (updatedPatientInfo.getHeight() != 0) {
            update.set("height", updatedPatientInfo.getHeight());
        }
        if (updatedPatientInfo.getSystolic() != 0) {
            update.set("Systolic", updatedPatientInfo.getSystolic());
        }
        if (updatedPatientInfo.getDiastolic() != 0) {
            update.set("diastolic", updatedPatientInfo.getDiastolic());
        }
        if (updatedPatientInfo.getMedicalHistorySummery() != null){
            update.set("medicalHistorySummery", updatedPatientInfo.getMedicalHistorySummery());
        }
        if(updatedPatientInfo.getObservations() != null){
            update.set("observations", updatedPatientInfo.getObservations());
        }

        mongoTemplate.updateFirst(query,update,Patient.class);

        return _patientRepository.findByPatientId(patientId).orElse(null);
    }


}
