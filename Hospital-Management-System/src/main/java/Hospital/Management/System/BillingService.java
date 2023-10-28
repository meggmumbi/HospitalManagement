package Hospital.Management.System;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillingService {

    @Autowired
    private BillingRepository billingRepository;

    public List<Billing> allBills(){
        return billingRepository.findAll();
    }

    public Optional<Billing> singleBill(String billId){
        return billingRepository.findByBillId(billId);
    }

    @Autowired
    private MongoTemplate mongoTemplate;
    public Billing createBill(String billId, int patientId, String serviceOffered, String amount, String paymentStatus, String paymentHistory){
        Billing newBilling = billingRepository.insert(new Billing(billId, patientId, serviceOffered, amount, paymentStatus, paymentHistory));
        return newBilling;
    }
}
