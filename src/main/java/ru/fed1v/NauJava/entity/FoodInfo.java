package ru.fed1v.NauJava.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Objects;

/**
 * Класс, описывающий продукты, хранящиеся в справочнике
 */
@Entity
public class FoodInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Double price;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private NutritionalValue nutritionalValue;

    public FoodInfo(String name, String description, Double price, NutritionalValue nutritionalValue) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.nutritionalValue = nutritionalValue;
    }

    public FoodInfo() {
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
        FoodInfo food = (FoodInfo) o;
        return Objects.equals(id, food.id)
                && Objects.equals(name, food.name)
                && Objects.equals(description, food.description)
                && Objects.equals(price, food.price)
                && Objects.equals(nutritionalValue, food.nutritionalValue);
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
