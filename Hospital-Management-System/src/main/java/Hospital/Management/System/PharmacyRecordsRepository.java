package Hospital.Management.System;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PharmacyRecordsRepository extends MongoRepository<PharmacyRecord, ObjectId> {
    Optional<PharmacyRecord> findByrecordId(Long recordId);

    List<PharmacyRecord> findBypatientId(Long patientId);
    void deleteByrecordId(Long itemId);
}
