package ru.fed1v.NauJava.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.fed1v.NauJava.entity.Report;
import ru.fed1v.NauJava.entity.ReportContent;
import ru.fed1v.NauJava.service.report.ReportService;

/**
 * Класс, обрабатывающий запросы, относящиеся к отчету
 */
@Controller
public class ReportController {

    /**
     * Сервис для работы с отчетами
     */
    private final ReportService reportService;

    /**
     * Вспомогательный объект для перевода данных в формат JSON и обратно
     */
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * Создает отчет, запускает процесс его формирования и возвращает 
     * id созданного отчета
     */
    @GetMapping("/report")
    @ResponseBody
    public String createReport() {
        Long createdReportId = reportService.createReport();
        reportService.buildReport(createdReportId);
        return "{ id: " + createdReportId + "}";
    }

    /**
     * Отображает страницу с отчетом по id
     *
     * @return имя шаблона, отображающего страницу с отчетом
     */
    @GetMapping("/report/{id}")
    public String getReportById(
            @PathVariable Long id,
            Model model
    ) throws JsonProcessingException {
        Report reportFromRepository = reportService.getReportById(id);
        model.addAttribute("report", reportFromRepository);

        if (!reportFromRepository.getStatus().equals(Report.Status.COMPLETED.name())) {
            return "report-not-completed";
        }

        ReportContent reportContent = objectMapper.readValue(reportFromRepository.getContent(), ReportContent.class);
        model.addAttribute("reportContent", reportContent);

        return "report";
    }
}
