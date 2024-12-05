package ru.fed1v.NauJava.service.dish;

import ru.fed1v.NauJava.entity.Dish;

import java.util.List;

/**
 * Сервис для работы с блюдами
 */
public interface DishService {

    /**
     * Создать блюдо
     */
    void createDish(Dish dish);

    /**
     * Получить блюдо по id
     */
    Dish findById(Long id);

    /**
     * Получить список всех блюд
     */
    List<Dish> getAll();

    /**
     * Обновить блюдо
     */
    void updateDish(Dish dish);

    /**
     * Удалить блюдо
     */
    void deleteDish(Long id);
}
