package ru.fed1v.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fed1v.NauJava.entity.Food;
import ru.fed1v.NauJava.repository.FoodRepository;
import ru.fed1v.NauJava.repository.FoodRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public void createFood(Food food) {
        foodRepository.save(food);
    }

    @Override
    public Food findById(Long id) {
        return foodRepository
                .findById(id)
                .orElse(null);
    }

    @Override
    public List<Food> getAll() {
        List<Food> foods = new ArrayList<>();
        foodRepository.findAll().forEach(foods::add);
        return foods;
    }

    @Override
    public void updateFood(Food food) {
        foodRepository.save(food);
    }

    @Override
    public void deleteFood(Long id) {
        foodRepository.deleteById(id);
    }
}
