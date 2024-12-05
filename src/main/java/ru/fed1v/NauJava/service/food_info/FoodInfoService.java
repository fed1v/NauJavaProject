package ru.fed1v.NauJava.service.food_info;

import ru.fed1v.NauJava.entity.FoodInfo;

import java.util.List;

/**
 * Сервис для работы с продуктами из справочника
 */
public interface FoodInfoService {

    /**
     * Создать продукт
     */
    void createFoodInfo(FoodInfo foodInfo);

    /**
     * Получить продукт по id
     */
    FoodInfo findById(Long id);

    /**
     * Получить список всех продуктов
     */
    List<FoodInfo> getAll();

    /**
     * Обновить продукт
     */
    void updateFoodInfo(FoodInfo food);

    /**
     * Удалить продукт
     */
    void deleteFoodInfo(Long id);
}
