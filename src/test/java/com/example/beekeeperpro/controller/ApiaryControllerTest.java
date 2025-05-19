package com.example.beekeeperpro.controller;

import com.example.beekeeperpro.model.Apiary;
import com.example.beekeeperpro.model.User;
import com.example.beekeeperpro.repository.ApiaryRepository;
import com.example.beekeeperpro.repository.UserRepository;
import com.example.beekeeperpro.service.ApiaryService;
import com.example.beekeeperpro.utility.DatabaseCleaner;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApiaryControllerTest {

    private static ApiaryController apiaryController;

    @BeforeAll
    public static void setUp() {
        DatabaseCleaner.clearDatabase();

        UserRepository userRepository = new UserRepository();
        userRepository.insertUser(new User(
                0,
                "testuser",
                "Test User",
                "test@example.com",
                LocalDate.now(),
                3
        ));

        ApiaryRepository repository = new ApiaryRepository();
        ApiaryService apiaryService = new ApiaryService(repository);
        apiaryController = new ApiaryController(apiaryService);
    }

    @Test
    @Order(1)
    public void testAddApiary() {
        Apiary apiary = new Apiary(
                0,
                1,
                "JUnit Apiary",
                "Test Location",
                LocalDate.now()
        );
        apiaryController.addApiary(apiary);
    }

    @Test
    @Order(2)
    public void testSearchApiaryByName() {
        assertDoesNotThrow(() -> apiaryController.searchApiaryByName("JUnit Apiary"));
    }

    @Test
    @Order(3)
    public void testUpdateApiary() {
        Apiary updatedApiary = new Apiary(
                0,
                1,
                "JUnit Apiary",
                "Updated Location",
                LocalDate.now()
        );
        apiaryController.updateApiary(updatedApiary);
    }

    @Test
    @Order(4)
    public void testDeleteApiary() {
        assertDoesNotThrow(() -> apiaryController.deleteApiary("JUnit Apiary"));
    }
}
