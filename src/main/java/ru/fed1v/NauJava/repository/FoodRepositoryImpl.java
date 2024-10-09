package ru.fed1v.NauJava.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fed1v.NauJava.entity.Food;

import java.util.List;

@Component
public class FoodRepositoryImpl implements CrudRepository<Food, Long> {

    private final List<Food> foodContainer;

    @Autowired
    public FoodRepositoryImpl(List<Food> foodContainer) {
        this.foodContainer = foodContainer;
    }

    @Override
    public void create(Food entity) {
        foodContainer.add(entity);
    }

    @Override
    public Food read(Long id) {
        return foodContainer
                .stream()
                .filter(entity -> entity.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Food> readAll() {
        return foodContainer
                .stream()
                .toList();
    }

    @Override
    public void update(Food entity) {
        delete(entity.getId());
        create(entity);
    }

    @Override
    public void delete(Long id) {
        foodContainer.removeIf(entity -> entity.getId().equals(id));
    }
}
