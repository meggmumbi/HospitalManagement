package Hospital.Management.System;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @GetMapping
   public ResponseEntity<List<Doctor>> getAllDoctors() {
        return new ResponseEntity<List<Doctor>>(doctorService.allDoctors(), HttpStatus.OK);
 }

        @GetMapping("/{username}")
        public ResponseEntity<Optional<Doctor>>getSingleDoctor(@PathVariable String username){

        return new ResponseEntity<Optional<Doctor>>(doctorService.singleDoctor(username), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Map<String, String> payload){
    return new ResponseEntity<Doctor>(doctorService.createDoctor(
            payload.get("username"),
            payload.get("password"),
            payload.get("gender"),
            payload.get("email"),
            payload.get("role"),
            payload.get("contacts"),
            payload.get("specialization"),
            payload.get("schedule")
    ), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Doctor> loginDoctor(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        String password = payload.get("password");

        Optional<Doctor> doctor = doctorService.login(username, password);
        if (doctor.isPresent()) {
            return new ResponseEntity<>(doctor.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }



}
