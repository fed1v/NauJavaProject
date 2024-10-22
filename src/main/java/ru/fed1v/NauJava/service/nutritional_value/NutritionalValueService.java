package ru.fed1v.NauJava.service.nutritional_value;

import ru.fed1v.NauJava.entity.NutritionalValue;

import java.util.List;

public interface NutritionalValueService {

    void createNutritionalValue(NutritionalValue nutritionalValue);

    NutritionalValue findById(Long id);

    List<NutritionalValue> getAll();

    void updateNutritionalValue(NutritionalValue nutritionalValue);

    void deleteNutritionalValue(Long id);
}
