package Hospital.Management.System;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hospital")
public class HospitalController {

    @Autowired
    private HospitalService _hospitalService;

    @PostMapping
    public ResponseEntity<Hospital> createHospital(@RequestBody Hospital hospital) {
        Hospital createdHospital = _hospitalService.createHospital(hospital);
        return new ResponseEntity<>(createdHospital, HttpStatus.CREATED);
    }

    @GetMapping("/{hospitalId}")
    public ResponseEntity<Hospital> getHospital(@PathVariable String hospitalId) {
        ObjectId objectId = new ObjectId(hospitalId);
        Optional<Hospital> hospital = _hospitalService.getHospital(objectId);
        if (hospital.isPresent()) {
            return new ResponseEntity<>(hospital.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Hospital>> getAllHospitals() {
        List<Hospital> hospitals = _hospitalService.getAllHospitals();
        return new ResponseEntity<>(hospitals, HttpStatus.OK);
    }

    @PutMapping("/{hospitalId}")
    public ResponseEntity<Hospital> updateHospital(@PathVariable String hospitalId, @RequestBody Hospital updatedHospital) {
        ObjectId objectId = new ObjectId(hospitalId);
        Hospital updatedHospitalEntity = _hospitalService.updateHospital(objectId, updatedHospital);
        if (updatedHospitalEntity != null) {
            return new ResponseEntity<>(updatedHospitalEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
