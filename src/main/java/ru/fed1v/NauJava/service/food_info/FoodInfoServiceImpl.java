package ru.fed1v.NauJava.service.food_info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fed1v.NauJava.entity.FoodInfo;
import ru.fed1v.NauJava.repository.food_info.FoodInfoRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.StreamSupport;

/**
 * Реализация сервиса для работы с продуктами из справочника
 */
@Service
public class FoodInfoServiceImpl implements FoodInfoService {

    private final FoodInfoRepository foodInfoRepository;

    @Autowired
    public FoodInfoServiceImpl(FoodInfoRepository foodInfoRepository) {
        this.foodInfoRepository = foodInfoRepository;
    }

    @Override
    public void createFoodInfo(FoodInfo food) {
        foodInfoRepository.save(food);
    }

    @Override
    public FoodInfo findById(Long id) {
        return foodInfoRepository
                .findById(id)
                .orElse(null);
    }

    @Override
    public List<FoodInfo> getAll() {

        return StreamSupport
                .stream(foodInfoRepository.findAll().spliterator(), false)
                .sorted(Comparator.comparing(FoodInfo::getId))
                .toList();
    }

    @Override
    public void updateFoodInfo(FoodInfo food) {
        foodInfoRepository.save(food);
    }

    @Override
    public void deleteFoodInfo(Long id) {
        foodInfoRepository.deleteById(id);
    }
}
