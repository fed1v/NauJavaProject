package ru.fed1v.NauJava.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.fed1v.NauJava.entity.AppUser;
import ru.fed1v.NauJava.service.app_user.AppUserService;

import java.util.List;

/**
 * Класс, обрабатывающий запросы, относящиеся к пользователям
 */
@Controller
@RequestMapping("/users")
public class AppUserController {

    /**
     * Сервис для работы с пользователями
     */
    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    /**
     * Метод, ответственный за отображение пользователей
     *
     * @param name         имя пользователя
     * @param age          точный возраст пользователя
     * @param olderThanAge минимальный возраст пользователя
     * @return имя шаблона, отображающего пользователей
     */
    @GetMapping
    public String getAppUsersByNameAndAge(
            Model model,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "age", required = false) Integer age,
            @RequestParam(value = "olderThan", required = false) Integer olderThanAge
    ) {
        List<AppUser> users = appUserService.findAppUsersByParams(name, age, olderThanAge);
        model.addAttribute("users", users);
        return "users";
    }

    /**
     * Отображает пользователя
     *
     * @param id id пользователя, которого нужно показать
     * @return имя шаблона, отображающего пользователя
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole(\"ADMIN\")")
    public String getUser(
            Model model,
            @PathVariable Long id
    ) {
        AppUser user = appUserService.findById(id);
        model.addAttribute("user", user);
        return "user";
    }


    /**
     * Удаляет пользователя
     *
     * @param id id пользователя, которого нужно удалить
     * @return перенаправляет на страницу с пользователями
     */
    @PostMapping("/{id}/delete")
    @PreAuthorize("hasRole(\"ADMIN\")")
    public String deleteUser(
            @PathVariable Long id
    ) {
        appUserService.deleteById(id);
        return "redirect:/users";
    }

    /**
     * Заблокирует пользователя
     *
     * @param id id пользователя, которого нужно заблокировать
     * @return перенаправляет на страницу с пользователями
     */
    @PostMapping("/{id}/ban")
    @PreAuthorize("hasRole(\"ADMIN\")")
    public String banUser(
            @PathVariable Long id
    ) {
        appUserService.banById(id);
        return "redirect:/users";
    }

    /**
     * Разблокирует пользователя
     *
     * @param id id пользователя, которого нужно разблокировать
     * @return перенаправляет на страницу с пользователями
     */
    @PostMapping("/{id}/unban")
    @PreAuthorize("hasRole(\"ADMIN\")")
    public String unbanUser(
            @PathVariable Long id
    ) {
        appUserService.unbanById(id);
        return "redirect:/users";
    }

    /**
     * Делает пользователя админом
     *
     * @param id id пользователя, которого нужно сделать админом
     * @return перенаправляет на страницу с пользователями
     */
    @PostMapping("/{id}/makeAdmin")
    @PreAuthorize("hasRole(\"ADMIN\")")
    public String makeAdmin(
            @PathVariable Long id
    ) {
        appUserService.makeAdminById(id);
        return "redirect:/users";
    }
}
