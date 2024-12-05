package ru.fed1v.NauJava.repository.app_user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.fed1v.NauJava.entity.AppUser;

import java.util.List;

/**
 * Репозиторий для работы с пользователями
 */
@RepositoryRestResource(path = "app_users")
public interface AppUserRepository extends CrudRepository<AppUser, Long> {

    /**
     * Возвращает пользователей с именем name
     */
    List<AppUser> findAppUsersByName(String name);

    /**
     * Возвращает пользователя с уникальным именем username
     */
    AppUser findAppUserByUsername(String username);

    /**
     * Возвращает пользователей с возрастом age
     */
    List<AppUser> findAppUsersByAge(int age);

    /**
     * Возвращает пользователей старше возраста age
     */
    List<AppUser> findAppUsersByAgeGreaterThan(int age);

    /**
     * Возвращает пользователей с именем name и возрастом age
     */
    @Query("select u from AppUser u where u.name = ?1 and u.age=?2")
    List<AppUser> findAppUsersByNameAndAge(String name, int age);
}
