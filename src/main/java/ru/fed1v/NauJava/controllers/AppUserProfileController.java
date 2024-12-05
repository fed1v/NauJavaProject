package ru.fed1v.NauJava.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.fed1v.NauJava.entity.AppUser;
import ru.fed1v.NauJava.service.app_user.AppUserService;

/**
 * Класс, обрабатывающий запросы, относящиеся к профилю пользователя
 */
@Controller
@RequestMapping("/profile")
public class AppUserProfileController {

    /**
     * Сервис для работы с пользователями
     */
    private final AppUserService appUserService;

    public AppUserProfileController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    /**
     * Отображает профиль пользователя
     *
     * @param currentUser текущий авторизированный пользователь
     * @return имя шаблона, отображающего профиль пользователя
     */
    @GetMapping
    public String getProfile(
            Model model,
            @AuthenticationPrincipal UserDetails currentUser
    ) {

        AppUser appUser = appUserService.findAppUserByUsername(currentUser.getUsername());
        model.addAttribute("user", appUser);
        return "profile";
    }

    /**
     * Отображает профиль нужного пользователя
     * @param id id пользователя, профиль которого нужно отобразить
     * @return имя шаблона, отображающего профиль пользователя
     */
    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole(\"ADMIN\")")
    public String getProfileById(
            Model model,
            @PathVariable Long id
    ) {
        AppUser appUser = appUserService.findById(id);
        model.addAttribute("user", appUser);
        return "user-profile";
    }

    /**
     * Обновляет информацию о пользователе
     * @param appUserModel введенная информация о пользователе
     * @return перенаправляет на главную страницу
     */
    @PostMapping
    public String updateProfile(
            @ModelAttribute AppUser appUserModel
    ) {
        AppUser appUser = appUserService.findById(appUserModel.getId());

        appUser.setName(appUserModel.getName());
        appUser.setGender(appUserModel.getGender());
        appUser.setAge(appUserModel.getAge());
        appUser.setHeight(appUserModel.getHeight());
        appUser.setWeight(appUserModel.getWeight());

        appUserService.saveUser(appUser);

        return "redirect:/";
    }
}
