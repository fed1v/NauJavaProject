package ru.fed1v.NauJava.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.fed1v.NauJava.controllers.dto.DishDto;
import ru.fed1v.NauJava.controllers.dto.DishesDto;
import ru.fed1v.NauJava.controllers.dto.MealDto;
import ru.fed1v.NauJava.controllers.dto.MealsDto;
import ru.fed1v.NauJava.entity.*;
import ru.fed1v.NauJava.service.app_user.AppUserService;
import ru.fed1v.NauJava.service.dish.DishService;
import ru.fed1v.NauJava.service.meal.MealService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс, обрабатывающий запросы, относящиеся к приемам пищи
 */
@Controller
@RequestMapping("/meals")
public class MealController {

    /**
     * Сервис для работы с приемами пищи
     */
    private final MealService mealService;

    /**
     * Сервис для работы с пользователями
     */
    private final AppUserService appUserService;

    /**
     * Сервис для работы с блюдами
     */
    private final DishService dishService;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public MealController(MealService mealService, AppUserService appUserService, DishService dishService) {
        this.mealService = mealService;
        this.appUserService = appUserService;
        this.dishService = dishService;
    }

    /**
     * Отображает страницу с приемами пищи
     * с фильтрацией по параметрам и сортировкой
     *
     * @param type      тип (завтрак, обед, ужин)
     * @param dateStart начальная дата
     * @param dateEnd   конечная дата
     * @param sort      сортировка (белки, жиры, углеводы, килокалории)
     * @return имя шаблона, отображающего страницу с приемами пищи
     */
    @GetMapping
    public String getUserMeals(
            @AuthenticationPrincipal UserDetails currentUser,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "dateStart", required = false) String dateStart,
            @RequestParam(value = "dateEnd", required = false) String dateEnd,
            @RequestParam(value = "sort", required = false) String sort,
            Model model
    ) {
        AppUser appUser = appUserService.findAppUserByUsername(currentUser.getUsername());

        List<Meal> meals = mealService.getMealsFilteredSortedMeals(appUser.getId(), type, dateStart, dateEnd, sort);

        LocalDateTime start = null;
        LocalDateTime end = null;

        if (dateStart != null && dateEnd != null && !dateStart.trim().isEmpty() && !dateEnd.trim().isEmpty()) {
            start = LocalDateTime.parse(dateStart, dateTimeFormatter);
            end = LocalDateTime.parse(dateEnd, dateTimeFormatter);
        }

        MealsDto mealsDto = new MealsDto(meals);
        mealsDto.setDateStart(start);
        mealsDto.setDateEnd(end);
        mealsDto.setSelectedMealIds(List.of());

        model.addAttribute("mealsDto", mealsDto);
        return "meals";
    }

    /**
     * Отображает страницу с приемами пищи с выбранными id
     *
     * @param checkedIds id приемов пищи, которые нужно показать
     * @return имя шаблона, отображающего страницу с приемами пищи
     */
    @PostMapping
    public String getUserMealsWithIds(
            Model model,
            @AuthenticationPrincipal UserDetails currentUser,
            @RequestParam(value = "checkedIds", required = false) List<Long> checkedIds
    ) {
        AppUser appUser = appUserService.findAppUserByUsername(currentUser.getUsername());

        List<Meal> meals;

        if (checkedIds == null) {
            meals = mealService.getAllByUserId(appUser.getId());
        } else {
            meals = mealService.getAllWithIdsByUserId(appUser.getId(), checkedIds);
        }

        MealsDto mealsDto = new MealsDto(meals);
        mealsDto.setSelectedMealIds(checkedIds);

        model.addAttribute("mealsDto", mealsDto);

        return "meals";
    }

    /**
     * Отображает страницу с приемами пищи пользователя
     *
     * @param id пользователя
     * @return имя шаблона, отображающего страницу с приемами пищи пользователя
     */
    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole(\"ADMIN\")")
    public String getMealsByUserId(
            Model model,
            @PathVariable Long id
    ) {
        List<Meal> meals = mealService.getAllByUserId(id);
        MealsDto mealsDto = new MealsDto(meals);
        model.addAttribute("mealsDto", mealsDto);
        return "user-meals";
    }

    /**
     * Отображает страницу создания приема пищи
     *
     * @return имя шаблона, отображающего страницу создания приема пищи
     */
    @GetMapping("/create")
    public String createMeal(Model model) {

        String formattedDateTime = LocalDateTime.now().format(dateTimeFormatter);

        MealDto mealDto = new MealDto();

        List<DishDto> dishDtos = new ArrayList<>();

        for (var i = 0; i < 4; i++) {
            DishDto dishDto = new DishDto();
            dishDto.setId(1000000L + i);

            dishDtos.add(dishDto);
        }

        DishesDto dishesDto = new DishesDto(dishDtos);

        mealDto.setDishesDto(dishesDto);
        mealDto.setDateTime(formattedDateTime);

        model.addAttribute("meal", mealDto);
        return "meal-create";
    }

    /**
     * Создает прием пищи
     *
     * @return перенаправляет на страницу с приемами пищи
     */
    @PostMapping("/create")
    public String createMeal(
            @AuthenticationPrincipal UserDetails currentUser,
            @ModelAttribute MealDto mealDto,
            @RequestParam("dishes") String dishesJson
    ) {
        AppUser appUser = appUserService.findAppUserByUsername(currentUser.getUsername());

        Meal meal = new Meal();

        meal.setDescription(mealDto.getDescription());
        meal.setType(Meal.Type.fromName(mealDto.getType()));

        LocalDateTime dateTime = LocalDateTime.parse(mealDto.getDateTime(), dateTimeFormatter);
        meal.setDateTime(dateTime);

        List<DishDto> dishDtos;

        try {
            dishDtos = mapper.readValue(dishesJson, new TypeReference<>() {
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<Dish> dishesFromDtos = getDishesFromDtos(dishDtos);

        meal.setDishes(dishesFromDtos);
        meal.setAppUser(appUser);

        mealService.createMeal(meal);

        return "redirect:/meals";
    }


    /**
     * Удаляет прием пищи
     *
     * @return перенаправляет на страницу с приемами пищи
     */
    @GetMapping("/{id}/delete")
    public String deleteMeal(
            @PathVariable Long id
    ) {
        mealService.deleteMeal(id);
        return "redirect:/meals";
    }

    /**
     * Отображает страницу редактирования приема пищи
     *
     * @return имя шаблона, отображающего страницу редактирования приема пищи
     */
    @GetMapping("/{id}/edit")
    public String editMealById(
            Model model,
            @PathVariable Long id
    ) {
        Meal existingMeal = mealService.findById(id);

        MealDto mealDto = new MealDto();
        mealDto.setId(existingMeal.getId());
        mealDto.setDescription(existingMeal.getDescription());
        mealDto.setType(existingMeal.getType().getName());

        List<DishDto> dishDtos = new ArrayList<>(existingMeal
                .getDishes()
                .stream()
                .map(this::mapDishToDishDto)
                .toList());

        for (var i = 0; i < 4; i++) {
            DishDto dishDto = new DishDto();
            dishDto.setId(1000000 * mealDto.getId() + i);

            dishDtos.add(dishDto);
        }

        DishesDto dishesDto = new DishesDto(dishDtos);
        mealDto.setDishesDto(dishesDto);

        String formattedDateTime = existingMeal.getDateTime().format(dateTimeFormatter);
        mealDto.setDateTime(formattedDateTime);

        model.addAttribute("meal", mealDto);

        return "meal-edit";
    }

    /**
     * Обновляет прием пищи

     * @return перенаправляет на страницу с приемами пищи
     */
    @PostMapping("/{id}/edit")
    public String updateMeal(
            @PathVariable long id,
            @ModelAttribute MealDto mealDto,
            @RequestParam("dishes") String dishesJson
    ) {

        List<DishDto> dishDtos;

        try {
            dishDtos = mapper.readValue(dishesJson, new TypeReference<>() {
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Meal existingMeal = mealService.findById(id);

        existingMeal.setDescription(mealDto.getDescription());
        existingMeal.setType(Meal.Type.fromName(mealDto.getType()));

        List<Dish> dishesFromDtos = getDishesFromDtos(dishDtos);

        existingMeal.setDishes(dishesFromDtos);

        LocalDateTime dateTime = LocalDateTime.parse(mealDto.getDateTime(), dateTimeFormatter);
        existingMeal.setDateTime(dateTime);

        mealService.updateMeal(existingMeal);

        return "redirect:/meals";
    }

    /**
     * Переводит объект Dish в объект DishDto
     */
    private DishDto mapDishToDishDto(Dish dish) {
        DishDto dishDto = new DishDto();

        dishDto.setId(dish.getId());
        dishDto.setName(dish.getName());
        dishDto.setDescription(dish.getDescription());
        dishDto.setGramsWeight(dish.getGramsWeight());

        dishDto.setProteinPer100g(dish.getFood().getNutritionalValue().getProteinPer100g());
        dishDto.setFatPer100g(dish.getFood().getNutritionalValue().getFatPer100g());
        dishDto.setCarbohydratePer100g(dish.getFood().getNutritionalValue().getCarbohydratePer100g());

        return dishDto;
    }

    /**
     * Возвращает список Dish из списка DishDto
     */
    private List<Dish> getDishesFromDtos(List<DishDto> dishDtos) {
        return dishDtos
                .stream()
                .map(this::dishDtoToDish)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Переводит объект DishDto в объект Dish
     */
    private Dish dishDtoToDish(DishDto dishDto) {
        Dish existingDish = dishService.findById(dishDto.getId());

        Dish dish = existingDish != null
                ? existingDish
                : new Dish();

        NutritionalValue nutritionalValue = new NutritionalValue(dishDto.getProteinPer100g(), dishDto.getCarbohydratePer100g(), dishDto.getFatPer100g());

        Food food = new Food();
        food.setNutritionalValue(nutritionalValue);

        dish.setFood(food);

        dish.setName(dishDto.getName());
        dish.setDescription(dishDto.getDescription());
        dish.setGramsWeight(dishDto.getGramsWeight());

        return dish;
    }
}
