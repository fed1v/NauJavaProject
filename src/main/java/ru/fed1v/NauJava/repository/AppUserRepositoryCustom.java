package ru.fed1v.NauJava.repository;

import ru.fed1v.NauJava.entity.AppUser;

import java.util.List;

public interface AppUserRepositoryCustom {

    List<AppUser> findAppUsersByAgeGreaterThan(int age);

    List<AppUser> findAppUsersByNameAndAge(String name, int age);
}
