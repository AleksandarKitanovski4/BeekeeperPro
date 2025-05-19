package com.example.beekeeperpro.controller;

import com.example.beekeeperpro.model.Apiary;
import com.example.beekeeperpro.service.ApiaryService;

public class ApiaryController {

    private ApiaryService apiaryService;

    public ApiaryController(ApiaryService apiaryService) {
        this.apiaryService = apiaryService;
    }

    public void addApiary(Apiary apiary) {
        apiaryService.insertApiary(apiary);
    }

    public void searchApiaryByName(String name) {
        apiaryService.searchApiariesByName(name);
    }

    public void updateApiary(Apiary apiary) {
        try {
            apiaryService.updateApiary(apiary);
            System.out.println("Apiary updated successfully");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteApiary(String apiaryName) {
        try {
            apiaryService.deleteApiary(apiaryName);
            System.out.println("Apiary deleted successfully");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
