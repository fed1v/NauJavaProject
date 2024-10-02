package ru.fed1v.NauJava.entity;

import java.util.Objects;

public class NutritionalValue {
    /**
     * Количество граммов белка в 100 граммах продукта
     */
    private double proteinPer100g;

    /**
     * Количество граммов углеводов в 100 граммах продукта
     */
    private double carbohydratePer100g;

    /**
     * Количество граммов жира в 100 граммах продукта
     */
    private double fatPer100g;

    /**
     * Количество ккал в 100 граммах продукта
     */
    private double kcalPer100g;


    public NutritionalValue(double proteinPer100g, double carbohydratePer100g, double fatPer100g) {
        this.proteinPer100g = proteinPer100g;
        this.carbohydratePer100g = carbohydratePer100g;
        this.fatPer100g = fatPer100g;
    }

    public NutritionalValue(double kcalPer100g) {
        this.kcalPer100g = kcalPer100g;
    }

    public double getProteinPer100g() {
        return proteinPer100g;
    }

    public void setProteinPer100g(double proteinPer100g) {
        this.proteinPer100g = proteinPer100g;
    }

    public double getCarbohydratePer100g() {
        return carbohydratePer100g;
    }

    public void setCarbohydratePer100g(double carbohydratePer100g) {
        this.carbohydratePer100g = carbohydratePer100g;
    }

    public double getFatPer100g() {
        return fatPer100g;
    }

    public void setFatPer100g(double fatPer100g) {
        this.fatPer100g = fatPer100g;
    }

    public double getKcalPer100g() {
        return kcalPer100g;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NutritionalValue that)) return false;
        return Double.compare(proteinPer100g, that.proteinPer100g) == 0 && Double.compare(carbohydratePer100g, that.carbohydratePer100g) == 0 && Double.compare(fatPer100g, that.fatPer100g) == 0 && Double.compare(kcalPer100g, that.kcalPer100g) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(proteinPer100g, carbohydratePer100g, fatPer100g, kcalPer100g);
    }

    @Override
    public String toString() {
        return "NutritionalValue{" +
                "proteinPer100g=" + proteinPer100g +
                ", carbohydratePer100g=" + carbohydratePer100g +
                ", fatPer100g=" + fatPer100g +
                ", kcalPer100g=" + kcalPer100g +
                '}';
    }
}
