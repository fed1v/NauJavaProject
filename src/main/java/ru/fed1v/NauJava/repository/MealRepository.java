package ru.fed1v.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.fed1v.NauJava.entity.Meal;

public interface MealRepository extends CrudRepository<Meal, Long> {
}
