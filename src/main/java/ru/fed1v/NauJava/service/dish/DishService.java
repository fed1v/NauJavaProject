package ru.fed1v.NauJava.service.dish;

import ru.fed1v.NauJava.entity.Dish;

import java.util.List;

public interface DishService {

    void createDish(Dish dish);

    Dish findById(Long id);

    List<Dish> getAll();

    void updateDish(Dish dish);

    void deleteDish(Long id);
}
