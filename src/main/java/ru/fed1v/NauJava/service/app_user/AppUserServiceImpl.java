package ru.fed1v.NauJava.service.app_user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fed1v.NauJava.entity.AppUser;
import ru.fed1v.NauJava.repository.app_user.AppUserRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AppUserServiceImpl implements AppUserService {
    
    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
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
    public List<AppUser> findAppUsersByNameAndAge(String name, int age) {
        return appUserRepository
                .findAppUsersByNameAndAge(name, age);
    }

    @Override
    public List<AppUser> findAppUsersOlderThan(int age) {
        return appUserRepository
                .findAppUsersByAgeGreaterThan(age);
    }
}
