package Hospital.Management.System;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

//autowired instantiate the class
    @Autowired
    private PatientService _patientService;
    @Autowired
    private SequenceService _sequenceService;

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients(){
       return new ResponseEntity<List<Patient>>(_patientService.allPatients(), HttpStatus.OK);
    }

    @GetMapping("/{PatientId}")
    public  ResponseEntity<Optional<Patient>> getPatient(@PathVariable String PatientId){

        Optional<Patient> patient = _patientService.singlePatient(Long.parseLong(PatientId));
        if (patient != null) {
            return new ResponseEntity<Optional<Patient>>(_patientService.singlePatient(Long.parseLong(PatientId)),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Map<String, String> payload)
    {
        long patientId = generateNextPatientId("patient_sequence");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate registrationDate = LocalDate.parse(payload.get("registrationDate"), formatter);
        Date newDate = java.sql.Date.valueOf(registrationDate);
        Patient newPatient = _patientService.createPatient(
                patientId,
                Integer.parseInt(payload.get("age")),
                payload.get("name"),
                payload.get("gender"),
                payload.get("contacts"),
                payload.get("insuranceDetails"),
                payload.get("address"),
                newDate
        );

        return new ResponseEntity<>(newPatient, HttpStatus.CREATED);
    }

    private long generateNextPatientId(String sequenceName) {
        // Get the current sequence value for patient IDs
        long sequenceValue = _sequenceService.getSequenceValue(sequenceName);

        // Increment the sequence value and return it
        _sequenceService.incrementSequenceValue(sequenceName);

        return sequenceValue;
    }


    @RequestMapping(value = "/{patientId}", method = RequestMethod.PUT)
    public ResponseEntity<Patient> updatePatient(@PathVariable String patientId, @RequestBody Patient updatedPatientInfo) {

        Patient updatedPatient=_patientService.updatePatientInformation(Long.parseLong(patientId),updatedPatientInfo);

        if (updatedPatient != null) {
            return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<Void> deletePharmacy(@PathVariable String patientId) {

        boolean deleted = _patientService.deletePharmacy(Long.parseLong(patientId));

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getByStatus")
    public ResponseEntity<List<Patient>> getPatientsByStatus() {
        return new ResponseEntity<List<Patient>>(_patientService.getPatientsByStatus(), HttpStatus.OK);

    }

    @GetMapping("/getByPharmacyStatus")
    public ResponseEntity<List<Patient>> getByPharmacyStatus() {
        return new ResponseEntity<List<Patient>>(_patientService.getPatientsByPharmacyStatus(), HttpStatus.OK);

    }

}


