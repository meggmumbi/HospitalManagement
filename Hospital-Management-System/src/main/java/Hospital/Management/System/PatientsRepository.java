package Hospital.Management.System;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientsRepository extends MongoRepository<Patient, ObjectId> {
    Optional<Patient> findByPatientId(ObjectId patientId);
    Optional<Patient> findPatientByName(String name);
}
