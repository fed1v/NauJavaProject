package ru.fed1v.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fed1v.NauJava.entity.Meal;
import ru.fed1v.NauJava.repository.MealRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;

    @Autowired
    public MealServiceImpl(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Override
    public void createMeal(Meal meal) {
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
        return meals;
    }

    @Override
    public void updateMeal(Meal meal) {
        mealRepository.save(meal);
    }

    @Override
    public void deleteMeal(Long id) {
        mealRepository.deleteById(id);
    }
}
