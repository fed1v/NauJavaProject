package ru.fed1v.NauJava.repository.app_user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.fed1v.NauJava.entity.AppUser;

import java.util.List;

@RepositoryRestResource(path = "app_users")
public interface AppUserRepository extends CrudRepository<AppUser, Long> {

    List<AppUser> findAppUsersByName(String name);

    List<AppUser> findAppUsersByAge(int age);

    List<AppUser> findAppUsersByAgeGreaterThan(int age);

    @Query("select u from AppUser u where u.name = ?1 and u.age=?2")
    List<AppUser> findAppUsersByNameAndAge(String name, int age);
}
