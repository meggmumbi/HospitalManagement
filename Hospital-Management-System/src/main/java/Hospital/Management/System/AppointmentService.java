package Hospital.Management.System;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment>allAppointments(){
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> singleAppointment(String UID){
        return appointmentRepository.findByUID(UID);
    }

    public Appointment createAppointment(String UID, int patientId, String doctorUsername, String dateTime){
        Appointment newAppointment = appointmentRepository.insert(new Appointment(UID, patientId, doctorUsername, dateTime));
        return newAppointment;
    }
}
