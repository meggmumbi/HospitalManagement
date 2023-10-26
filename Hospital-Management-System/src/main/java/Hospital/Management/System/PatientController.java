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
        long patientId = generateNextPatientId();

        // Create a new patient with the custom ID
        Patient newPatient = _patientService.createPatient(
                patientId,
                Integer.parseInt(payload.get("age")),
                payload.get("name"),
                payload.get("gender"),
                payload.get("contacts"),
                payload.get("insuranceDetails")
        );

        return new ResponseEntity<>(newPatient, HttpStatus.CREATED);
    }

    private long generateNextPatientId() {
        // Get the current sequence value for patient IDs
        long sequenceValue = _sequenceService.getPatientSequenceValue();

        // Increment the sequence value and return it
        _sequenceService.incrementPatientSequenceValue();

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

}


