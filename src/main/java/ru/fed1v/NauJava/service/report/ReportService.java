package ru.fed1v.NauJava.service.report;

import ru.fed1v.NauJava.entity.Report;

public interface ReportService {
    
    Report getReportById(Long id);
    
    String getContentById(Long id);
    
    Long createReport();
    
    void buildReport(Long id);
}
