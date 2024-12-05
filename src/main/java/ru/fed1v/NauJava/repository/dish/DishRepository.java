package ru.fed1v.NauJava.repository.dish;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.fed1v.NauJava.entity.Dish;

import java.util.List;

/**
 * Репозиторий для работы с блюдами
 */
@RepositoryRestResource
public interface DishRepository extends CrudRepository<Dish, Long> {

    /**
     * Возвращает список блюд, относящихся к нужному приему пищи
     */
    List<Dish> findDishesByMealId(long mealId);
}
