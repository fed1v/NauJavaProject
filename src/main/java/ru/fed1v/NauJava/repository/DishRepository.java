package ru.fed1v.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.fed1v.NauJava.entity.Dish;

import java.util.List;

public interface DishRepository extends CrudRepository<Dish, Long> {
    
    List<Dish> findDishesByMealId(long mealId);
}
