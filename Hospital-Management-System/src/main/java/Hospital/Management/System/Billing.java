package Hospital.Management.System;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "billing")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Billing {

    @Id
    private ObjectId id;

    private String billId;

    private int patientId;

    private String serviceOffered;

    private String amount;

    private String paymentStatus;

    private String paymentHistory;


    public Billing(String billId, int patientId, String serviceOffered, String amount, String paymentStatus, String paymentHistory) {
        this.billId = billId;
        this.patientId = patientId;
        this.serviceOffered = serviceOffered;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.paymentHistory = paymentHistory;
    }
}
