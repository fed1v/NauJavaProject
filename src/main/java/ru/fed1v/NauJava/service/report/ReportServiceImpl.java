package ru.fed1v.NauJava.service.report;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fed1v.NauJava.entity.Food;
import ru.fed1v.NauJava.entity.Report;
import ru.fed1v.NauJava.entity.ReportContent;
import ru.fed1v.NauJava.repository.app_user.AppUserRepository;
import ru.fed1v.NauJava.repository.food.FoodRepository;
import ru.fed1v.NauJava.repository.report.ReportRepository;
import ru.fed1v.NauJava.util.ThreadWithTime;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Реализация сервиса для работы с отчетами
 */
@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final AppUserRepository appUserRepository;
    private final FoodRepository foodRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository, AppUserRepository appUserRepository, FoodRepository foodRepository) {
        this.reportRepository = reportRepository;
        this.appUserRepository = appUserRepository;
        this.foodRepository = foodRepository;
    }

    @Override
    public Report getReportById(Long id) {
        Optional<Report> report = reportRepository.findById(id);

        if (report.isEmpty()) {
            throw new EntityNotFoundException("Report with id " + id + " not found");
        }

        return report.get();
    }

    @Override
    public String getContentById(Long id) {
        return reportRepository.getContentById(id);
    }

    @Override
    public Long createReport() {
        Report report = new Report(Report.Status.CREATED.name());
        return reportRepository.save(report).getId();
    }

    @Override
    public void buildReport(Long id) {
        long buildReportStartTime = System.currentTimeMillis();
        
        Report report = getReportById(id);
        
        CompletableFuture<ReportContent> future = getReportContentAsync();

        future
                .thenAccept(builtContent -> {
                    if (builtContent == null) {
                        report.setStatus(Report.Status.ERROR.name());
                        reportRepository.save(report);
                        return;
                    }

                    try {
                        long totalTime = System.currentTimeMillis() - buildReportStartTime;
                        builtContent.setTotalTime(totalTime);
                        
                        String content = reportContentToJson(builtContent);
                        report.setContent(content);
                        report.setStatus(Report.Status.COMPLETED.name());
                    } catch (Exception e) {
                        report.setStatus(Report.Status.ERROR.name());
                    } finally {
                        reportRepository.save(report);
                    }
                });
    }

    private CompletableFuture<ReportContent> getReportContentAsync() {
        ReportContent reportContent = new ReportContent();

        ThreadWithTime userCounterThread = new ThreadWithTime("UserCounterThread", () -> {
            long usersCount = appUserRepository.count();
            reportContent.setUsersCount(usersCount);
        });

        ThreadWithTime foodsGetterThread = new ThreadWithTime("FoodsGetterThread", () -> {
            Iterable<Food> foods = foodRepository.findAll();

            reportContent.setFoodList(new ArrayList<>());
            foods.forEach(reportContent.getFoodList()::add);
        });

        return CompletableFuture.supplyAsync(() -> {
                    userCounterThread.start();
                    foodsGetterThread.start();

                    try {
                        userCounterThread.join();
                        foodsGetterThread.join();
                    } catch (InterruptedException ignored) {
                    }

                    if (userCounterThread.getHasExceptions() || foodsGetterThread.getHasExceptions()) {
                        throw new RuntimeException();
                    }

                    reportContent.setTimeToCountUsers(userCounterThread.getWorkingTime());
                    reportContent.setTimeToGetFoods(foodsGetterThread.getWorkingTime());

                    return reportContent;
                })
                .exceptionally(e -> {
                    return null;
                });
    }

    private String reportContentToJson(ReportContent reportContent) throws JsonProcessingException {
        return objectMapper.writeValueAsString(reportContent);
    }
}
