package ru.fed1v.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.fed1v.NauJava.entity.AppUser;

public interface UserRepository extends CrudRepository<AppUser, Long> {
    
}
