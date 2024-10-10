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
import ru.fed1v.NauJava.repository.DishRepository;
import ru.fed1v.NauJava.repository.MealRepository;
import ru.fed1v.NauJava.service.MealService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class MealTest {
    
    private final DishRepository dishRepository;
    private final MealRepository mealRepository;

    private final MealService mealService;
    
    @Autowired
    public MealTest(DishRepository dishRepository, MealRepository mealRepository, MealService mealService) {
        this.dishRepository = dishRepository;
        this.mealRepository = mealRepository;
        this.mealService = mealService;
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
        dish1.setMeal(meal);
        dish2.setMeal(meal);
        
        mealRepository.save(meal);

        Optional<Meal> mealFromRepository = mealRepository.findById(meal.getId());
        Assertions.assertTrue(mealFromRepository.isPresent());

        Assertions.assertEquals(meal, mealFromRepository.get());
    }
    
    @Test
    public void testDeleteMealDeletesItsDishes(){
        NutritionalValue nutritionalValue1 = new NutritionalValue(1, 2, 3);
        NutritionalValue nutritionalValue2 = new NutritionalValue(4, 5, 6);

        Food food1 = new Food("Rice111333333", "Tasty", 100.0, nutritionalValue1);
        Food food2 = new Food("Potato111333333", "Not Tasty", 10.0, nutritionalValue2);
        nutritionalValue1.setFood(food1);
        nutritionalValue2.setFood(food2);

        Dish dish1 = new Dish("Fried Rice111333333333", "Ok", 250.0, food1);
        Dish dish2 = new Dish("Fried Potato1113333333", "Not ok", 123.0, food2);

        List<Dish> dishes = List.of(dish1, dish2);

        Meal meal = new Meal("Cool Meal111333333333", Meal.Type.LUNCH, LocalDateTime.now(), dishes);
        dish1.setMeal(meal);
        dish2.setMeal(meal);
        
        mealRepository.save(meal);
        
        Assertions.assertTrue(mealRepository.findById(meal.getId()).isPresent());
        Assertions.assertTrue(dishRepository.findById(dish1.getId()).isPresent());
        Assertions.assertTrue(dishRepository.findById(dish2.getId()).isPresent());
        
        mealService.deleteMeal(meal.getId());

        Assertions.assertFalse(mealRepository.findById(meal.getId()).isPresent());
        Assertions.assertFalse(dishRepository.findById(dish1.getId()).isPresent());
        Assertions.assertFalse(dishRepository.findById(dish2.getId()).isPresent());
    }
}
