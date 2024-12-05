package ru.fed1v.NauJava.service.nutritional_value;

import ru.fed1v.NauJava.entity.NutritionalValue;

import java.util.List;

/**
 * Сервис для работы с пищевой ценностью
 */
public interface NutritionalValueService {

    /**
     * Создать пищевую ценность
     */
    void createNutritionalValue(NutritionalValue nutritionalValue);

    /**
     * Получить пищевую ценность по id
     */
    NutritionalValue findById(Long id);

    /**
     * Получить список всех пищевых ценностей
     */
    List<NutritionalValue> getAll();

    /**
     * Обновить пищевую ценность
     */
    void updateNutritionalValue(NutritionalValue nutritionalValue);

    /**
     * Удалить пищевую ценность
     */
    void deleteNutritionalValue(Long id);
}
