package ru.fed1v.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.fed1v.NauJava.entity.Meal;

@RepositoryRestResource
public interface MealRepository extends CrudRepository<Meal, Long> {
}
