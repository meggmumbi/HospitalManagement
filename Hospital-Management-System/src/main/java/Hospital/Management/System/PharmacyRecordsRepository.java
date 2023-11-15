package Hospital.Management.System;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PharmacyRecordsRepository extends MongoRepository<PharmacyRecord, ObjectId> {
    Optional<PharmacyRecord> findByrecordId(Long recordId);

    Optional<PharmacyRecord> findBypatientId(Long recordId);
    void deleteByrecordId(Long itemId);
}
