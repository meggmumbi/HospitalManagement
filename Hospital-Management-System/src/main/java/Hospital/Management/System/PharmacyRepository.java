package Hospital.Management.System;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PharmacyRepository extends MongoRepository<Pharmacy, ObjectId> {

    Optional<Pharmacy> findByitemId(Long itemId);
    void deleteByitemId(Long itemId);
}
