package Hospital.Management.System;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private MongoOperations mongoOperations;

     public Hospital createHospital(Hospital hospital) {
         Long hospitalId = getNextSequence("hospital_sequence");
         hospital.setHospitalId(hospitalId);
        return hospitalRepository.save(hospital);
    }

    private Long getNextSequence(String sequenceName) {
        Query query = new Query(Criteria.where("_id").is(sequenceName));
        Update update = new Update().inc("value", 1);
        Sequence sequence = mongoOperations.findAndModify(query, update,
                FindAndModifyOptions.options().returnNew(true).upsert(true), Sequence.class);
        return sequence.getSeq();
    }

    public Optional<Hospital> getHospital(Long hospitalId) {
        return hospitalRepository.findByhospitalId(hospitalId);
    }

    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    public Hospital updateHospital(Long hospitalId, Hospital updatedHospital) {
        updatedHospital.setHospitalId(hospitalId);
        return hospitalRepository.save(updatedHospital);
    }

    public boolean deleteHospital(Long hospitalId) {
        Optional<Hospital> hospitalOptional = hospitalRepository.findByhospitalId(hospitalId);

        if (hospitalOptional.isPresent()) {
            hospitalRepository.deleteByhospitalId(hospitalId);
            return true;
        }

        return false;
    }


}
