package ru.fed1v.NauJava.controllers.dto;

import ru.fed1v.NauJava.entity.Meal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

/**
 * Класс для отображения списка приемов пищи и их фильтрации
 * по промежутку дат и выбору конкретных приемов пищи
 */
public class MealsDto {

    /**
     * Список приемов пищи
     */
    private List<Meal> meals;

    /**
     * Дата, начиная с которой будут показываться приемы пищи
     */
    private LocalDateTime dateStart;

    /**
     * Дата, начиная до которой будут показываться приемы пищи
     */
    private LocalDateTime dateEnd;

    /**
     * Уникальные идентификаторы приемов пищи, которые необходимо отобразить
     */
    private List<Long> selectedMealIds;

    public MealsDto(List<Meal> meals) {
        this.meals = meals;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public List<Long> getSelectedMealIds() {
        return selectedMealIds;
    }

    public void setSelectedMealIds(List<Long> selectedMealIds) {
        this.selectedMealIds = selectedMealIds;
    }

    /**
     * @return Общее количество белка в приеме пищи
     */
    public double getTotalProteinGrams() {
        return getTotalValue(Meal::getTotalProteinGrams);
    }

    /**
     * @return Общее количество жира в приеме пищи
     */
    public double getTotalFatGrams() {
        return getTotalValue(Meal::getTotalFatGrams);
    }

    /**
     * @return Общее количество углеводов в приеме пищи
     */
    public double getTotalCarbohydrate() {
        return getTotalValue(Meal::getTotalCarbohydrateGrams);
    }

    /**
     * @return Общее количество килокалорий в приеме пищи
     */
    public double getTotalKcal() {
        return getTotalValue(Meal::getTotalKcal);
    }

    /**
     * Вспомогательный метод для подсчета суммы выбранных значений
     */
    private double getTotalValue(Function<Meal, Double> valueSelector) {
        double totalValue = 0;

        for (Meal meal : meals) {
            totalValue += valueSelector.apply(meal);
        }

        return Math.round(totalValue);
    }
}
