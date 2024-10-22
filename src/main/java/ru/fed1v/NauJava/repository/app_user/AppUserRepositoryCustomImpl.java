package ru.fed1v.NauJava.repository.app_user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.fed1v.NauJava.entity.AppUser;

import java.util.List;

@Repository
public class AppUserRepositoryCustomImpl implements AppUserRepositoryCustom {

    private final EntityManager entityManager;

    @Autowired
    public AppUserRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<AppUser> findAppUsersByAgeGreaterThan(int age) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AppUser> criteriaQuery = builder.createQuery(AppUser.class);

        Root<AppUser> root = criteriaQuery.from(AppUser.class);
        criteriaQuery.select(root).where(builder.gt(root.get("age"), age));
        
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<AppUser> findAppUsersByNameAndAge(String name, int age) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AppUser> criteriaQuery = builder.createQuery(AppUser.class);

        Root<AppUser> root = criteriaQuery.from(AppUser.class);

        Predicate predicateForName = builder.equal(root.get("name"), name);
        Predicate predicateForAge = builder.equal(root.get("age"), age);
        Predicate bothPredicates = builder.and(predicateForName, predicateForAge);
        
        criteriaQuery.select(root).where(bothPredicates);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
