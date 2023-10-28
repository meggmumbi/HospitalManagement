package Hospital.Management.System;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments(){
        return new ResponseEntity<List<Appointment>>(appointmentService.allAppointments(), HttpStatus.OK);
    }

    @GetMapping("/{UID}")
    public ResponseEntity<Optional<Appointment>>getSingleAppointment(@PathVariable String UID){
        return new ResponseEntity<Optional<Appointment>>(appointmentService.singleAppointment(UID), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Appointment>createAppointment(@RequestBody Map<String, String> payload){
        int patientId = Integer.parseInt(payload.get("patientId"));
        return new ResponseEntity<Appointment>(appointmentService.createAppointment(
             payload.get("UID"),
             patientId,
                payload.get("doctorUsername"),
                payload.get("dateTime")
                ), HttpStatus.CREATED);
    }
}
