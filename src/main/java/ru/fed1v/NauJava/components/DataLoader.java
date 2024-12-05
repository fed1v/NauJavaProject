package ru.fed1v.NauJava.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.fed1v.NauJava.entity.FoodInfo;
import ru.fed1v.NauJava.entity.NutritionalValue;
import ru.fed1v.NauJava.repository.food_info.FoodInfoRepository;

/**
 * Класс, загружающий первоначальные данные в приложение
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final FoodInfoRepository foodInfoRepository;

    @Autowired
    public DataLoader(FoodInfoRepository foodInfoRepository) {
        this.foodInfoRepository = foodInfoRepository;
    }

    /**
     * Загружает продукты в справочник
     *
     * @param args incoming main method arguments
     */
    @Override
    public void run(String... args) {

        if (foodInfoRepository.count() > 0) {
            return;
        }

        foodInfoRepository.save(
                new FoodInfo("Рис", "", 10.0,
                        new NutritionalValue(7.5, 62.3, 2.6)
                )
        );

        foodInfoRepository.save(
                new FoodInfo("Гречка", "", 4.0,
                        new NutritionalValue(12.6, 57.1, 3.3)
                )
        );

        foodInfoRepository.save(
                new FoodInfo("Картофель", "", 3.0,
                        new NutritionalValue(2.0, 16.3, 0.4)
                )
        );

        foodInfoRepository.save(
                new FoodInfo("Творог 9%", "", 90.0,
                        new NutritionalValue(18, 3, 9)
                )
        );

        foodInfoRepository.save(
                new FoodInfo("Сметана 20%", "", 90.0,
                        new NutritionalValue(2.5, 3.4, 20)
                )
        );
    }
}
