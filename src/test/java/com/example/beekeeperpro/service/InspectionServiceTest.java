package com.example.beekeeperpro.service;

import com.example.beekeeperpro.model.Apiary;
import com.example.beekeeperpro.model.Beehive;
import com.example.beekeeperpro.model.Inspection;
import com.example.beekeeperpro.model.User;
import com.example.beekeeperpro.repository.ApiaryRepository;
import com.example.beekeeperpro.repository.BeehiveRepository;
import com.example.beekeeperpro.repository.InspectionRepository;
import com.example.beekeeperpro.repository.UserRepository;
import com.example.beekeeperpro.utility.DatabaseCleaner;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InspectionServiceTest {

    private static InspectionService inspectionService;

    @BeforeAll
    public static void setUp() {
        DatabaseCleaner.clearDatabase();

        UserRepository userRepository = new UserRepository();
        userRepository.insertUser(new User(
                0,
                "inspector",
                "Inspector Name",
                "inspector@example.com",
                LocalDate.now(),
                5
        ));

        ApiaryRepository apiaryRepository = new ApiaryRepository();
        apiaryRepository.insertApiary(new Apiary(
                0,
                1,
                "Test Apiary",
                "Location",
                LocalDate.now()
        ));

        BeehiveRepository beehiveRepository = new BeehiveRepository();
        beehiveRepository.insertBeehive(new Beehive(
                0,
                101,
                "Langstroth",
                "Active",
                1
        ));

        inspectionService = new InspectionService(new InspectionRepository());
    }

    @Test
    @Order(1)
    public void testAddInspection() {
        Inspection inspection = new Inspection(
                1,
                1,
                LocalDate.now(),
                true,
                "Everything ok",
                "None",
                "None"
        );

        inspectionService.addInspection(inspection);
    }

    @Test
    @Order(2)
    public void testFindByBeehiveNumber() {
        List<Inspection> inspections = inspectionService.getInspectionsByBeehiveNumber(101);
        assertFalse(inspections.isEmpty());
    }

    @Test
    @Order(3)
    public void testFindByInspectionDate() {
        List<Inspection> inspections = inspectionService.getInspectionsByDate(LocalDate.now());
        assertFalse(inspections.isEmpty());
    }

    @Test
    @Order(4)
    public void testDeleteByDate() {
        inspectionService.deleteByDate(LocalDate.now());
        List<Inspection> inspections = inspectionService.getInspectionsByDate(LocalDate.now());
        assertTrue(inspections.isEmpty());
    }
}