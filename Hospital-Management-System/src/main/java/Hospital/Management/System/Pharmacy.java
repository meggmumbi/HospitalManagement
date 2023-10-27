package Hospital.Management.System;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pharmacy")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pharmacy {
    @Transient
    public static final String SEQUENCE_NAME = "pharmacy_sequence";
    @Id
    private Long itemId;
    private String name;
    private String category;
    private int quantity;
    private double unitPrice;
    private String supplierInformation;
}
