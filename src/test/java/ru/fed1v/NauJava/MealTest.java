package ru.fed1v.NauJava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import ru.fed1v.NauJava.entity.Dish;
import ru.fed1v.NauJava.entity.Food;
import ru.fed1v.NauJava.entity.Meal;
import ru.fed1v.NauJava.entity.NutritionalValue;
import ru.fed1v.NauJava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class MealTest {

    private final MealRepository mealRepository;

    @Autowired
    public MealTest(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Test
    @Transactional
    @Commit
    public void testFindMealById() {
        NutritionalValue nutritionalValue1 = new NutritionalValue(1, 2, 3);
        NutritionalValue nutritionalValue2 = new NutritionalValue(4, 5, 6);

        Food food1 = new Food("Rice", "Tasty", 100.0, nutritionalValue1);
        Food food2 = new Food("Potato", "Not Tasty", 10.0, nutritionalValue2);
        nutritionalValue1.setFood(food1);
        nutritionalValue2.setFood(food2);

        Dish dish1 = new Dish("Fried Rice", "Ok", 250.0, food1);
        Dish dish2 = new Dish("Fried Potato", "Not ok", 123.0, food2);

        List<Dish> dishes = List.of(dish1, dish2);

        Meal meal = new Meal("Cool Meal", Meal.Type.LUNCH, LocalDateTime.now(), dishes);

        mealRepository.save(meal);

        Optional<Meal> mealFromRepository = mealRepository.findById(meal.getId());
        Assertions.assertTrue(mealFromRepository.isPresent());

        Assertions.assertEquals(meal, mealFromRepository.get());
    }
}
