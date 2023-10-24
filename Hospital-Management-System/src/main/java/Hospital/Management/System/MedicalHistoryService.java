package Hospital.Management.System;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalHistoryService {
    @Autowired
    private  MedicalHistoryRepository _medicalHistoryRepository;

    @Autowired
    private MongoTemplate _mongoTemplate;

    public MedicalHistory createMedicalHistory(MedicalHistory medicalHistory){
        return  _medicalHistoryRepository.save(medicalHistory);
    }

    public  MedicalHistory getMedicalHistory(ObjectId medicalHistoryId){
        return _medicalHistoryRepository.findById(medicalHistoryId).orElse(null);
    }

    public List<MedicalHistory> getAllMedicalHostories(){
        return _medicalHistoryRepository.findAll();
    }
    public void deleteMedicalHistory(ObjectId medicalHistoryId){
        _medicalHistoryRepository.deleteById(medicalHistoryId);
    }




}
