package com.example.beekeeperpro.controller;

import com.example.beekeeperpro.model.Beehive;
import com.example.beekeeperpro.service.BeehiveService;

import java.util.List;

public class BeehiveController {

    private BeehiveService beehiveService;

    public BeehiveController(BeehiveService beehiveService) {
        this.beehiveService = beehiveService;
    }

    public Beehive getBeehive(int number) {
        return beehiveService.getBeehiveByNumber(number);
    }

    public List<Beehive> getBeehivesByApiaryId(int apiaryId) {
        return beehiveService.getBeehivesByApiaryId(apiaryId);
    }

    public void addBeehive(Beehive beehive) {
        try {
            beehiveService.insertBeehive(beehive);
            System.out.println("Beehive added successfully");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateBeehive(Beehive beehive) {
        try {
            beehiveService.updateBeehive(beehive);
            System.out.println("Beehive updated successfully");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteBeehiveByNumber(int number) {
        try {
            beehiveService.deleteBeehive(number);
            System.out.println("Beehive deleted successfully");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
