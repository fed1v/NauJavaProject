package ru.fed1v.NauJava.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.fed1v.NauJava.entity.Food;
import ru.fed1v.NauJava.repository.FoodRepository;

@Controller
@RequestMapping("/foods")
public class FoodController {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodController(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @GetMapping
    public String getAllFoods(Model model) {
        Iterable<Food> foods = foodRepository.findAll();
        model.addAttribute("foods", foods);
        return "foods";
    }
}
