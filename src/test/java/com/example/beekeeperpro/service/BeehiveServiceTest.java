package com.example.beekeeperpro.service;

import com.example.beekeeperpro.model.Apiary;
import com.example.beekeeperpro.model.Beehive;
import com.example.beekeeperpro.model.User;
import com.example.beekeeperpro.repository.ApiaryRepository;
import com.example.beekeeperpro.repository.BeehiveRepository;
import com.example.beekeeperpro.repository.UserRepository;
import com.example.beekeeperpro.utility.DatabaseCleaner;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BeehiveServiceTest {

    private static BeehiveService beehiveService;

    @BeforeAll
    public static void setUp() {
        DatabaseCleaner.clearDatabase();

        UserRepository userRepository = new UserRepository();
        userRepository.insertUser(new User(
                1,
                "testuser",
                "Test User",
                "test@example.com",
                LocalDate.now(),
                3
        ));

        ApiaryRepository apiaryRepository = new ApiaryRepository();
        apiaryRepository.insertApiary(new Apiary(
                0,
                1,
                "TestApiary",
                "TestLocation",
                LocalDate.now()
        ));

        beehiveService = new BeehiveService(new BeehiveRepository());
    }

    @Test
    @Order(1)
    public void testInsertBeehive() {
        Beehive beehive = new Beehive(1, 1, "JUnitHive", "TestStatus", 1);
        beehiveService.insertBeehive(beehive);
        assertTrue(true);
    }

    @Test
    @Order(2)
    public void testGetBeehivesByApiaryId() {
        List<Beehive> list = beehiveService.getBeehivesByApiaryId(1);
        assertNotNull(list, "The list should not be null");
        assertFalse(list.isEmpty(), "The list should not be empty");
    }

    @Test
    @Order(3)
    public void updateBeehive() {
        Beehive beehive = new Beehive(
                1,
                1,
                "JUnitUpdatedHive",
                "TestUpdatedStatus",
                1
        );
        beehiveService.updateBeehive(beehive);
    }

    @Test
    @Order(4)
    public void testFindBeehiveByNumber() {
        Beehive beehive = beehiveService.getBeehiveByNumber(1);
        assertNotNull(beehive, "The beehive should not be null");
        assertEquals(1, beehive.getNumber());
    }

    @Test
    @Order(5)
    public void deleteBeehive() {
        Beehive beehive = new Beehive(
                1,
                1,
                "JUnitUpdatedHive",
                "TestUpdatedStatus",
                1
        );
        beehiveService.deleteBeehive(beehive.getNumber());
    }
}
