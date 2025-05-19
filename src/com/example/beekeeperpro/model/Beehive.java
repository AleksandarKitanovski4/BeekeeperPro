package com.example.beekeeperpro.model;

public class Beehive {

    private int id;
    private int number;
    private String type;
    private String status;
    private int apiaryId;

    public Beehive(int id, int number, String type, String status, int apiaryId) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.status = status;
        this.apiaryId = apiaryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getApiaryId() {
        return apiaryId;
    }

    public void setApiaryId(int apiaryId) {
        this.apiaryId = apiaryId;
    }

    @Override
    public String toString() {
        return String.format("%-5d | %-5d | %-10s | %-10s | %-5d", id, number, type, status, apiaryId);
    }
}
