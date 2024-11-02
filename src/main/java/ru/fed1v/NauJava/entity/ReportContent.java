package ru.fed1v.NauJava.entity;

import java.util.List;

public class ReportContent {
    private Long usersCount;
    private List<Food> foodList;
    private Long timeToCountUsers;
    private Long timeToGetFoods;
    private Long totalTime;
    
    public ReportContent() {
    }

    public Long getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(Long usersCount) {
        this.usersCount = usersCount;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public Long getTimeToCountUsers() {
        return timeToCountUsers;
    }

    public void setTimeToCountUsers(Long timeToCountUsers) {
        this.timeToCountUsers = timeToCountUsers;
    }

    public Long getTimeToGetFoods() {
        return timeToGetFoods;
    }

    public void setTimeToGetFoods(Long timeToGetFoods) {
        this.timeToGetFoods = timeToGetFoods;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }
}
