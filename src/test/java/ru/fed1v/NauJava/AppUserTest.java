package ru.fed1v.NauJava;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import ru.fed1v.NauJava.entity.*;
import ru.fed1v.NauJava.repository.MealRepository;
import ru.fed1v.NauJava.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class AppUserTest {
    
    private final UserRepository userRepository;
    private final MealRepository mealRepository;

    @Autowired
    public AppUserTest(UserRepository userRepository, MealRepository mealRepository) {
        this.userRepository = userRepository;
        this.mealRepository = mealRepository;
    }
    
    @Test
    @Transactional
    @Commit
    public void testFindUserById() {
        List<Dish> dishes = getDishes();

        Meal meal = new Meal("Cool Meal", Meal.Type.LUNCH, LocalDateTime.now(), dishes);
        AppUser user = new AppUser("Ivan", 37, AppUser.Gender.MALE, List.of(meal));
        
        userRepository.save(user);
        
        Optional<AppUser> userFromRepository = userRepository.findById(user.getId());

        Assertions.assertTrue(userFromRepository.isPresent());
        AppUser userFromDb = userFromRepository.get();
        
        Assertions.assertEquals(user, userFromDb);
    }

    private static List<Dish> getDishes() {
        NutritionalValue nutritionalValue1 = new NutritionalValue(1, 2, 3);
        NutritionalValue nutritionalValue2 = new NutritionalValue(4, 5, 6);

        Food food1 = new Food("Rice", "Tasty", 100.0, nutritionalValue1);
        Food food2 = new Food("Potato", "Not Tasty", 10.0, nutritionalValue2);
        nutritionalValue1.setFood(food1);
        nutritionalValue2.setFood(food2);

        Dish dish1 = new Dish("Fried Rice", "Ok", 250.0, food1);
        Dish dish2 = new Dish("Fried Potato", "Not ok", 123.0, food2);
//        food1.getDishes().add(dish1);
//        food2.getDishes().add(dish2);

        List<Dish> dishes = List.of(dish1, dish2);
        return dishes;
    }
}
