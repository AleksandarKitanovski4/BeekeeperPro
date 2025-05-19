package com.example.beekeeperpro.controller;

import com.example.beekeeperpro.model.Inspection;
import com.example.beekeeperpro.repository.InspectionRepository;
import com.example.beekeeperpro.service.InspectionService;

import java.time.LocalDate;
import java.util.List;

public class InspectionController {

    private final InspectionService service;

    public InspectionController() {
        this.service = new InspectionService(new InspectionRepository());
    }

    public void addInspection(Inspection inspection) {
        service.addInspection(inspection);
    }

    public void listInspectionsByBeehiveNumber(int beehiveNumber) {
        List<Inspection> inspections = service.getInspectionsByBeehiveNumber(beehiveNumber);
        if (inspections.isEmpty()) {
            System.out.println("No inspections found");
            return;
        }
        System.out.println(Inspection.header());
        for (Inspection inspection : inspections) {
            System.out.println(inspection);
        }
    }

    public void listInspectionsByDate(LocalDate date) {
        List<Inspection> inspections = service.getInspectionsByDate(date);
        if (inspections.isEmpty()) {
            System.out.println("No inspections found");
            return;
        }
        System.out.println(Inspection.header());
        for (Inspection inspection : inspections) {
            System.out.println(inspection);
        }
    }

    public void listAllInspections() {
        List<Inspection> inspections = service.getAllInspections();
        if (inspections.isEmpty()) {
            System.out.println("No inspections found");
            return;
        }
        System.out.println(Inspection.header());
        for (Inspection inspection : inspections) {
            System.out.println(inspection);
        }
    }

    public void deleteInspectionsByDate(LocalDate date) {
        service.deleteByDate(date);
        System.out.println("Inspection for date: " + date + " has been deleted (if existed).");
    }
}
