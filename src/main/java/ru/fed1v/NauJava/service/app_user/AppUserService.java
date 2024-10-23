package ru.fed1v.NauJava.service.app_user;

import ru.fed1v.NauJava.entity.AppUser;

import java.util.List;

public interface AppUserService {

    List<AppUser> findAll();

    AppUser findById(Long id);

    List<AppUser> findAppUsersByAge(int age);

    List<AppUser> findAppUsersByName(String name);

    AppUser findAppUserByUsername(String username);
    
    List<AppUser> findAppUsersByNameAndAge(String name, int age);

    List<AppUser> findAppUsersOlderThan(int age);
    
    void addAppUser(AppUser appUser);
}
