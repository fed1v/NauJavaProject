package ru.fed1v.NauJava.service.app_user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fed1v.NauJava.entity.AppUser;
import ru.fed1v.NauJava.entity.AppUserRole;
import ru.fed1v.NauJava.entity.Meal;
import ru.fed1v.NauJava.repository.app_user.AppUserRepository;
import ru.fed1v.NauJava.repository.app_user_role.AppUserRoleRepository;
import ru.fed1v.NauJava.repository.meal.MealRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Реализация сервиса для работы с пользователями
 */
@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final AppUserRoleRepository appUserRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final MealRepository mealRepository;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository, AppUserRoleRepository appUserRoleRepository, PasswordEncoder passwordEncoder, MealRepository mealRepository) {
        this.appUserRepository = appUserRepository;
        this.appUserRoleRepository = appUserRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.mealRepository = mealRepository;
    }


    @Override
    public List<AppUser> findAll() {
        return StreamSupport
                .stream(appUserRepository.findAll().spliterator(), false)
                .sorted(Comparator.comparing(AppUser::getId))
                .collect(Collectors.toList());
    }

    @Override
    public AppUser findById(Long id) {
        return appUserRepository
                .findById(id)
                .orElse(null);
    }

    @Override
    public List<AppUser> findAppUsersByAge(int age) {
        return appUserRepository
                .findAppUsersByAge(age);
    }

    @Override
    public List<AppUser> findAppUsersByName(String name) {
        return appUserRepository
                .findAppUsersByName(name);
    }

    @Override
    public AppUser findAppUserByUsername(String username) {
        return appUserRepository
                .findAppUserByUsername(username);
    }

    @Override
    public List<AppUser> findAppUsersByParams(String name, Integer age, Integer olderThanAge) {
        if (olderThanAge != null) {
            return findAppUsersOlderThan(olderThanAge);

        } else if (name == null && age == null) {
            return findAll();

        } else if (age != null && name == null) {
            return findAppUsersByAge(age);

        } else if (name != null && age == null) {
            return findAppUsersByName(name);
        }

        return new ArrayList<>();
    }

    @Override
    public List<AppUser> findAppUsersOlderThan(int age) {
        return appUserRepository
                .findAppUsersByAgeGreaterThan(age);
    }

    @Override
    @Transactional
    public void addAppUser(AppUser appUser) {

        if (appUserRoleRepository.findByRole("USER").isEmpty()) {
            appUserRoleRepository.save(new AppUserRole("USER"));
        }

        if (appUserRoleRepository.findByRole("ADMIN").isEmpty()) {
            appUserRoleRepository.save(new AppUserRole("ADMIN"));
        }

        AppUserRole roleUser = appUserRoleRepository.findByRole("USER").get();
        appUser.setRole(roleUser);

        String encodedPassword = passwordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);
    }

    @Override
    public void saveUser(AppUser appUser) {
        appUserRepository.save(appUser);
    }

    @Override
    public void deleteById(Long id) {
        Optional<AppUser> user = appUserRepository.findById(id);

        if (user.isEmpty()) {
            return;
        }

        List<Meal> userMeals = mealRepository.findMealsByAppUserId(user.get().getId());

        mealRepository.deleteAll(userMeals);
        appUserRepository.deleteById(id);
    }

    @Override
    public void banById(Long id) {
        Optional<AppUser> user = appUserRepository.findById(id);

        if (user.isEmpty()) {
            return;
        }

        user.get().setActive(false);
        appUserRepository.save(user.get());
    }

    @Override
    public void unbanById(Long id) {
        Optional<AppUser> user = appUserRepository.findById(id);

        if (user.isEmpty()) {
            return;
        }

        user.get().setActive(true);
        appUserRepository.save(user.get());
    }

    @Override
    public void makeAdminById(Long id) {
        Optional<AppUser> user = appUserRepository.findById(id);

        if (user.isEmpty()) {
            return;
        }

        AppUserRole roleAdmin = appUserRoleRepository.findByRole("ADMIN").get();
        user.get().setRole(roleAdmin);

        appUserRepository.save(user.get());
    }
}
