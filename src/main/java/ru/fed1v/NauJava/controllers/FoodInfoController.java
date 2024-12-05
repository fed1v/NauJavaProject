package ru.fed1v.NauJava.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.fed1v.NauJava.entity.FoodInfo;
import ru.fed1v.NauJava.entity.NutritionalValue;
import ru.fed1v.NauJava.service.food_info.FoodInfoService;

import java.util.List;

/**
 * Класс, обрабатывающий запросы, относящиеся к продуктам из справочника
 */
@Controller
@RequestMapping("/foods")
public class FoodInfoController {

    /**
     * Сервис для работы с продуктами
     */
    private final FoodInfoService foodInfoService;

    @Autowired
    public FoodInfoController(FoodInfoService foodInfoService) {
        this.foodInfoService = foodInfoService;
    }

    /**
     * Отображает страницу со справочником продуктов
     *
     * @return имя шаблона, отображающего страницу со справочником продуктов
     */
    @GetMapping
    public String getAllFoods(Model model) {
        List<FoodInfo> foods = foodInfoService.getAll();
        model.addAttribute("foods", foods);
        return "foods";
    }

    /**
     * Отображает страницу создания продукта
     *
     * @return имя шаблона, отображающего страницу создания продукта
     */
    @GetMapping("/create")
    @PreAuthorize("hasRole(\"ADMIN\")")
    public String createFood(Model model) {

        model.addAttribute("food", new FoodInfo());
        model.addAttribute("nutritionalValue", new NutritionalValue());

        return "food-create";
    }


    /**
     * Создает продукт
     *
     * @return перенаправляет на страницу с продуктами
     */
    @PostMapping("/create")
    @PreAuthorize("hasRole(\"ADMIN\")")
    public String addFood(
            @ModelAttribute FoodInfo foodInfo
    ) {
        foodInfoService.createFoodInfo(foodInfo);
        return "redirect:/foods";
    }

    /**
     * Отображает страницу изменения продукта
     *
     * @return перенаправляет на страницу для изменением продукта
     */
    @GetMapping("/{id}/edit")
    @PreAuthorize("hasRole(\"ADMIN\")")
    public String editFood(
            Model model,
            @PathVariable long id
    ) {

        FoodInfo foodInfo = foodInfoService.findById(id);
        model.addAttribute("food", foodInfo);
        return "food-edit";
    }

    /**
     * Изменяет продукт
     *
     * @return перенаправляет на страницу с продуктами
     */
    @PostMapping("/{id}/edit")
    @PreAuthorize("hasRole(\"ADMIN\")")
    public String updateFood(
            @PathVariable long id,
            @ModelAttribute FoodInfo foodInfo
    ) {

        FoodInfo existingFood = foodInfoService.findById(id);

        existingFood.setName(foodInfo.getName());
        existingFood.setNutritionalValue(foodInfo.getNutritionalValue());
        existingFood.setDescription(foodInfo.getDescription());
        existingFood.setPrice(foodInfo.getPrice());

        foodInfoService.updateFoodInfo(existingFood);

        return "redirect:/foods";
    }

    /**
     * Удаляет продукт
     *
     * @return перенаправляет на страницу с продуктами
     */
    @PostMapping("/{id}/delete")
    @PreAuthorize("hasRole(\"ADMIN\")")
    public String deleteFood(
            @PathVariable long id
    ) {
        foodInfoService.deleteFoodInfo(id);
        return "redirect:/foods";
    }
}
