package Hospital.Management.System;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class MedicalHistoryService {
    @Autowired
    private  MedicalHistoryRepository _medicalHistoryRepository;

    @Autowired
    private MongoTemplate _mongoTemplate;

    public  MedicalHistory createMedicalHistory(String Diagnosis, String Background, String Symptoms, String Remarks, String patientId){
        MedicalHistory history = _medicalHistoryRepository.insert(new MedicalHistory(Diagnosis,Background,Symptoms,Remarks));



        _mongoTemplate.update(Patient.class)
                .matching(Criteria.where("patientid").is(patientId))
                .apply(new Update().push("").value(history))
                .first();

        return  history;
    }
}
