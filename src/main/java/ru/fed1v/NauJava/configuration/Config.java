package ru.fed1v.NauJava.configuration;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.fed1v.NauJava.controller.CommandProcessor;
import ru.fed1v.NauJava.entity.Food;
import ru.fed1v.NauJava.repository.FoodRepository;
import ru.fed1v.NauJava.service.FoodService;
import ru.fed1v.NauJava.service.FoodServiceImpl;

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
    public FoodRepository foodRepository() {
        return new FoodRepository(foodContainer());
    }
    
    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public FoodService foodService(FoodRepository foodRepository) {
        return new FoodServiceImpl(foodRepository);
    }
    
    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public CommandProcessor commandProcessor(FoodService foodService) {
        return new CommandProcessor(foodService);
    }
}
