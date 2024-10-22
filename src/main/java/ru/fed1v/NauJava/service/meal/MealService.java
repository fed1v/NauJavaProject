package ru.fed1v.NauJava.service.meal;

import ru.fed1v.NauJava.entity.Meal;

import java.util.List;

public interface MealService {

    void createMeal(Meal meal);

    Meal findById(Long id);

    List<Meal> getAll();

    void updateMeal(Meal meal);

    void deleteMeal(Long id);
}
