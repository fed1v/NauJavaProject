package ru.fed1v.NauJava.configuration;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.fed1v.NauJava.components.CommandProcessor;
import ru.fed1v.NauJava.entity.Food;
import ru.fed1v.NauJava.service.food.FoodService;
import ru.fed1v.NauJava.service.nutritional_value.NutritionalValueService;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Config {
    
    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public List<Food> foodContainer() {
        return new ArrayList<>();
    }
    
    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public CommandProcessor commandProcessor(FoodService foodService, NutritionalValueService nutritionalValueService) {
        return new CommandProcessor(foodService, nutritionalValueService);
    }
}
