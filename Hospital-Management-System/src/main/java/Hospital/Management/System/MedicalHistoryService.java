package Hospital.Management.System;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class MedicalHistoryService {
    @Autowired
    private  MedicalHistoryRepository _medicalHistoryRepository;

    @Autowired
    private MongoTemplate _mongoTemplate;

    @Autowired
    private  SequenceService _sequenceService;

    @Autowired
    private MongoOperations mongoOperations;


    public MedicalHistory createMedicalHistory(MedicalHistory medicalHistory){
        Long medicalHistoryId = generateNextId("medicalHistory_sequence");
        medicalHistory.setMedicalHistoryId(medicalHistoryId);
        return  _medicalHistoryRepository.save(medicalHistory);
    }

    private long generateNextId(String sequenceName) {
        // Get the current sequence value for patient IDs
        long sequenceValue = _sequenceService.getSequenceValue(sequenceName);

        // Increment the sequence value and return it
        _sequenceService.incrementSequenceValue(sequenceName);

        return sequenceValue;
    }

    public  MedicalHistory getMedicalHistory(Long medicalHistoryId){
        return _medicalHistoryRepository.findByMedicalHistoryId(medicalHistoryId).orElse(null);
    }

    public List<MedicalHistory> getAllMedicalHostories(){
        return _medicalHistoryRepository.findAll();
    }
    public void deleteMedicalHistory(Long medicalHistoryId){
        _medicalHistoryRepository.deleteByMedicalHistoryId(medicalHistoryId);
    }

    public MedicalHistory updateMedicalHistory(Long medicalHistoryId, MedicalHistory updatedMedicalHistory) {
        updatedMedicalHistory.setMedicalHistoryId(medicalHistoryId);
        return _medicalHistoryRepository.save(updatedMedicalHistory);
    }






}
