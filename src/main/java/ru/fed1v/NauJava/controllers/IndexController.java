package ru.fed1v.NauJava.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.fed1v.NauJava.entity.AppUser;
import ru.fed1v.NauJava.service.app_user.AppUserService;

/**
 * Класс, обрабатывающий запросы, относящиеся к главной странице
 */
@Controller
@RequestMapping("/")
public class IndexController {

    /**
     * Сервис для работы с пользователями
     */
    private final AppUserService appUserService;

    @Autowired
    public IndexController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    /**
     * Отображает главную страницу
     *
     * @return имя шаблона, отображающего главную страницу
     */
    @GetMapping
    public String getIndex(
            Model model,
            @AuthenticationPrincipal UserDetails currentUser
    ) {

        AppUser appUser = appUserService.findAppUserByUsername(currentUser.getUsername());
        model.addAttribute("user", appUser);
        return "index";
    }
}
