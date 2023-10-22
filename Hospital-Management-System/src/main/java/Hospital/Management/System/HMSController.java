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
public class HMSController {

//autowired instantiate the class
    @Autowired
    private PatientService _patientService;
    private MedicalHistoryService _medicalHistoryService;

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients(){
       return new ResponseEntity<List<Patient>>(_patientService.allPatients(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Optional<Patient>> getPatient(@PathVariable ObjectId id){
        return  new ResponseEntity<Optional<Patient>>(_patientService.singelPatient(id),HttpStatus.OK);
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
    public ResponseEntity<Patient> updatePatient(@RequestBody Map<String, String> payload){
        return  new ResponseEntity<Patient>(_patientService.createPatient(Integer.parseInt(payload.get("Age")),payload.get("Name"),payload.get("Gender")
                ,payload.get("Contacts"),payload.get("InsuranceDetails")), HttpStatus.CREATED);
    }

}


