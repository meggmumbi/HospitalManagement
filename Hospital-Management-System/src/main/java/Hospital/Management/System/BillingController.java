package Hospital.Management.System;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/billing")
public class BillingController {
    @Autowired
    private BillingService billingService;

    @GetMapping
    public ResponseEntity<List<Billing>>getAllBills(){
        return new ResponseEntity<List<Billing>>(billingService.allBills(), HttpStatus.OK);
    }

    @GetMapping("/{billId}")
    public ResponseEntity<Optional<Billing>>getSingleBill(@PathVariable String billId){
        return new ResponseEntity<Optional<Billing>>(billingService.singleBill(billId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Billing>createBill(@RequestBody Map<String, String> payload){
        int patientId = Integer.parseInt(payload.get("patientId"));
        return new ResponseEntity<Billing>(billingService.createBill(
                payload.get("billId"),
                patientId,
                payload.get("serviceOffered"),
                payload.get("amount"),
                payload.get("paymentStatus"),
                payload.get("paymentHistory")
        ), HttpStatus.CREATED);
    }
}
