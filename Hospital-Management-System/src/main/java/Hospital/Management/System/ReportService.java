package Hospital.Management.System;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public List<Report>allReports(){
        return reportRepository.findAll();
    }

    public Optional<Report> singleReport(String reportId){
        return reportRepository.findByReportId(reportId);
    }

    public Report createReport(String reportId, String type, String content){
        Report newReport = reportRepository.insert(new Report(reportId, type, content));
        return newReport;
    }

}
