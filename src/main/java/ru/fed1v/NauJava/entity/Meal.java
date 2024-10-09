package ru.fed1v.NauJava.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Meal {
    private Long id;

    private MealType type;
    private List<Dish> dishes;
    private User user;
    private LocalDateTime dateTime;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MealType getType() {
        return type;
    }

    public void setType(MealType type) {
        this.type = type;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return Objects.equals(id, meal.id) && type == meal.type && Objects.equals(dishes, meal.dishes) && Objects.equals(user, meal.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, dishes, user);
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", type=" + type +
                ", dishes=" + dishes +
                ", user=" + user +
                '}';
    }
}

enum MealType {
    BREAKFAST,
    LUNCH,
    DINNER
}