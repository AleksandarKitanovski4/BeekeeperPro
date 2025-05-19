package com.example.beekeeperpro.model;

import java.time.LocalDate;

public class Inspection {

    private int id;
    private int hiveId;
    private int userId;
    private LocalDate inspectionDate;
    private boolean hasQueen;
    private String notes;
    private String issues;
    private String intervention;

    public Inspection(int id, int hiveId, int userId, LocalDate inspectionDate,
                      boolean hasQueen, String notes, String issues, String intervention) {
        this.id = id;
        this.hiveId = hiveId;
        this.userId = userId;
        this.inspectionDate = inspectionDate;
        this.hasQueen = hasQueen;
        this.notes = notes;
        this.issues = issues;
        this.intervention = intervention;
    }

    public Inspection(int hiveId, int userId, LocalDate inspectionDate,
                      boolean hasQueen, String notes, String issues, String intervention) {
        this(0, hiveId, userId, inspectionDate, hasQueen, notes, issues, intervention);
    }

    public int getId() {
        return id;
    }

    public int getHiveId() {
        return hiveId;
    }

    public int getUserId() {
        return userId;
    }

    public LocalDate getInspectionDate() {
        return inspectionDate;
    }

    public boolean hasQueen() {
        return hasQueen;
    }

    public String getNotes() {
        return notes;
    }

    public String getIssues() {
        return issues;
    }

    public String getIntervention() {
        return intervention;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHiveId(int hiveId) {
        this.hiveId = hiveId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setInspectionDate(LocalDate inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public void setHasQueen(boolean hasQueen) {
        this.hasQueen = hasQueen;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setIssues(String issues) {
        this.issues = issues;
    }

    public void setIntervention(String intervention) {
        this.intervention = intervention;
    }

    @Override
    public String toString() {
        return String.format("%-5d | %-5d | %-5d | %-15s | %-10s | \n%-50s | \n%-50s | \n%-50s",
                id, userId, userId, inspectionDate, hasQueen, notes, issues, intervention );
    }

    public static String header() {
        return String.format(
                "%-5s | %-5s | %-5s | %-15s | %-10s | \n%-50s | \n%-50s | \n%-50s",
                "ID", "Hive", "User", "Date", "Queen", "Notes", "Issues", "Intervention"
        );
    }
}