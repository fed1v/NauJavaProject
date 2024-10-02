package ru.fed1v.NauJava.entity;

import java.util.Objects;

public class Food {
    private Long id;
    
    private String name;
    private NutritionalValue nutritionalValue;

    public Food(Long id, String name, NutritionalValue nutritionalValue) {
        this.id = id;
        this.name = name;
        this.nutritionalValue = nutritionalValue;
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

    public NutritionalValue getNutritionalValue() {
        return nutritionalValue;
    }

    public void setNutritionalValue(NutritionalValue nutritionalValue) {
        this.nutritionalValue = nutritionalValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Objects.equals(id, food.id) && Objects.equals(name, food.name) && Objects.equals(nutritionalValue, food.nutritionalValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, nutritionalValue);
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nutritionalValue=" + nutritionalValue +
                '}';
    }
}
