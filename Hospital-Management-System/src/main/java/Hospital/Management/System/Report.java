package Hospital.Management.System;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "report")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {

    @Id
    private ObjectId id;

    private String reportId;

    private String type;

    private String content;


    public Report(String reportId, String type, String content) {
        this.reportId = reportId;
        this.type = type;
        this.content = content;
    }
}
