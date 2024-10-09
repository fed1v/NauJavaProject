package ru.fed1v.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.fed1v.NauJava.entity.NutritionalValue;

public interface NutritionalValueRepository extends CrudRepository<NutritionalValue, Long> {
    
}
