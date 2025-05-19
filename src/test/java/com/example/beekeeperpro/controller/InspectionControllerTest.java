package com.example.beekeeperpro.controller;

import com.example.beekeeperpro.model.Apiary;
import com.example.beekeeperpro.model.Beehive;
import com.example.beekeeperpro.model.Inspection;
import com.example.beekeeperpro.model.User;
import com.example.beekeeperpro.repository.ApiaryRepository;
import com.example.beekeeperpro.repository.BeehiveRepository;
import com.example.beekeeperpro.repository.UserRepository;
import com.example.beekeeperpro.utility.DatabaseCleaner;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InspectionControllerTest {

    private static InspectionController inspectionController;

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

        inspectionController = new InspectionController();
    }

    @Test
    @Order(1)
    public void testAddAndListInspections() {
        Inspection inspection = new Inspection(
                1,
                1,
                LocalDate.now(),
                true,
                "Everything from controller",
                "None",
                "None"
        );
                inspectionController.addInspection(inspection);
                inspectionController.listInspectionsByBeehiveNumber(101);
                inspectionController.listInspectionsByDate(LocalDate.now());
                inspectionController.listAllInspections();
    }

    @Test
    @Order(2)
    public void testDeleteInspectionsByDate() {
        inspectionController.deleteInspectionsByDate(LocalDate.now());
        inspectionController.listInspectionsByDate(LocalDate.now());
    }
}
