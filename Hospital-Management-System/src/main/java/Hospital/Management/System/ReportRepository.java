package Hospital.Management.System;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepository extends MongoRepository<Report, ObjectId> {

    Optional<Report>findByReportId(String reportId);
}
