package ru.fed1v.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.fed1v.NauJava.entity.Food;

public interface FoodRepository extends CrudRepository<Food, Long> {
    
}
