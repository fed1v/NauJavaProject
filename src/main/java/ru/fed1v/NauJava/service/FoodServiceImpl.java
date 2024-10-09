package ru.fed1v.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fed1v.NauJava.entity.Food;
import ru.fed1v.NauJava.repository.FoodRepository;

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
        foodRepository.create(food);
    }

    @Override
    public Food findById(Long id) {
        return foodRepository.read(id);
    }

    @Override
    public List<Food> getAll() {
        return foodRepository.readAll();
    }

    @Override
    public void updateFood(Food food) {
        foodRepository.update(food);
    }

    @Override
    public void deleteFood(Long id) {
        foodRepository.delete(id);
    }
}
