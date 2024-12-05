package ru.fed1v.NauJava.controllers.dto;

import java.util.List;

/**
 * Класс для отображения списка блюд на странице
 */
public class DishesDto {

    /**
     * Список отображаемых блюд
     */
    private List<DishDto> dishDtos;

    public DishesDto(List<DishDto> dishDtos) {
        this.dishDtos = dishDtos;
    }

    public DishesDto() {
    }

    public List<DishDto> getDishDtos() {
        return dishDtos;
    }

    public void setDishDtos(List<DishDto> dishDtos) {
        this.dishDtos = dishDtos;
    }
}
