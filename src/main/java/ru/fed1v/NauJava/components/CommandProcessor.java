package ru.fed1v.NauJava.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fed1v.NauJava.entity.Food;
import ru.fed1v.NauJava.entity.NutritionalValue;
import ru.fed1v.NauJava.service.food.FoodService;
import ru.fed1v.NauJava.service.nutritional_value.NutritionalValueService;

import java.util.List;

@Component
public class CommandProcessor {

    private final FoodService foodService;
    private final NutritionalValueService nutritionalValueService;
    

    @Autowired
    public CommandProcessor(FoodService foodService, NutritionalValueService nutritionalValueService) {
        this.foodService = foodService;
        this.nutritionalValueService = nutritionalValueService;
    }

    public void processCommand(String input) {
        try {
            String[] inputSplit = input.split(" ");

            String command = inputSplit[0];

            switch (command.toLowerCase()) {
                case "add" -> processAdd(input);
                case "updatefood" -> processUpdateFood(inputSplit);
                case "updatenutr" -> processUpdateNutr(inputSplit);
                case "deletefood" -> processDeleteFood(inputSplit);
                case "deletenutr" -> processDeleteNutr(inputSplit);
                case "get" -> processGet(inputSplit);
                case "getallfood" -> processGetAllFood();
                case "getallnutr" -> processGetAllNutr();
                default -> System.out.println("Unknown command");
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    private void processGetAllNutr() {
        List<NutritionalValue> allNutritionalValues = nutritionalValueService.getAll();
        allNutritionalValues.forEach(System.out::println);
    }

    private void processDeleteNutr(String[] inputSplit) {
        long id = Long.parseLong(inputSplit[1]);
        nutritionalValueService.deleteNutritionalValue(id);
    }

    private void processUpdateNutr(String[] input) {
        long id = Long.parseLong(input[1]);
        double newKcal = Double.parseDouble(input[2]);
        
        NutritionalValue nutritionalValue = nutritionalValueService.findById(id);
        nutritionalValue.setKcalPer100g(newKcal);
        
        nutritionalValueService.updateNutritionalValue(nutritionalValue);
    }

    private void processGetAllFood() {
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

    private void processDeleteFood(String[] inputSplit) {
        Long foodToDeleteId = Long.parseLong(inputSplit[1]);
        foodService.deleteFood(foodToDeleteId);
    }

    private void processUpdateFood(String[] inputSplit) {
        long id = Long.parseLong(inputSplit[1]);
        String newName = inputSplit[2];
        
        Food food = foodService.findById(id);
        food.setName(newName);
        
        foodService.updateFood(food);
    }

    private void processAdd(String input) {
        Food foodToCreate = createFoodFromString(input);
        foodService.createFood(foodToCreate);
    }

    private Food createFoodFromString(String input) {
        String[] inputSplited = input.split(" ");

        String foodName = inputSplited[1];

        double calories = Double.parseDouble(inputSplited[2]);
        
        NutritionalValue nutritionalValue = new NutritionalValue(null, calories);
        Food food = new Food(foodName, "description", 123.0, nutritionalValue);
        
        return food;
    }
}
