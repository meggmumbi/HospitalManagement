package Hospital.Management.System;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, ObjectId> {

    Optional<Appointment> findByUID(String UID);
}
