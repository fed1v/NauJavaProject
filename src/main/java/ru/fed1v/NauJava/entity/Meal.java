package ru.fed1v.NauJava.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String description;
    private Type type;
    private LocalDateTime dateTime;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "meal"
    )
    private List<Dish> dishes;

    public Meal(String description, Type type, LocalDateTime dateTime, List<Dish> dishes) {
        this.description = description;
        this.type = type;
        this.dateTime = dateTime;
        this.dishes = dishes;
    }

    public Meal() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return Objects.equals(id, meal.id)
                && Objects.equals(description, meal.description)
                && type == meal.type
                && Objects.equals(dateTime, meal.dateTime)
                && Objects.equals(dishes, meal.dishes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, type, dateTime, dishes);
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", dateTime=" + dateTime +
                ", dishes=" + dishes +
                '}';
    }

    public enum Type {
        BREAKFAST,
        LUNCH,
        DINNER
    }
}

