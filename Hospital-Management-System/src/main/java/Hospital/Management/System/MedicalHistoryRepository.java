package Hospital.Management.System;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalHistoryRepository extends MongoRepository<MedicalHistory, ObjectId> {
    Optional<MedicalHistory> findByMedicalHistoryId(Long MedicalHistoryId);
    void deleteByMedicalHistoryId(Long MedicalHistoryId);
}
