package Hospital.Management.System;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;



@Service
public class SequenceService {
    @Autowired
    private MongoTemplate mongoTemplate;



    public long getPatientSequenceValue() {
        Query query = new Query(Criteria.where("_id").is("patient_sequence"));
        Sequence sequence = mongoTemplate.findOne(query, Sequence.class);

        if (sequence == null) {
            sequence = new Sequence();
            sequence.setId("patient_sequence");
            sequence.setSeq(1);
            mongoTemplate.save(sequence);
        }

        return sequence.getSeq();
    }

    public void incrementPatientSequenceValue() {
        Query query = new Query(Criteria.where("_id").is("patient_sequence"));
        Update update = new Update().inc("seq", 1);
        mongoTemplate.updateFirst(query, update, Sequence.class);
    }
}
