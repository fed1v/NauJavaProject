package ru.fed1v.NauJava.entity;

import java.util.Objects;

/**
 * Пищевая ценность продукта: белки, жиры, углеводы, калории
 */
public class NutritionalValue {

    private Long id;

    /**
     * Количество граммов белка в 100 граммах продукта
     */
    private Double proteinPer100g;

    /**
     * Количество граммов углеводов в 100 граммах продукта
     */
    private Double carbohydratePer100g;

    /**
     * Количество граммов жира в 100 граммах продукта
     */
    private Double fatPer100g;

    /**
     * Количество ккал в 100 граммах продукта
     */
    private Double kcalPer100g;

    private Food food;


    public NutritionalValue(Long id, double proteinPer100g, double carbohydratePer100g, double fatPer100g) {
        this.id = id;
        this.proteinPer100g = proteinPer100g;
        this.carbohydratePer100g = carbohydratePer100g;
        this.fatPer100g = fatPer100g;
    }

    public NutritionalValue(Long id, double kcalPer100g) {
        this.id = id;
        this.kcalPer100g = kcalPer100g;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getProteinPer100g() {
        return proteinPer100g;
    }

    public void setProteinPer100g(Double proteinPer100g) {
        this.proteinPer100g = proteinPer100g;
    }

    public Double getCarbohydratePer100g() {
        return carbohydratePer100g;
    }

    public void setCarbohydratePer100g(Double carbohydratePer100g) {
        this.carbohydratePer100g = carbohydratePer100g;
    }

    public Double getFatPer100g() {
        return fatPer100g;
    }

    public void setFatPer100g(Double fatPer100g) {
        this.fatPer100g = fatPer100g;
    }

    public Double getKcalPer100g() {
        return kcalPer100g;
    }

    public void setKcalPer100g(Double kcalPer100g) {
        this.kcalPer100g = kcalPer100g;
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
        NutritionalValue that = (NutritionalValue) o;
        return Objects.equals(id, that.id) && Objects.equals(proteinPer100g, that.proteinPer100g) && Objects.equals(carbohydratePer100g, that.carbohydratePer100g) && Objects.equals(fatPer100g, that.fatPer100g) && Objects.equals(kcalPer100g, that.kcalPer100g) && Objects.equals(food, that.food);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, proteinPer100g, carbohydratePer100g, fatPer100g, kcalPer100g, food);
    }

    @Override
    public String toString() {
        return "NutritionalValue{" +
                "id=" + id +
                ", proteinPer100g=" + proteinPer100g +
                ", carbohydratePer100g=" + carbohydratePer100g +
                ", fatPer100g=" + fatPer100g +
                ", kcalPer100g=" + kcalPer100g +
                ", food=" + food +
                '}';
    }
}
