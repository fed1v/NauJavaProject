package ru.fed1v.NauJava.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.fed1v.NauJava.entity.AppUser;
import ru.fed1v.NauJava.repository.AppUserRepository;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/users")
public class AppUserController {

    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @GetMapping
    public List<AppUser> getAppUsersByNameAndAge(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "age", required = false) Integer age,
            @RequestParam(value = "olderThan", required = false) Integer olderThanAge
    ) {
        if (olderThanAge != null) {
            return getUsersOlderThanAge(olderThanAge);
        }

        if (name == null && age == null) {
            return getAllUsers();
        }

        if (age != null && name == null) {
            return getUsersWithAge(age);
        }

        if (name != null && age == null) {
            return getUsersWithName(name);
        }

        return getUsersWithNameAndAge(name, age);
    }

    private List<AppUser> getUsersWithNameAndAge(String name, Integer age) {
        return appUserRepository
                .findAppUsersByNameAndAge(name, age);
    }

    private List<AppUser> getUsersWithName(String name) {
        return appUserRepository
                .findAppUsersByName(name);
    }

    private List<AppUser> getUsersWithAge(Integer age) {
        return appUserRepository
                .findAppUsersByAge(age);
    }

    private List<AppUser> getAllUsers() {
        return StreamSupport
                .stream(appUserRepository.findAll().spliterator(), false)
                .toList();
    }

    private List<AppUser> getUsersOlderThanAge(Integer olderThanAge) {
        return appUserRepository
                .findAppUsersByAgeGreaterThan(olderThanAge);
    }
}
