package ru.fed1v.NauJava.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fed1v.NauJava.entity.Food;
import ru.fed1v.NauJava.entity.NutritionalValue;
import ru.fed1v.NauJava.service.FoodService;

import java.util.List;

@Component
public class CommandProcessor {

    private final FoodService foodService;

    @Autowired
    public CommandProcessor(FoodService foodService) {
        this.foodService = foodService;
    }

    public void processCommand(String input) {
        try {
            String[] inputSplit = input.split(" ");

            String command = inputSplit[0];

            switch (command) {
                case "add" -> processAdd(input);
                case "update" -> processUpdate(input);
                case "delete" -> processDelete(inputSplit);
                case "get" -> processGet(inputSplit);
                case "getAll" -> processGetAll();
                default -> System.out.println("Unknown command");
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    private void processGetAll() {
        List<Food> allFood = foodService.getAll();

        if (!allFood.isEmpty()) {
            allFood.forEach(System.out::println);
        } else {
            System.out.println("No Food");
        }
    }

    private void processGet(String[] inputSplit) {
        Long foodId = Long.parseLong(inputSplit[1]);
        Food foodFromRepository = foodService.findById(foodId);
        System.out.println(foodFromRepository);
    }

    private void processDelete(String[] inputSplit) {
        Long foodToDeleteId = Long.parseLong(inputSplit[1]);
        foodService.deleteFood(foodToDeleteId);
    }

    private void processUpdate(String input) {
        Food foodToUpdate = createFoodFromString(input);
        foodService.updateFood(foodToUpdate);
    }

    private void processAdd(String input) {
        Food foodToCreate = createFoodFromString(input);
        foodService.createFood(foodToCreate);
    }

    private Food createFoodFromString(String input) {
        String[] inputSplited = input.split(" ");

        Long foodId = Long.parseLong(inputSplited[1]);

        String foodName = inputSplited[2];
        double calories = Double.parseDouble(inputSplited[3]);

        NutritionalValue nutritionalValue = new NutritionalValue(calories);
        return new Food(foodId, foodName, nutritionalValue);
    }
}
