package ru.fed1v.NauJava.repository.nutritional_value;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.fed1v.NauJava.entity.NutritionalValue;

@RepositoryRestResource
public interface NutritionalValueRepository extends CrudRepository<NutritionalValue, Long> {
    
}