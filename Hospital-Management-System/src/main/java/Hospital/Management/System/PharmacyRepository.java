package Hospital.Management.System;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyRepository extends MongoRepository<Pharmacy, ObjectId> {
}
