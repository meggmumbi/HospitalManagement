package Hospital.Management.System;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pharmacy")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pharmacy {
    @Id
    private ObjectId ItemId;
    private String Name;
    private String Category;
    private int Quantity;
    private double UnitPrice;
    private String SupplierInformation;
}
