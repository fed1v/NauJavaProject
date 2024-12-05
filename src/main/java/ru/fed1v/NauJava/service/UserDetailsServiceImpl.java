package ru.fed1v.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.fed1v.NauJava.entity.AppUser;
import ru.fed1v.NauJava.entity.AppUserRole;
import ru.fed1v.NauJava.repository.app_user.AppUserRepository;

import java.io.NotActiveException;
import java.util.List;
import java.util.Set;


/**
 * Сервис для работы с аутентификацией, предоставляющий
 * информацию о пользователе
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * Репозиторий для работы с пользователями
     */
    private final AppUserRepository appUserRepository;

    @Autowired
    public UserDetailsServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    /**
     * Метод для получения информации о пользователе по его имени
     * @param username the username identifying the user whose data is required.
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser userFromRepository = appUserRepository.findAppUserByUsername(username);

        if (userFromRepository == null) {
            throw new UsernameNotFoundException(username);
        }

        if (!userFromRepository.isActive()) {
            throw new UsernameNotFoundException("User is blocked");
        }

        return new User(
                userFromRepository.getUsername(),
                userFromRepository.getPassword(),
                mapRoleToAuthority(userFromRepository.getRole())
        );
    }

    /**
     * Метод, отображающий Role в Authority
     */
    private List<? extends GrantedAuthority> mapRoleToAuthority(AppUserRole role) {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
    }
}
