package ru.fed1v.NauJava.service.food;

import ru.fed1v.NauJava.entity.Food;

import java.util.List;

/**
 * Сервис для работы с продуктами
 */
public interface FoodService {

    /**
     * Создать продук
     */
    void createFood(Food food);

    /**
     * Получить отчет по id
     */
    Food findById(Long id);

    /**
     * Получить список всех продуктов
     */
    List<Food> getAll();

    /**
     * Обновить продукт
     */
    void updateFood(Food food);

    /**
     * Удалить продукт
     */
    void deleteFood(Long id);
}
