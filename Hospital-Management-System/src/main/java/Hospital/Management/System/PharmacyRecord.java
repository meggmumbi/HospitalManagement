package Hospital.Management.System;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pharmacyRecords")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PharmacyRecord {
    @Transient
    public static final String SEQUENCE_NAME = "pharmacy_records_sequence";

    @Id
    private Long recordId;
    private Long itemId;
    private Long patientId;
    private int quantity;
    private double price;
    private double totals;

}
