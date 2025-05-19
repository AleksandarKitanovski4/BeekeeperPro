package com.example.beekeeperpro.service;

import com.example.beekeeperpro.model.Apiary;
import com.example.beekeeperpro.model.User;
import com.example.beekeeperpro.repository.ApiaryRepository;
import com.example.beekeeperpro.repository.UserRepository;
import com.example.beekeeperpro.utility.DatabaseCleaner;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApiaryServiceTest {

    private static ApiaryService apiaryService;

    @BeforeAll
    public static void setup() {
        DatabaseCleaner.clearDatabase();
        UserRepository userRepository = new UserRepository();
        userRepository.insertUser(new User(
                1,
                "TestUser",
                "testUser",
                "test@example.com",
                LocalDate.now(),
                3
        ));
        apiaryService = new ApiaryService(new ApiaryRepository());
    }

    @Test
    @Order(1)
    public void testInsertApiary() {
        Apiary testApiary = new Apiary(0, 1, "JUnit Apiary", "Test City", LocalDate.now());
        apiaryService.insertApiary(testApiary);

        List<Apiary> apiaries = apiaryService.getAllApiaries();

        boolean exists = apiaries.stream()
                .anyMatch(a -> a.getName().equals("JUnit Apiary") && a.getLocation().equals("Test City"));

        assertTrue(exists, "Inserted apiary should exist in the database.");
    }


    @Test
    @Order(2)
    public void testGetAllApiaries() {
        List<Apiary> list = apiaryService.getAllApiaries();
        assertFalse(list.isEmpty(), "The list of apiaries should not be empty");
    }

    @Test
    @Order(3)
    public void testSearchApiariesByName() {
        List<Apiary> result = apiaryService.searchApiariesByName("JUnit Apiary");
        assertFalse(result.isEmpty(), "Search should return at least one result");
    }

    @Test
    @Order(4)
    public void testUpdateApiary() {
        Apiary updated = new Apiary(
                0,
                1,
                "JUnit Apiary",
                "Updated Location",
                LocalDate.now().minusDays(5)
        );
        apiaryService.updateApiary(updated);
    }

    @Test
    @Order(5)
    public void testDeleteApiary() {
        apiaryService.deleteApiary("JUnit Apiary");
    }
}
