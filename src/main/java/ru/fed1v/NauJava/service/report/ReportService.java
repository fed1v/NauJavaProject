package ru.fed1v.NauJava.service.report;

import ru.fed1v.NauJava.entity.Report;

/**
 * Сервис для работы с отчетами
 */
public interface ReportService {

    /**
     * Получить отчет по id
     */
    Report getReportById(Long id);

    /**
     * Получить содержимое отчета по id
     */
    String getContentById(Long id);

    /**
     * Создать отчет
     * @return id созданного отчета
     */
    Long createReport();

    /**
     * Сформировать отчет
     * @param id id отчета, который нужно сформировать
     */
    void buildReport(Long id);
}
