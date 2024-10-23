package ru.fed1v.NauJava.service.app_user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fed1v.NauJava.entity.AppUser;
import ru.fed1v.NauJava.entity.AppUserRole;
import ru.fed1v.NauJava.repository.app_user.AppUserRepository;
import ru.fed1v.NauJava.repository.app_user_role.AppUserRoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final AppUserRoleRepository appUserRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository, AppUserRoleRepository appUserRoleRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appUserRoleRepository = appUserRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List<AppUser> findAll() {
        return StreamSupport
                .stream(appUserRepository.findAll().spliterator(), false)
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
    public List<AppUser> findAppUsersByNameAndAge(String name, int age) {
        return appUserRepository
                .findAppUsersByNameAndAge(name, age);
    }

    @Override
    public List<AppUser> findAppUsersOlderThan(int age) {
        return appUserRepository
                .findAppUsersByAgeGreaterThan(age);
    }

    // TODO Спросить про то, как лучше такое реализовывать: при помощи транзакции или cascade-стратегии 
    @Override
    @Transactional
    public void addAppUser(AppUser appUser) {

        // TODO спросить про иерархию ролей
        appUser.getRoles().add(new AppUserRole("USER"));

        if (appUser.getUsername().toLowerCase().contains("admin")) {
            appUser.getRoles().add(new AppUserRole("ADMIN"));
        }

        Set<AppUserRole> rolesToSave = new HashSet<>();

        for (AppUserRole role : appUser.getRoles()) {
            AppUserRole roleToSave = appUserRoleRepository
                    .findByRole(role.getRole())
                    .orElse(role);

            rolesToSave.add(roleToSave);
            roleToSave.addUser(appUser);
        }

        appUser.setRoles(rolesToSave);

        String encodedPassword = passwordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);
    }
}
