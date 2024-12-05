package ru.fed1v.NauJava.controllers.dto;

/**
 * Класс для отображения приема пищи на странице
 */
public class MealDto {

    private Long id;

    /**
     * Описание приема пищи
     */
    private String description;

    /**
     * Тип приема пищи (завтрак, обед, ужин)
     */
    private String type;

    /**
     * Дата и время приема пищи
     */
    private String dateTime;

    /**
     * Блюда, которые входят в прием пищи
     */
    private DishesDto dishesDto;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public DishesDto getDishesDto() {
        return dishesDto;
    }

    public void setDishesDto(DishesDto dishesDto) {
        this.dishesDto = dishesDto;
    }
}
