package ru.fed1v.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fed1v.NauJava.entity.Dish;
import ru.fed1v.NauJava.repository.DishRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;

    @Autowired
    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }


    @Override
    public void createDish(Dish dish) {
        dishRepository.save(dish);
    }

    @Override
    public Dish findById(Long id) {
        return dishRepository
                .findById(id)
                .orElse(null);
    }

    @Override
    public List<Dish> getAll() {
        List<Dish> dishes = new ArrayList<>();
        dishRepository.findAll().forEach(dishes::add);
        return dishes;
    }

    @Override
    public void updateDish(Dish dish) {
        dishRepository.save(dish);
    }

    @Override
    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
    }
}
