package ru.fed1v.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.fed1v.NauJava.entity.Food;

@RepositoryRestResource
public interface FoodRepository extends CrudRepository<Food, Long> {
    
}
