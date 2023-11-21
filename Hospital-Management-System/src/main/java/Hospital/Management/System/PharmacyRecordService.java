package Hospital.Management.System;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PharmacyRecordService {

    @Autowired
    private PharmacyRecordsRepository _pharmacyRecordsRepository;
    @Autowired
    private  SequenceService _sequenceService;
    @Autowired
    private MongoOperations mongoOperations;

    public PharmacyRecord createPharmacyRecord(PharmacyRecord pharmacy) {
        Long recordId = generateNextId("pharmacy_records_sequence");
        pharmacy.setRecordId(recordId);
        return _pharmacyRecordsRepository.save(pharmacy);
    }

    private long generateNextId(String sequenceName) {
        // Get the current sequence value for patient IDs
        long sequenceValue = _sequenceService.getSequenceValue(sequenceName);

        // Increment the sequence value and return it
        _sequenceService.incrementSequenceValue(sequenceName);

        return sequenceValue;
    }

    public List<PharmacyRecord> getAllPharmacies() {
        return _pharmacyRecordsRepository.findAll();
    }

    public Optional<PharmacyRecord> getPharmacy(Long recordId) {
        return _pharmacyRecordsRepository.findByrecordId(recordId);
    }
    public List<PharmacyRecord> getPharmacyRecord(Long patientId) {
        return _pharmacyRecordsRepository.findBypatientId(patientId);
    }

    public PharmacyRecord updatePharmacy(Long recordId, PharmacyRecord updatedPharmacy) {
        // Find the existing pharmacy
        Optional<PharmacyRecord> existingPharmacyOptional = _pharmacyRecordsRepository.findByrecordId(recordId);

        if (existingPharmacyOptional.isPresent()) {
            PharmacyRecord existingPharmacy = existingPharmacyOptional.get();

            // Update the fields you want to modify
            if (updatedPharmacy.getQuantity() > 0 ) {
                existingPharmacy.setQuantity(updatedPharmacy.getQuantity());
            }
            if (updatedPharmacy.getPrice() > 0) {
                existingPharmacy.setPrice(updatedPharmacy.getPrice());
            }
            if (updatedPharmacy.getQuantity() > 0) {
                existingPharmacy.setQuantity(updatedPharmacy.getQuantity());
            }

            return _pharmacyRecordsRepository.save(existingPharmacy);
        }

        return null;
    }

    public boolean deletePharmacy(Long recordId) {
        Optional<PharmacyRecord> pharmacyOptional = _pharmacyRecordsRepository.findByrecordId(recordId);

        if (pharmacyOptional.isPresent()) {
            _pharmacyRecordsRepository.deleteByrecordId(recordId);
            return true;
        }

        return false;
    }
}
