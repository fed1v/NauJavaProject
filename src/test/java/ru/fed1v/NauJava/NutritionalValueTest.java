package ru.fed1v.NauJava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.fed1v.NauJava.entity.NutritionalValue;
import ru.fed1v.NauJava.repository.NutritionalValueRepository;

import java.util.Optional;

@SpringBootTest
public class NutritionalValueTest {

    private final NutritionalValueRepository repository;

    @Autowired
    public NutritionalValueTest(NutritionalValueRepository repository) {
        this.repository = repository;
    }

    @Test
    void testFindNutritionalValueById() {
        double proteinPer100g = 20.5;
        double fatPer100g = 15.3;
        double carbohydratePer100g = 15.3;

        NutritionalValue nutritionalValue = new NutritionalValue(proteinPer100g, fatPer100g, carbohydratePer100g);
        
        NutritionalValue savedValue = repository.save(nutritionalValue);
        Optional<NutritionalValue> foundNutritionalValue = repository.findById(savedValue.getId());
        
        Assertions.assertTrue(foundNutritionalValue.isPresent());
        Assertions.assertEquals(nutritionalValue, foundNutritionalValue.get());
    }
}
