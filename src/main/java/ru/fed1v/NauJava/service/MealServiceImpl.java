package ru.fed1v.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.fed1v.NauJava.entity.Dish;
import ru.fed1v.NauJava.entity.Meal;
import ru.fed1v.NauJava.repository.DishRepository;
import ru.fed1v.NauJava.repository.MealRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    private final PlatformTransactionManager transactionManager;
    private final MealRepository mealRepository;
    private final DishRepository dishRepository;

    @Autowired
    public MealServiceImpl(PlatformTransactionManager transactionManager, MealRepository mealRepository, DishRepository dishRepository) {
        this.transactionManager = transactionManager;
        this.mealRepository = mealRepository;
        this.dishRepository = dishRepository;
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
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            List<Dish> dishedOfMeal = dishRepository.findDishesByMealId(id);
            dishRepository.deleteAll(dishedOfMeal);
            mealRepository.deleteById(id);
        } catch (DataAccessException e) {
            transactionManager.rollback(status);
            throw e;
        }
    }
}
