package ru.fed1v.NauJava.service.meal;

import ru.fed1v.NauJava.entity.Meal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Сервис для работы с приемами пищи
 */
public interface MealService {

    void createMeal(Meal meal);

    /**
     * Получить прием пищи по id
     */
    Meal findById(Long id);

    /**
     * Получить список всех приемов пищи
     */
    List<Meal> getAll();


    /**
     * Получить список всех приемов пищи пользователя
     */
    List<Meal> getAllByUserId(Long id);


    /**
     * Получить список приемов пищи пользователя с определенными id
     */
    List<Meal> getAllWithIdsByUserId(Long userId, List<Long> ids);


    /**
     * Получить список приемов пищи пользователя в определенном временном промежутке
     */
    List<Meal> getUserMealsByDateRange(Long userId, LocalDateTime start, LocalDateTime end);

    /**
     * Получить отфильтрованный и отсортированный список приемов пищи
     *
     * @param userId id пользователя, у которого мы получаем приемы пищи
     * @param type   тип (завтрак, обед, ужин)
     * @param start  дата начала
     * @param end    дата конца
     * @param sortBy параметр сортировки (белки, жиры, углеводы, ккал)
     * @return отфильтрованный и отсортированный список приемов пищи
     */
    List<Meal> getMealsFilteredSortedMeals(Long userId, String type, String start, String end, String sortBy);

    /**
     * Обновить прием пищи
     */
    void updateMeal(Meal meal);

    /**
     * Удалить прием пищи
     */
    void deleteMeal(Long id);
}
