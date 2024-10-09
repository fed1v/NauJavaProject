package ru.fed1v.NauJava.service;

import ru.fed1v.NauJava.entity.Food;

import java.util.List;

public interface FoodService {
    
    void createFood(Food food);
    
    Food findById(Long id);
    
    List<Food> getAll();
    
    void updateFood(Food food);
    
    void deleteFood(Long id);
}
