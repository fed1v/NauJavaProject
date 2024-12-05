package ru.fed1v.NauJava.service.app_user;

import ru.fed1v.NauJava.entity.AppUser;

import java.util.List;

/**
 * Сервис для работы с пользователями
 */
public interface AppUserService {

    /**
     * Получить список всех пользователей
     */
    List<AppUser> findAll();

    /**
     * Получить пользователя по id
     */
    AppUser findById(Long id);

    /**
     * Получить список всех пользователей с возрастом age
     */
    List<AppUser> findAppUsersByAge(int age);

    /**
     * Получить список всех пользователей с именем name
     */
    List<AppUser> findAppUsersByName(String name);

    /**
     * Получить пользователя с уникальным именем username
     */
    AppUser findAppUserByUsername(String username);

    /**
     * Получить список пользователей по параметрам
     */
    List<AppUser> findAppUsersByParams(String name, Integer age, Integer olderThanAge);
    
    /**
     * Получить список всех пользователей старше age
     */
    List<AppUser> findAppUsersOlderThan(int age);

    /**
     * Добавить пользователя
     */
    void addAppUser(AppUser appUser);

    /**
     * Сохранить пользователя
     */
    void saveUser(AppUser appUser);

    /**
     * Удалить пользователя
     */
    void deleteById(Long id);

    /**
     * Заблокировать пользователя
     */
    void banById(Long id);

    /**
     * Разблокировать пользователя
     */
    void unbanById(Long id);

    /**
     * Сделать пользователя админом
     */
    void makeAdminById(Long id);
}
