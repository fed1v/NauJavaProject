package ru.fed1v.NauJava.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.fed1v.NauJava.entity.AppUser;
import ru.fed1v.NauJava.service.app_user.AppUserService;
import ru.fed1v.NauJava.service.bmi.BmiService;

/**
 * Класс, обрабатывающий запросы, относящиеся к индексу массы тела
 */
@Controller
@RequestMapping("/bmi")
public class BmiController {

    private final AppUserService appUserService;
    private final BmiService bmiService;

    @Autowired
    public BmiController(AppUserService appUserService, BmiService bmiService) {
        this.appUserService = appUserService;
        this.bmiService = bmiService;
    }

    /**
     * Отображает страницу, на которой можно посчитать индекс массы тела
     *
     * @param currentUser текущий авторизированный пользователь
     * @return имя шаблона, отображающего страницу с индексом массы тела
     */
    @GetMapping
    public String getBmi(
            Model model,
            @AuthenticationPrincipal UserDetails currentUser
    ) {
        AppUser appUser = appUserService.findAppUserByUsername(currentUser.getUsername());
        model.addAttribute("user", appUser);
        return "bmi";
    }

    /**
     * Считает индекс массы тела
     *
     * @param user введенная информация о пользователе
     * @return имя шаблона, отображающего страницу с индексом массы тела
     */
    @PostMapping
    public String calculateBmi(
            @ModelAttribute AppUser user,
            Model model
    ) {
        double bmi = bmiService.getBmi(user.getHeight(), user.getWeight());
        
        model.addAttribute("bmi", bmi);
        model.addAttribute("user", user);

        return "bmi";
    }
}
