package ru.fed1v.NauJava.repository.report;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.fed1v.NauJava.entity.Report;

public interface ReportRepository extends CrudRepository<Report, Long> {

    @Query("SELECT r.content from Report r")
    String getContentById(Long id);
}
