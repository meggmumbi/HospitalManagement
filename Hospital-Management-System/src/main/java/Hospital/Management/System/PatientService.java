package Hospital.Management.System;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    //db access methods
    private PatientsRepository _patientRepository;
    public List<Patient> allPatients(){
        return  _patientRepository.findAll();
    }

    public Optional<Patient> singelPatient(ObjectId id){
        return  _patientRepository.findById(id);
    }
    public  Patient createPatient(int Age, String Name, String Gender, String Contacts,String InsuranceDetails) {
        Patient newPatient = _patientRepository.insert(new Patient(Age, Name, Gender, Contacts,InsuranceDetails));




        return  newPatient;
    }
}
