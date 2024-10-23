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

import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    @Autowired
    public UserDetailsServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser userFromRepository = appUserRepository.findAppUserByUsername(username);

        if (userFromRepository == null) {
            throw new UsernameNotFoundException(username);
        }

        return new User(
                userFromRepository.getUsername(),
                userFromRepository.getPassword(),
                mapRolesToAuthorities(userFromRepository.getRoles())
        );
    }

    private List<? extends GrantedAuthority> mapRolesToAuthorities(Set<AppUserRole> roles) {
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
                .toList();
    }
}
