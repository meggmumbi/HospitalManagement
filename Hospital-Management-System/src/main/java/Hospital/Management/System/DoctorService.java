package Hospital.Management.System;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    public List<Doctor> allDoctors(){
        return doctorRepository.findAll();
    }

    public Optional<Doctor> singleDoctor(String username){
        return doctorRepository.findByUsername(username);
    }

    @Autowired
    private MongoTemplate mongoTemplate;
    public Doctor createDoctor(String username, String password, String gender,String email, String role, String contacts, String specialization, String schedule){
        Doctor newDoctor = doctorRepository.insert(new Doctor(username, password, gender, email, role, contacts, specialization, schedule));
        return  newDoctor;
    }

    public Optional<Doctor> login(String username, String password) {
        Query query = new Query(Criteria.where("username").is(username).and("password").is(password));
        Doctor doctor = mongoTemplate.findOne(query, Doctor.class);
        return Optional.ofNullable(doctor);
    }
}
