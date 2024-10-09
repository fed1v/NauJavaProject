package ru.fed1v.NauJava.entity;

import java.util.Objects;

public class Food {
    private Long id;
    
    private String name;
    private String description;
    private Double price;
    
    private NutritionalValue nutritionalValue;

    public Food(Long id, String name, String description, Double price, NutritionalValue nutritionalValue) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
        return Objects.equals(id, food.id) && Objects.equals(name, food.name) && Objects.equals(description, food.description) && Objects.equals(price, food.price) && Objects.equals(nutritionalValue, food.nutritionalValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, nutritionalValue);
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", nutritionalValue=" + nutritionalValue +
                '}';
    }
}
