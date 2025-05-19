package com.example.beekeeperpro.model;

import java.time.LocalDate;

public class User {

    private int id;
    private String userName;
    private String fullName;
    private String email;
    private LocalDate registrationDate;
    private int yearsOfExperience;

    public User(int id, String userName, String fullName, String email, LocalDate registrationDate, int yearsOfExperience) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.registrationDate = registrationDate;
        this.yearsOfExperience = yearsOfExperience;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public String toString() {
        return String.format("%-5d | %-15s | %-20s | %-20s | %-10s | %d years",
                id, userName, fullName, email, registrationDate, yearsOfExperience);
    }
}
