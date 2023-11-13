package Hospital.Management.System;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public Optional<Patient> singlePatient(Long PatientId){
        return Optional.ofNullable(_patientRepository.findBypatientId(PatientId).orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + PatientId)));
    }

    public  Patient createPatient(Long patientId, int age, String name, String gender, String contacts, String insuranceDetails, String address, Date registrationDate) {


        Patient newPatient = _patientRepository.insert(new Patient(patientId,age, name, gender, contacts,insuranceDetails,address,registrationDate));
        return  newPatient;
    }

    public  Patient updatePatientInformation(Long patientId, Patient updatedPatientInfo){
        Query query = new Query(Criteria.where("_id").is(patientId));
        Update update = new Update();

        if(updatedPatientInfo.getAllergies() != null){
            update.set("allergies",updatedPatientInfo.getAllergies());
        }
        if (updatedPatientInfo.getWeight() != 0) {
            update.set("weight", updatedPatientInfo.getWeight());
        }
        if (updatedPatientInfo.getHeight() != 0) {
            update.set("height", updatedPatientInfo.getHeight());
        }
        if (updatedPatientInfo.getSystolic() != 0) {
            update.set("systolic", updatedPatientInfo.getSystolic());
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
        if(updatedPatientInfo.getStatus() != null){
            update.set("status", updatedPatientInfo.getStatus());
        }
        if(updatedPatientInfo.getAssignedDoctor() != null){
            update.set("assignedDoctor", updatedPatientInfo.getAssignedDoctor());
        }
        if(updatedPatientInfo.getRegistrationDate() != null){
            update.set("date", updatedPatientInfo.getRegistrationDate());
        }
        if(updatedPatientInfo.getSampleDetails() != null){
            update.set("sampleDetails", updatedPatientInfo.getSampleDetails());
        }
        if(updatedPatientInfo.getDateSampleTaken() != null){
            update.set("dateSampleTaken", updatedPatientInfo.getDateSampleTaken());
        }
        if(updatedPatientInfo.getSampleType() != null){
            update.set("sampleType", updatedPatientInfo.getSampleType());
        }
        if(updatedPatientInfo.getTestTypes() != null){
            update.set("testTypes", updatedPatientInfo.getTestTypes());
        }
        if(updatedPatientInfo.getAdditionalTests() != null){
            update.set("additionalTests", updatedPatientInfo.getAdditionalTests());
        }
        if(updatedPatientInfo.getClinicalInformation() != null){
            update.set("clinicalInformation", updatedPatientInfo.getClinicalInformation());
        }
        if(updatedPatientInfo.getConclusion() != null){
            update.set("conclusion", updatedPatientInfo.getConclusion());
        }

        mongoTemplate.updateFirst(query,update,Patient.class);

        return _patientRepository.findBypatientId(patientId).orElse(null);
    }

    public boolean deletePharmacy(Long patientId) {
        Optional<Patient> patientOptional = _patientRepository.findBypatientId(patientId);

        if (patientOptional.isPresent()) {
            _patientRepository.deleteBypatientId(patientId);
            return true;
        }

        return false;
    }

    public List<Patient> getPatientsByStatus() {
        return _patientRepository.findByStatusIn("Lab", "Triage");
    }

}
