package ru.fed1v.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.fed1v.NauJava.entity.Dish;

import java.util.List;

@RepositoryRestResource
public interface DishRepository extends CrudRepository<Dish, Long> {
    
    List<Dish> findDishesByMealId(long mealId);
}
