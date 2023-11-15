package Hospital.Management.System;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pharmacyRecords")
public class PharmacyRecordsController {
    @Autowired
    private PharmacyRecordService _pharmacyRecordService;

    @Autowired
    private PharmacyService _pharmacyService;

    @PostMapping
    public ResponseEntity<PharmacyRecord> createPharmacy(@RequestBody PharmacyRecord pharmacy) {

        PharmacyRecord createdPharmacy = _pharmacyRecordService.createPharmacyRecord(pharmacy);

        // Update quantity in Pharmacy
        Long itemId = pharmacy.getItemId();
        int quantity = pharmacy.getQuantity();
        _pharmacyService.updatePharmacyItemQuantity(itemId, quantity);

        return new ResponseEntity<>(createdPharmacy, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PharmacyRecord>> getAllPharmacies() {

        List<PharmacyRecord> pharmacies = _pharmacyRecordService.getAllPharmacies();
        return new ResponseEntity<>(pharmacies, HttpStatus.OK);
    }

    @GetMapping("/{recordId}")
    public ResponseEntity<PharmacyRecord> getPharmacy(@PathVariable String recordId) {

        Optional<PharmacyRecord> pharmacy = _pharmacyRecordService.getPharmacy(Long.parseLong(recordId));
        if (pharmacy.isPresent()) {
            return new ResponseEntity<>(pharmacy.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<PharmacyRecord> getPharmacyRecords(@PathVariable String patientId) {

        Optional<PharmacyRecord> pharmacy = _pharmacyRecordService.getPharmacyRecord(Long.parseLong(patientId));
        if (pharmacy.isPresent()) {
            return new ResponseEntity<>(pharmacy.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{recordId}")
    public ResponseEntity<PharmacyRecord> updatePharmacy(@PathVariable String recordId, @RequestBody PharmacyRecord updatedPharmacy) {

        PharmacyRecord updatedPharmacyEntity = _pharmacyRecordService.updatePharmacy(Long.parseLong(recordId), updatedPharmacy);

        if (updatedPharmacyEntity != null) {
            return new ResponseEntity<>(updatedPharmacyEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{recordId}")
    public ResponseEntity<Void> deletePharmacy(@PathVariable String recordId) {

        boolean deleted = _pharmacyRecordService.deletePharmacy(Long.parseLong(recordId));

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

