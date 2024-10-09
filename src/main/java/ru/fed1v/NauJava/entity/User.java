package ru.fed1v.NauJava.entity;

import java.util.List;
import java.util.Objects;

public class User {
    
    private Long id;
    
    private String name;
    private Integer age;
    private Gender gender;
    
    private List<Meal> meals;

    public User(Long id, String name, Integer age, Gender gender, List<Meal> meals) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.meals = meals;
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

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(age, user.age) && gender == user.gender && Objects.equals(meals, user.meals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, gender, meals);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", meals=" + meals +
                '}';
    }
}

enum Gender{
    MALE,
    FEMALE
}