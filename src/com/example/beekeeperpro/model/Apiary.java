package com.example.beekeeperpro.model;

import java.time.LocalDate;

public class Apiary {

    private int id;
    private int userId;
    private String name;
    private String location;
    private LocalDate startDate;

    public Apiary(int id, int userId, String name, String location, LocalDate startDate) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.location = location;
        this.startDate = startDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return String.format("%-5d | %-10d | %-20s | %-20s | %-20s", id, userId, name, location, startDate);
    }
}
