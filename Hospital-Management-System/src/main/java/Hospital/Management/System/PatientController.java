package Hospital.Management.System;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

//autowired instantiate the class
    @Autowired
    private PatientService _patientService;

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients(){
       return new ResponseEntity<List<Patient>>(_patientService.allPatients(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Optional<Patient>> getPatient(@PathVariable String patientId){
        ObjectId objectId = new ObjectId(patientId);
        Optional<Patient> patient = _patientService.singelPatient(objectId);
        if (patient != null) {
            return new ResponseEntity<Optional<Patient>>(_patientService.singelPatient(objectId),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

//    @PostMapping
//    public ResponseEntity<MedicalHistory> createMedicalHistory(@RequestBody Map<String, String> payload){
//        return  new ResponseEntity<MedicalHistory>(_medicalHistoryService.createMedicalHistory(payload.get("Diagnosis"),
//                payload.get("Background"),payload.get("Symptoms"),payload.get("Remarks"),payload.get("patientId")), HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Map<String, String> payload){
        return  new ResponseEntity<Patient>(_patientService.createPatient(Integer.parseInt(payload.get("Age")),payload.get("Name"),payload.get("Gender")
                ,payload.get("Contacts"),payload.get("InsuranceDetails")), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Patient> updatePatient(@RequestBody String patientId,@RequestBody Patient updatedPatientInfo){
        ObjectId objectId = new ObjectId(patientId);
        Patient updatedPatient=_patientService.updatePatientInformation(objectId,updatedPatientInfo);

        if (updatedPatient != null) {
            return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}


