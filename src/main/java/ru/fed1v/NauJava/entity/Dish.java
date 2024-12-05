package ru.fed1v.NauJava.entity;

import jakarta.persistence.*;

import java.util.Objects;


/**
 * Класс, представляющий блюдо. Относится к конкретному приему пищи.
 * Содержит продукт, его вес и прочее описание.
 */
@Entity
public class Dish {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    /**
     * Название блюда
     */
    private String name;


    /**
     * Описание блюда
     */
    private String description;


    /**
     * Вес блюда в граммах
     */
    private Double gramsWeight;

    /**
     * Продукт, из которого сделано блюдо
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Food food;

    /**
     * Прием пищи, к которому относится блюдо
     */
    @ManyToOne
    @JoinColumn(name = "meal_id")
    private Meal meal;
    
    public Dish(String name, String description, Double gramsWeight, Food food) {
        this.name = name;
        this.description = description;
        this.gramsWeight = gramsWeight;
        this.food = food;
    }

    public Dish() {
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

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Objects.equals(id, dish.id) 
                && Objects.equals(name, dish.name)
                && Objects.equals(description, dish.description)
                && Objects.equals(gramsWeight, dish.gramsWeight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, gramsWeight);
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
