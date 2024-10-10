package ru.fed1v.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.fed1v.NauJava.entity.Dish;

public interface DishRepository extends CrudRepository<Dish, Long> {
}
