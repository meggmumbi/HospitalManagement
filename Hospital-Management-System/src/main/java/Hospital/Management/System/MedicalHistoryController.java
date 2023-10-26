package Hospital.Management.System;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medicalHistories")
public class MedicalHistoryController {

    @Autowired
    private MedicalHistoryService _medicalHistoryService;

    @PostMapping
    public ResponseEntity<MedicalHistory> createMedicalHistory(@RequestBody MedicalHistory medicalHistory){
        MedicalHistory createdMedicalHistory = _medicalHistoryService.createMedicalHistory(medicalHistory);
        return new ResponseEntity<>(createdMedicalHistory, HttpStatus.CREATED);
    }

    @GetMapping("/{medicalHistoryId}")
    public ResponseEntity<MedicalHistory> getMedicalHistory(@PathVariable String medicalHistoryId){
        ObjectId objectId = new ObjectId(medicalHistoryId);
        MedicalHistory medicalHistory = _medicalHistoryService.getMedicalHistory((objectId));
        if(medicalHistory != null){
            return new ResponseEntity<>(medicalHistory, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<MedicalHistory>> getAllMedicalHistories(){
        List<MedicalHistory> medicalHistories = _medicalHistoryService.getAllMedicalHostories();
        return new ResponseEntity<>(medicalHistories, HttpStatus.OK);
    }

    @DeleteMapping("/{medicalHistoryId}")
    public ResponseEntity<Void> deleteMedicalHistory(@PathVariable String medicalHistoryId) {
        ObjectId objectId = new ObjectId(medicalHistoryId);
        _medicalHistoryService.deleteMedicalHistory(objectId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
