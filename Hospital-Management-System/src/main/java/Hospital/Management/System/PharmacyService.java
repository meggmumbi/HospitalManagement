package Hospital.Management.System;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PharmacyService {

    @Autowired
    private PharmacyRepository _pharmacyRepository;

    public Pharmacy createPharmacy(Pharmacy pharmacy) {
        return _pharmacyRepository.save(pharmacy);
    }

    public List<Pharmacy> getAllPharmacies() {
        return _pharmacyRepository.findAll();
    }

    public Optional<Pharmacy> getPharmacy(ObjectId itemId) {
        return _pharmacyRepository.findById(itemId);
    }

    public Pharmacy updatePharmacy(ObjectId itemId, Pharmacy updatedPharmacy) {
        // Find the existing pharmacy
        Optional<Pharmacy> existingPharmacyOptional = _pharmacyRepository.findById(itemId);

        if (existingPharmacyOptional.isPresent()) {
            Pharmacy existingPharmacy = existingPharmacyOptional.get();

            // Update the fields you want to modify
            if (updatedPharmacy.getName() != null) {
                existingPharmacy.setName(updatedPharmacy.getName());
            }
            if (updatedPharmacy.getCategory() != null) {
                existingPharmacy.setCategory(updatedPharmacy.getCategory());
            }
            if (updatedPharmacy.getQuantity() > 0) {
                existingPharmacy.setQuantity(updatedPharmacy.getQuantity());
            }
            if (updatedPharmacy.getUnitPrice() > 0) {
                existingPharmacy.setUnitPrice(updatedPharmacy.getUnitPrice());
            }
            if (updatedPharmacy.getSupplierInformation() != null) {
                existingPharmacy.setSupplierInformation(updatedPharmacy.getSupplierInformation());
            }

            return _pharmacyRepository.save(existingPharmacy);
        }

        return null;
    }

    public boolean deletePharmacy(ObjectId itemId) {
        Optional<Pharmacy> pharmacyOptional = _pharmacyRepository.findById(itemId);

        if (pharmacyOptional.isPresent()) {
            _pharmacyRepository.deleteById(itemId);
            return true;
        }

        return false;
    }
}
