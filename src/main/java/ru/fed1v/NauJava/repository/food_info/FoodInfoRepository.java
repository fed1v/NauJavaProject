package ru.fed1v.NauJava.repository.food_info;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.fed1v.NauJava.entity.FoodInfo;


/**
 * Репозиторий для работы с продуктами из справочника
 */
@RepositoryRestResource
public interface FoodInfoRepository extends CrudRepository<FoodInfo, Long> {
}
