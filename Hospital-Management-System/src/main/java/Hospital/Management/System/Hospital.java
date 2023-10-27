package Hospital.Management.System;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "hospital")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hospital {
    @Transient
    public static final String SEQUENCE_NAME = "hospital_sequence";
    @Id
    private Long hospitalId;
    private String name;
    private String location;
    private String contacts;
    private String specialization;
    private List<String> facilities;

    @DBRef
    private List<Pharmacy> pharmacies;

}
