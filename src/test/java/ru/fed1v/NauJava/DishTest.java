package ru.fed1v.NauJava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.fed1v.NauJava.entity.Dish;
import ru.fed1v.NauJava.entity.Food;
import ru.fed1v.NauJava.entity.NutritionalValue;
import ru.fed1v.NauJava.repository.dish.DishRepository;
import ru.fed1v.NauJava.repository.food.FoodRepository;

import java.util.Optional;

@SpringBootTest
public class DishTest {

    private final DishRepository dishRepository;
    private final FoodRepository foodRepository;

    @Autowired
    public DishTest(DishRepository dishRepository, FoodRepository foodRepository) {
        this.dishRepository = dishRepository;
        this.foodRepository = foodRepository;
    }

    @Test
    public void testFindDishById() {
        NutritionalValue nutritionalValue = new NutritionalValue(12, 13, 14);
        Food food = new Food("TestRice", "Good Rice", 100.0, nutritionalValue);
        nutritionalValue.setFood(food);
        Dish dish = new Dish("TestDish", "Very Tasty", 250.0, food);
        dish.setFood(food);

        dishRepository.save(dish);

        Assertions.assertTrue(dishRepository.existsById(dish.getId()));
    }

    @Test
    public void testFoodInsertedWhenInsertDish() {
        NutritionalValue nutritionalValue = new NutritionalValue(12, 13, 14);
        Food food = new Food("TestRice", "Good Rice", 100.0, nutritionalValue);
        nutritionalValue.setFood(food);
        Dish dish = new Dish("TestDish", "Very Tasty", 250.0, food);
        dish.setFood(food);

        dishRepository.save(dish);

        Optional<Food> foodFromRepo = foodRepository.findById(food.getId());

        Assertions.assertTrue(foodFromRepo.isPresent());
        Assertions.assertEquals(food, foodFromRepo.get());
    }
}
