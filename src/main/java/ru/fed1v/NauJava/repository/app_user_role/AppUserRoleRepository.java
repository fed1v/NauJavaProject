package ru.fed1v.NauJava.repository.app_user_role;

import org.springframework.data.repository.CrudRepository;
import ru.fed1v.NauJava.entity.AppUserRole;

import java.util.Optional;

public interface AppUserRoleRepository extends CrudRepository<AppUserRole, Long> {

    Optional<AppUserRole> findByRole(String role);
}
