package ru.fed1v.NauJava.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * Класс, описывающий прием пищи. Каждый прием пищи относится
 * к конкретному пользователю
 */
@Entity
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    /**
     * Описание приема пищи
     */
    private String description;

    /**
     * Тип приема пищи (завтрак, обед, ужин)
     */
    private Type type;

    /**
     * Дата и время приема пищи
     */
    private LocalDateTime dateTime;


    /**
     * Блюда, входящие в состав приема пищи
     */
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "meal"
    )
    private List<Dish> dishes;

    /**
     * Пользователь, к которому относится прием пищи
     */
    @ManyToOne
    private AppUser appUser;

    public Meal(String description, Type type, LocalDateTime dateTime, List<Dish> dishes) {
        this.description = description;
        this.type = type;
        this.dateTime = dateTime;
        this.dishes = dishes;
    }

    public Meal() {

    }

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    /**
     * Вычисление суммарного количества белка во всех блюдах
     * @return количество граммов белка, входящего в прием пищи
     */
    public double getTotalProteinGrams() {
        return getTotalValue(
                nutritionalValue -> nutritionalValue.getProteinPer100g() / 100.0
        );
    }

    /**
     * Вычисление суммарного количества жира во всех блюдах
     * @return количество граммов жира, входящего в прием пищи
     */
    public double getTotalFatGrams() {
        return getTotalValue(
                nutritionalValue -> nutritionalValue.getFatPer100g() / 100.0
        );
    }

    /**
     * Вычисление суммарного количества углеводов во всех блюдах
     * @return количество граммов углеводов, входящих в прием пищи
     */
    public double getTotalCarbohydrateGrams() {
        return getTotalValue(
                nutritionalValue -> nutritionalValue.getCarbohydratePer100g() / 100.0
        );
    }

    /**
     * Вычисление суммарного количества килокалорий во всех блюдах
     * @return количество килокалорий, входящих в прием пищи
     */
    public double getTotalKcal() {
        return getTotalValue(
                nutritionalValue -> nutritionalValue.getKcalPer100g() / 100.0
        );
    }

    /**
     * Функция для вычисления величины, содержащейся в приеме пищи
     * 
     * @param valuePerGrammSelector функция, переводящая пищевую ценность в количество нужной величины,
     *                              содержащейся в одном грамме
     * @return количество граммов величины, содержащейся в приеме пищи
     */
    private double getTotalValue(Function<NutritionalValue, Double> valuePerGrammSelector) {
        double totalGrams = 0.0;

        for (Dish dish : dishes) {
            double valuePerGramm = valuePerGrammSelector.apply(dish.getFood().getNutritionalValue());
            totalGrams += valuePerGramm * dish.getGramsWeight();
        }

        return totalGrams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return Objects.equals(id, meal.id)
                && Objects.equals(description, meal.description)
                && type == meal.type
                && Objects.equals(dateTime, meal.dateTime)
                && Objects.equals(dishes, meal.dishes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, type, dateTime, dishes);
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", dateTime=" + dateTime +
                ", dishes=" + dishes +
                '}';
    }

    /**
     * Тип приема пищи: завтрак, обед, ужин
     */
    public enum Type {
        BREAKFAST("Завтрак"),
        LUNCH("Обед"),
        DINNER("Ужин");

        private final String name;

        Type(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        /**
         * Функция для получения типа приема пищи из его строкового представления
         * @param name строковое представление типа
         * @return тип
         */
        public static Type fromName(String name) {
            for (Type type : Type.values()) {
                if (type.getName().equals(name)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Нет такого значения: " + name);
        }
    }
}

