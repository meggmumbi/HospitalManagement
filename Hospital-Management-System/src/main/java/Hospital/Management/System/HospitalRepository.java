package Hospital.Management.System;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HospitalRepository extends MongoRepository<Hospital, ObjectId> {
    Optional<Hospital> findByhospitalId(Long hospitalId);
    void deleteByhospitalId(Long hospitalId);
}
