package ru.fed1v.NauJava.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.fed1v.NauJava.entity.AppUser;
import ru.fed1v.NauJava.service.app_user.AppUserService;

/**
 * Класс, обрабатывающий запросы, относящиеся к регистрации
 */
@Controller
@RequestMapping
public class RegistrationController {

    /**
     * Сервис для работы с пользователями
     */
    private final AppUserService appUserService;

    public RegistrationController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    /**
     * Отображает страницу регистрацией
     *
     * @return имя шаблона, отображающего страницу регистрации
     */
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("appUser", new AppUser());
        return "registration";
    }

    /**
     * Добавляет пользователя
     */
    @PostMapping("/registration")
    public String addUser(@ModelAttribute AppUser appUser, Model model) {
        try {
            appUserService.addAppUser(appUser);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "registration";
        }
    }
}
