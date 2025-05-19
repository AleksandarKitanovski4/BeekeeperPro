package com.example.beekeeperpro.service;

import com.example.beekeeperpro.model.Inspection;
import com.example.beekeeperpro.repository.InspectionRepository;

import java.time.LocalDate;
import java.util.List;

public class InspectionService {

    private final InspectionRepository inspectionRepository;

    public InspectionService(InspectionRepository inspectionRepository) {
        this.inspectionRepository = inspectionRepository;
    }

    public void addInspection(Inspection inspection) {
        if (!inspectionRepository.existsByDateAndBeehiveNumber(inspection.getInspectionDate(), inspection.getHiveId())) {
            inspectionRepository.insert(inspection);
        } else {
            System.out.println("The inspection for this date and Beehive number already exists.");
        }
    }

    public List<Inspection> getInspectionsByBeehiveNumber(int beehiveNumber) {
        return inspectionRepository.findByBeehiveNumber(beehiveNumber);
    }

    public List<Inspection> getInspectionsByDate(LocalDate date) {
        return inspectionRepository.findByInspectionDate(date);
    }

    public List<Inspection> getAllInspections() {
        return inspectionRepository.findAll();
    }

    public void deleteByDate(LocalDate date) {
        inspectionRepository.deleteByInspectionDate(date);
    }
}
