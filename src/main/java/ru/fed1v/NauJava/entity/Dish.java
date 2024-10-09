package ru.fed1v.NauJava.entity;

import java.util.Objects;

public class Dish {
    private Long id;
    
    private String name;
    private String description;
    private Double gramsWeight;
    
    private Food food;

    public Dish(Long id, String name, String description, Double gramsWeight, Food food) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.gramsWeight = gramsWeight;
        this.food = food;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getGramsWeight() {
        return gramsWeight;
    }

    public void setGramsWeight(Double gramsWeight) {
        this.gramsWeight = gramsWeight;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Objects.equals(id, dish.id) && Objects.equals(name, dish.name) && Objects.equals(description, dish.description) && Objects.equals(gramsWeight, dish.gramsWeight) && Objects.equals(food, dish.food);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, gramsWeight, food);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", gramsWeight=" + gramsWeight +
                ", food=" + food +
                '}';
    }
}
