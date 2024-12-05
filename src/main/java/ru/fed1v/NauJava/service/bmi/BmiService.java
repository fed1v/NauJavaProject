package ru.fed1v.NauJava.service.bmi;

/**
 * Сервис для работы с индексом массы тела
 */
public interface BmiService {

    /**
     * Вычислить индекс массы тела
     * @param heightCm рост человека в сантиметрах
     * @param weighKg масса человека в килограммах
     * @return индекс массы тела
     */
    double getBmi(double heightCm, double weighKg);
}
