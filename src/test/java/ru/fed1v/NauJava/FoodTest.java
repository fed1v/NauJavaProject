package ru.fed1v.NauJava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import ru.fed1v.NauJava.entity.Food;
import ru.fed1v.NauJava.entity.NutritionalValue;
import ru.fed1v.NauJava.repository.FoodRepository;
import ru.fed1v.NauJava.repository.NutritionalValueRepository;

import java.util.Optional;

@SpringBootTest
public class FoodTest {

    private final FoodRepository foodRepository;
    private final NutritionalValueRepository nutritionalValueRepository;

    @Autowired
    public FoodTest(FoodRepository foodRepository, NutritionalValueRepository nutritionalValueRepository) {
        this.foodRepository = foodRepository;
        this.nutritionalValueRepository = nutritionalValueRepository;
    }

    @Test
    public void testFindFoodById() {
        NutritionalValue nutritionalValue = new NutritionalValue(20.5, 15.3, 12.1);
        Food food = new Food("Rice", "Tasty", 100.0, nutritionalValue);
        nutritionalValue.setFood(food);
        
        Food savedFood = foodRepository.save(food);
        Optional<Food> foundFood = foodRepository.findById(savedFood.getId());

        Assertions.assertTrue(foundFood.isPresent());
        Assertions.assertEquals(food, foundFood.get());
    }

    @Test
    public void testNutritionalValueInsertedWhenInsertFood() {
        NutritionalValue nutritionalValue = new NutritionalValue(20.0, 15.3, 9.8);
        Food food = new Food("Rice", "description", 123.0, nutritionalValue);
        nutritionalValue.setFood(food);
        
        Food savedFood = foodRepository.save(food);

        Optional<NutritionalValue> nutritionalValueFromRepo = nutritionalValueRepository
                .findById(savedFood.getNutritionalValue().getId());

        Assertions.assertTrue(nutritionalValueFromRepo.isPresent());
        Assertions.assertEquals(nutritionalValueFromRepo.get(), food.getNutritionalValue());
    }

    @Test
    @Transactional
    @Commit
    public void testDeleteNutritionalValueWhenDeleteFood() {
        NutritionalValue nutritionalValue = new NutritionalValue(20.0, 15.3, 9.8);
        Food food = new Food("Rice", "description", 123.0, nutritionalValue);
        nutritionalValue.setFood(food);

        foodRepository.save(food);

        Assertions.assertTrue(
                nutritionalValueRepository.existsById(nutritionalValue.getId()),
                "Value doesn't exit in repository"
        );
        Assertions.assertNotNull(food.getId());

        foodRepository.deleteById(food.getId());
        
        Assertions.assertFalse(nutritionalValueRepository.existsById(nutritionalValue.getId()));
    }
}
