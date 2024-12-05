package ru.fed1v.NauJava.repository.food;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.fed1v.NauJava.entity.Food;

/**
 * Репозиторий для работы с продуктами
 */
@RepositoryRestResource
public interface FoodRepository extends CrudRepository<Food, Long> {
    
}
