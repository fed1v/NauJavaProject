package ru.fed1v.NauJava;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import ru.fed1v.NauJava.entity.*;
import ru.fed1v.NauJava.repository.AppUserRepositoryCustom;
import ru.fed1v.NauJava.repository.MealRepository;
import ru.fed1v.NauJava.repository.AppUserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class AppUserTest {

    private final AppUserRepository userRepository;
    private final AppUserRepositoryCustom userRepositoryCustom;

    @Autowired
    public AppUserTest(AppUserRepository userRepository, AppUserRepositoryCustom userRepositoryCustom, MealRepository mealRepository) {
        this.userRepository = userRepository;
        this.userRepositoryCustom = userRepositoryCustom;
    }

    @Test
    @Transactional
    @Commit
    public void testFindUserById() {
        AppUser user = getAppUser();

        userRepository.save(user);

        Optional<AppUser> userFromRepository = userRepository.findById(user.getId());

        Assertions.assertTrue(userFromRepository.isPresent());
        AppUser userFromDb = userFromRepository.get();

        Assertions.assertEquals(user, userFromDb);
    }

    @Test
    public void testFindUsersWithAgeGreaterThan() {
        List<Dish> dishes1 = getDishes();
        List<Dish> dishes2 = getDishes();
        List<Dish> dishes3 = getDishes();

        Meal meal1 = new Meal("Cool Meal", Meal.Type.LUNCH, LocalDateTime.now(), dishes1);
        Meal meal2 = new Meal("Cool Meal", Meal.Type.LUNCH, LocalDateTime.now(), dishes2);
        Meal meal3 = new Meal("Cool Meal", Meal.Type.LUNCH, LocalDateTime.now(), dishes3);

        AppUser user1 = new AppUser("Ivan", 42, AppUser.Gender.MALE, List.of(meal1));
        AppUser user2 = new AppUser("Oleg", 18, AppUser.Gender.MALE, List.of(meal2));
        AppUser user3 = new AppUser("Oleg", 53, AppUser.Gender.MALE, List.of(meal3));

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        int age = 40;

        List<AppUser> usersOlderThanAge = userRepository.findAppUsersByAgeGreaterThan(age);

        Assertions.assertFalse(usersOlderThanAge.isEmpty());

        usersOlderThanAge.forEach(user -> {
            Assertions.assertTrue(user.getAge() > age);
        });

        Iterable<AppUser> allUsers = userRepository.findAll();

        int userOtherThanAgeCount = 0;
        for (AppUser user : allUsers) {
            if (user.getAge() > age) {
                userOtherThanAgeCount++;
            }
        }

        Assertions.assertEquals(userOtherThanAgeCount, usersOlderThanAge.size());
    }

    @Test
    public void testFindAppUsersByGenderAndAge() {
        List<Dish> dishes1 = getDishes();
        List<Dish> dishes2 = getDishes();
        List<Dish> dishes3 = getDishes();
        List<Dish> dishes4 = getDishes();

        Meal meal1 = new Meal("Cool Meal", Meal.Type.LUNCH, LocalDateTime.now(), dishes1);
        Meal meal2 = new Meal("Cool Meal", Meal.Type.LUNCH, LocalDateTime.now(), dishes2);
        Meal meal3 = new Meal("Cool Meal", Meal.Type.LUNCH, LocalDateTime.now(), dishes3);
        Meal meal4 = new Meal("Cool Meal", Meal.Type.LUNCH, LocalDateTime.now(), dishes4);

        AppUser user1 = new AppUser("Ivan", 42, AppUser.Gender.MALE, List.of(meal1));
        AppUser user2 = new AppUser("Ivan", 18, AppUser.Gender.MALE, List.of(meal2));
        AppUser user3 = new AppUser("Oleg", 18, AppUser.Gender.MALE, List.of(meal3));
        AppUser user4 = new AppUser("Oleg", 53, AppUser.Gender.MALE, List.of(meal4));

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);

        String name = "Oleg";
        int age = 18;

        List<AppUser> usersWithNameAndAge = userRepository.findAppUsersByNameAndAge(name, age);

        Assertions.assertFalse(usersWithNameAndAge.isEmpty());

        for (AppUser user : usersWithNameAndAge) {
            Assertions.assertEquals(user.getAge(), age);
            Assertions.assertEquals(user.getName(), name);
        }

        Iterable<AppUser> allUsers = userRepository.findAll();
        int validUsersCount = 0;

        for (AppUser user : allUsers) {
            if (user.getAge() == age && user.getName().equals(name)) {
                validUsersCount++;
            }
        }

        Assertions.assertEquals(validUsersCount, usersWithNameAndAge.size());
    }

    @Test
    public void testCriteriaApiFindAppUsersByAgeGreaterThan() {
        List<Dish> dishes1 = getDishes();
        List<Dish> dishes2 = getDishes();
        List<Dish> dishes3 = getDishes();
        List<Dish> dishes4 = getDishes();

        Meal meal1 = new Meal("Cool Meal", Meal.Type.LUNCH, LocalDateTime.now(), dishes1);
        Meal meal2 = new Meal("Cool Meal", Meal.Type.LUNCH, LocalDateTime.now(), dishes2);
        Meal meal3 = new Meal("Cool Meal", Meal.Type.LUNCH, LocalDateTime.now(), dishes3);
        Meal meal4 = new Meal("Cool Meal", Meal.Type.LUNCH, LocalDateTime.now(), dishes4);

        AppUser user1 = new AppUser("Ivan", 42, AppUser.Gender.MALE, List.of(meal1));
        AppUser user2 = new AppUser("Ivan", 18, AppUser.Gender.MALE, List.of(meal2));
        AppUser user3 = new AppUser("Oleg", 18, AppUser.Gender.MALE, List.of(meal3));
        AppUser user4 = new AppUser("Oleg", 53, AppUser.Gender.MALE, List.of(meal4));

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);

        int age = 35;

        List<AppUser> usersOlderThan = userRepositoryCustom.findAppUsersByAgeGreaterThan(age);

        Assertions.assertFalse(usersOlderThan.isEmpty());
        usersOlderThan.forEach(user -> Assertions.assertTrue(user.getAge() > age));

        Iterable<AppUser> allUsers = userRepository.findAll();
        int validUsersCount = 0;
        for (AppUser user : allUsers) {
            if (user.getAge() > age) {
                validUsersCount++;
            }
        }

        Assertions.assertEquals(validUsersCount, usersOlderThan.size());
    }

    @Test
    public void testCriteriaApiFindAppUsersByNameAndAge(){
        List<Dish> dishes1 = getDishes();
        List<Dish> dishes2 = getDishes();
        List<Dish> dishes3 = getDishes();
        List<Dish> dishes4 = getDishes();

        Meal meal1 = new Meal("Cool Meal", Meal.Type.LUNCH, LocalDateTime.now(), dishes1);
        Meal meal2 = new Meal("Cool Meal", Meal.Type.LUNCH, LocalDateTime.now(), dishes2);
        Meal meal3 = new Meal("Cool Meal", Meal.Type.LUNCH, LocalDateTime.now(), dishes3);
        Meal meal4 = new Meal("Cool Meal", Meal.Type.LUNCH, LocalDateTime.now(), dishes4);

        AppUser user1 = new AppUser("Ivan", 42, AppUser.Gender.MALE, List.of(meal1));
        AppUser user2 = new AppUser("Ivan", 18, AppUser.Gender.MALE, List.of(meal2));
        AppUser user3 = new AppUser("Oleg", 18, AppUser.Gender.MALE, List.of(meal3));
        AppUser user4 = new AppUser("Oleg", 53, AppUser.Gender.MALE, List.of(meal4));

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        
        String name = "Oleg";
        int age = 18;
        
        List<AppUser> usersWithNameAndAge = userRepositoryCustom.findAppUsersByNameAndAge(name, age);

        Assertions.assertFalse(usersWithNameAndAge.isEmpty());

        for (AppUser user : usersWithNameAndAge) {
            Assertions.assertEquals(user.getAge(), age);
            Assertions.assertEquals(user.getName(), name);
        }

        Iterable<AppUser> allUsers = userRepository.findAll();
        int validUsersCount = 0;

        for (AppUser user : allUsers) {
            if (user.getAge() == age && user.getName().equals(name)) {
                validUsersCount++;
            }
        }

        Assertions.assertEquals(validUsersCount, usersWithNameAndAge.size());
    }
    
    @AfterEach
    public void cleanUp() {
//        userRepository.deleteAll();
    }

    private static AppUser getAppUser() {
        List<Dish> dishes = getDishes();

        Meal meal = new Meal("Cool Meal", Meal.Type.LUNCH, LocalDateTime.now(), dishes);
        return new AppUser("Ivan", 37, AppUser.Gender.MALE, List.of(meal));
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

        List<Dish> dishes = List.of(dish1, dish2);
        return dishes;
    }
}
