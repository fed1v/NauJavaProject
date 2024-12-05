package ru.fed1v.NauJava.service.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fed1v.NauJava.entity.Dish;
import ru.fed1v.NauJava.entity.Meal;
import ru.fed1v.NauJava.repository.dish.DishRepository;
import ru.fed1v.NauJava.repository.meal.MealRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Реализация сервиса для работы с приемами пищи
 */
@Service
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;
    private final DishRepository dishRepository;

    @Autowired
    public MealServiceImpl(MealRepository mealRepository, DishRepository dishRepository) {
        this.mealRepository = mealRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public void createMeal(Meal meal) {
        meal.getDishes().forEach(dish -> dish.setMeal(meal));
        mealRepository.save(meal);
    }

    @Override
    public Meal findById(Long id) {
        return mealRepository
                .findById(id)
                .orElse(null);
    }

    @Override
    public List<Meal> getAll() {
        List<Meal> meals = new ArrayList<>();
        mealRepository.findAll().forEach(meals::add);
        meals.sort(Comparator.comparing(Meal::getDateTime));
        return meals.reversed();
    }

    @Override
    public List<Meal> getAllByUserId(Long id) {
        List<Meal> meals = mealRepository.findMealsByAppUserId(id);
        meals.sort(Comparator.comparing(Meal::getDateTime));
        return meals.reversed();
    }

    @Override
    public List<Meal> getAllWithIdsByUserId(Long userId, List<Long> ids) {
        return mealRepository
                .findMealsByAppUserId(userId)
                .stream()
                .filter(meal -> ids.contains(meal.getId()))
                .sorted(Comparator.comparing(Meal::getDateTime))
                .toList().reversed();
    }

    @Override
    public List<Meal> getUserMealsByDateRange(Long userId, LocalDateTime start, LocalDateTime end) {
        return mealRepository.findMealsByDateTimeBetween(start, end)
                .stream()
                .filter(meal -> meal.getAppUser() != null && meal.getAppUser().getId().equals(userId))
                .sorted(Comparator.comparing(Meal::getDateTime))
                .toList().reversed();
    }

    @Override
    public List<Meal> getMealsFilteredSortedMeals(Long userId, String type, String start, String end, String sortBy) {
        List<Meal> meals = getAllByUserId(userId);

        if (start != null && end != null && !start.trim().isEmpty() && !end.trim().isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

            LocalDateTime dateStart = LocalDateTime.parse(start, formatter);
            LocalDateTime dateEnd = LocalDateTime.parse(end, formatter);

            meals = getUserMealsByDateRange(userId, dateStart, dateEnd);
        }

        if (type != null) {
            meals = getFilteredMeals(meals, Meal.Type.fromName(type));
        }

        if (sortBy != null) {
            meals = getSortedMeals(meals, sortBy);
        }

        return meals;
    }

    private List<Meal> getFilteredMeals(List<Meal> meals, Meal.Type type) {
        if (type == null) {
            return meals;
        }

        return meals
                .stream()
                .filter(meal -> meal.getType().equals(type))
                .toList();
    }

    private List<Meal> getSortedMeals(List<Meal> meals, String sort) {
        if (sort == null || sort.isBlank()) {
            return meals;
        }

        return meals
                .stream()
                .sorted(getComparatorBySortName(sort))
                .toList()
                .reversed();
    }


    private Comparator<Meal> getComparatorBySortName(String sort) {
        switch (sort) {
            case "protein" -> {
                return Comparator.comparingDouble(Meal::getTotalProteinGrams);
            }
            case "fat" -> {
                return Comparator.comparingDouble(Meal::getTotalFatGrams);
            }
            case "carbohydrate" -> {
                return Comparator.comparingDouble(Meal::getTotalCarbohydrateGrams);
            }
            case "kcal" -> {
                return Comparator.comparingDouble(Meal::getTotalKcal);
            }
            default -> throw new IllegalArgumentException("Unknown sort");
        }
    }


    @Override
    public void updateMeal(Meal meal) {
        List<Dish> dishesToDelete = dishRepository.findDishesByMealId(meal.getId());
        dishRepository.deleteAll(dishesToDelete);

        meal.getDishes().forEach(dish -> dish.setMeal(meal));

        mealRepository.save(meal);
    }

    @Override
    public void deleteMeal(Long id) {
        List<Dish> dishesOfMeal = dishRepository.findDishesByMealId(id);
        dishRepository.deleteAll(dishesOfMeal);
        mealRepository.deleteById(id);
    }
}
