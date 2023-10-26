package Hospital.Management.System;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

     public Hospital createHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    public Optional<Hospital> getHospital(ObjectId hospitalId) {
        return hospitalRepository.findById(hospitalId);
    }

    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    public Hospital updateHospital(ObjectId hospitalId, Hospital updatedHospital) {
        updatedHospital.setHospitalId(hospitalId);
        return hospitalRepository.save(updatedHospital);
    }
}
