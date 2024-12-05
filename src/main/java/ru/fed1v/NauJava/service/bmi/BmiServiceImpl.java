package ru.fed1v.NauJava.service.bmi;

import org.springframework.stereotype.Service;

@Service
public class BmiServiceImpl implements BmiService {

    @Override
    public double getBmi(double heightCm, double weightKg) {
        double heightMeters = heightCm / 100.0;

        return Math.round(weightKg / (heightMeters * heightMeters));
    }
}
