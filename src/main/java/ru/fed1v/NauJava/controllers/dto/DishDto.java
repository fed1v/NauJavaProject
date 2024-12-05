package ru.fed1v.NauJava.controllers.dto;

/**
 * Класс для отображения блюда на странцие 
 */
public class DishDto {

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
     * Масса блюда в граммах
     */
    private Double gramsWeight;

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
}
