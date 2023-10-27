package Hospital.Management.System;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pharmacies")
public class PharmacyController {
    @Autowired
    private PharmacyService _pharmacyService;

    @PostMapping
    public ResponseEntity<Pharmacy> createPharmacy(@RequestBody Pharmacy pharmacy) {

        Pharmacy createdPharmacy = _pharmacyService.createPharmacy(pharmacy);
        return new ResponseEntity<>(createdPharmacy, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Pharmacy>> getAllPharmacies() {

        List<Pharmacy> pharmacies = _pharmacyService.getAllPharmacies();
        return new ResponseEntity<>(pharmacies, HttpStatus.OK);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Pharmacy> getPharmacy(@PathVariable String itemId) {

        Optional<Pharmacy> pharmacy = _pharmacyService.getPharmacy(Long.parseLong(itemId));
        if (pharmacy.isPresent()) {
            return new ResponseEntity<>(pharmacy.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<Pharmacy> updatePharmacy(@PathVariable String itemId, @RequestBody Pharmacy updatedPharmacy) {

        Pharmacy updatedPharmacyEntity = _pharmacyService.updatePharmacy(Long.parseLong(itemId), updatedPharmacy);

        if (updatedPharmacyEntity != null) {
            return new ResponseEntity<>(updatedPharmacyEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deletePharmacy(@PathVariable String itemId) {

        boolean deleted = _pharmacyService.deletePharmacy(Long.parseLong(itemId));

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
