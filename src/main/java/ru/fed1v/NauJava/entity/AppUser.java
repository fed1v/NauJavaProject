package ru.fed1v.NauJava.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


/**
 * Класс, описывающий пользователя приложения
 */
@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    /**
     * Имя пользователя
     */
    private String name;

    /**
     * Возраст пользователя
     */
    private Integer age;

    /**
     * Пол пользователя
     */
    private Gender gender;

    /**
     * Рост пользователя в сантиметрах
     */
    private Double height;

    /**
     * Вес пользователя в килограммах
     */
    private Double weight;

    /**
     * Уникальное имя пользователя
     */
    @Column(unique = true)
    private String username;

    /**
     * Пароль пользователя
     */
    @Column(nullable = false)
    private String password;

    /**
     * Имеет ли пользователь доступ к приложению
     */
    private boolean isActive = true;

    /**
     * Роль пользователя
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    private AppUserRole role;

    /**
     * Приемы пищи пользователя
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Meal> meals;

    public AppUser(String name, String username, String password, Integer age, Gender gender, AppUserRole role, List<Meal> meals) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.role = role;
        this.meals = meals;
        this.username = username;
        this.password = password;
    }

    public AppUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AppUserRole getRole() {
        return role;
    }

    public void setRole(AppUserRole role) {
        this.role = role;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser user = (AppUser) o;
        return Objects.equals(id, user.id)
                && Objects.equals(name, user.name)
                && Objects.equals(age, user.age)
                && gender == user.gender
                && Objects.equals(meals, user.meals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, gender, meals);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", meals=" + meals +
                '}';
    }

    /**
     * Пол пользователя: мужчина, женщина
     */
    public enum Gender {
        MALE("Мужчина"),
        FEMALE("Женщина");

        private final String value;

        Gender(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }
    }
}

