package com.example.beekeeperpro.controller;

import com.example.beekeeperpro.model.Apiary;
import com.example.beekeeperpro.model.Beehive;
import com.example.beekeeperpro.model.User;
import com.example.beekeeperpro.repository.ApiaryRepository;
import com.example.beekeeperpro.repository.BeehiveRepository;
import com.example.beekeeperpro.repository.UserRepository;
import com.example.beekeeperpro.service.BeehiveService;
import com.example.beekeeperpro.utility.DatabaseCleaner;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BeehiveControllerTest {

    private static BeehiveController beehiveController;

    private static final int TEST_APIARY_ID = 1;
    private static final int TEST_NUMBER = 999;

    @BeforeAll
    public static void setUp() {
        DatabaseCleaner.clearDatabase();

        UserRepository userRepository = new UserRepository();
        userRepository.insertUser(new User(
                0,
                "testuser",
                "JUnit User",
                "junit@example.com",
                LocalDate.now(),
                2
        ));

        ApiaryRepository apiaryRepository = new ApiaryRepository();
        apiaryRepository.insertApiary(new Apiary(
                0,
                1,
                "JUnit Apiary",
                "Test Location",
                LocalDate.now()
        ));

        BeehiveRepository beehiveRepository = new BeehiveRepository();
        BeehiveService beehiveService = new BeehiveService(beehiveRepository);
        beehiveController = new BeehiveController(beehiveService);
    }

    @Test
    @Order(1)
    public void testAddBeehive() {
        Beehive beehive = new Beehive(
                0,
                TEST_NUMBER,
                "TestType",
                "Active",
                TEST_APIARY_ID
        );
        beehiveController.addBeehive(beehive);
    }

    @Test
    @Order(2)
    public void testGetBeehivesByApiaryId() {
        List<Beehive> list = beehiveController.getBeehivesByApiaryId(TEST_APIARY_ID);
        assertNotNull(list);
        assertTrue(list.stream()
                .anyMatch(b -> b.getNumber() == TEST_NUMBER));
    }

    @Test
    @Order(3)
    public void testUpdateBeehive() {
        Beehive updated = new Beehive(
                0,
                TEST_NUMBER,
                "UpdatedType",
                "Inactive",
                TEST_APIARY_ID
        );
        beehiveController.updateBeehive(updated);
    }

    @Test
    @Order(4)
    public void testGetBeehiveByNumber() {
        Beehive beehive = beehiveController.getBeehive(TEST_NUMBER);
        assertNotNull(beehive);
        assertEquals(TEST_NUMBER, beehive.getNumber());
    }

    @Test
    @Order(5)
    public void testDeleteBeehive() {
        beehiveController.deleteBeehiveByNumber(TEST_NUMBER);
    }
}
