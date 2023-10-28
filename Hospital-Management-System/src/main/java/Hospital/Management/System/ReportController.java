package Hospital.Management.System;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public ResponseEntity<List<Report>> getAllReports(){
     return new ResponseEntity<List<Report>>(reportService.allReports(), HttpStatus.OK);
    }

    @GetMapping("/{reportId}")
    public ResponseEntity<Optional<Report>>getSingleReport(@PathVariable String reportId){
        return new ResponseEntity<Optional<Report>>(reportService.singleReport(reportId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Report>createReport(@RequestBody Map<String, String> payload){
        return new ResponseEntity<Report>(reportService.createReport(
                payload.get("reportId"),
                payload.get("type"),
                payload.get("content")
        ), HttpStatus.CREATED);
    }

}
