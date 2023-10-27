package Hospital.Management.System;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PharmacyService {

    @Autowired
    private PharmacyRepository _pharmacyRepository;
    @Autowired
    private MongoOperations mongoOperations;

    public Pharmacy createPharmacy(Pharmacy pharmacy) {
        Long itemId = getNextSequence("pharmacy_sequence");
        pharmacy.setItemId(itemId);
        return _pharmacyRepository.save(pharmacy);
    }
    private Long getNextSequence(String sequenceName) {
        Query query = new Query(Criteria.where("_id").is(sequenceName));
        Update update = new Update().inc("value", 1);
        Sequence sequence = mongoOperations.findAndModify(query, update,
                FindAndModifyOptions.options().returnNew(true).upsert(true), Sequence.class);
        return sequence.getSeq();
    }

    public List<Pharmacy> getAllPharmacies() {
        return _pharmacyRepository.findAll();
    }

    public Optional<Pharmacy> getPharmacy(Long itemId) {
        return _pharmacyRepository.findByitemId(itemId);
    }

    public Pharmacy updatePharmacy(Long itemId, Pharmacy updatedPharmacy) {
        // Find the existing pharmacy
        Optional<Pharmacy> existingPharmacyOptional = _pharmacyRepository.findByitemId(itemId);

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

    public boolean deletePharmacy(Long itemId) {
        Optional<Pharmacy> pharmacyOptional = _pharmacyRepository.findByitemId(itemId);

        if (pharmacyOptional.isPresent()) {
            _pharmacyRepository.deleteByitemId(itemId);
            return true;
        }

        return false;
    }
}
