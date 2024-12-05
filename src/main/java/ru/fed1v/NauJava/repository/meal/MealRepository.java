package ru.fed1v.NauJava.repository.meal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.fed1v.NauJava.entity.Meal;

import java.time.LocalDateTime;
import java.util.List;


/**
 * Репозиторий для работы с приемами пищи
 */
@RepositoryRestResource
public interface MealRepository extends CrudRepository<Meal, Long> {

    /**
     * Возвращает приемы пищи с даты start до даты end
     */
    List<Meal> findMealsByDateTimeBetween(LocalDateTime start, LocalDateTime end);

    /**
     * Возвращает приемы пищи пользователя
     */
    List<Meal> findMealsByAppUserId(Long appUserId);
}
