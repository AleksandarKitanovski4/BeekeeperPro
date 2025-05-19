package com.example.beekeeperpro.negative;

import com.example.beekeeperpro.model.Apiary;
import com.example.beekeeperpro.model.Beehive;
import com.example.beekeeperpro.model.Inspection;
import com.example.beekeeperpro.model.User;
import com.example.beekeeperpro.repository.ApiaryRepository;
import com.example.beekeeperpro.repository.BeehiveRepository;
import com.example.beekeeperpro.repository.InspectionRepository;
import com.example.beekeeperpro.repository.UserRepository;
import com.example.beekeeperpro.service.BeehiveService;
import com.example.beekeeperpro.service.InspectionService;
import com.example.beekeeperpro.utility.DatabaseCleaner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class GeneralNegativeTest {

    private static InspectionService inspectionService;
    private static BeehiveRepository beehiveRepository;
    private static BeehiveService beehiveService;
    private static ApiaryRepository apiaryRepository;
    private static UserRepository userRepository;

    @BeforeAll
    public static void setUp() {
        DatabaseCleaner.clearDatabase();

        beehiveRepository = new BeehiveRepository();
        beehiveService = new BeehiveService(beehiveRepository);
        apiaryRepository = new ApiaryRepository();
        userRepository = new UserRepository();
        inspectionService = new InspectionService(new InspectionRepository());
    }

    @Test
    public void testFindBeehiveByNonExistingNumber() {
        Beehive result = beehiveRepository.findBeehiveByNumber(9999);
        assertNull(result, "Beehive should be null for non existing number");
    }

    @Test
    public void testAddInspectionWithInvalidBeehiveId() {
        Inspection inspection = new Inspection(
                9999,
                1,
                LocalDate.now(),
                true,
                "Invalid",
                "None",
                "None"
        );
        assertDoesNotThrow(() -> inspectionService.addInspection(inspection));
    }

    @Test
    public void testInsertApiaryWithInvalidUserId() {
        Apiary apiary = new Apiary(
                0,
                9999,
                "Invalid Apiary",
                "Nowhere",
                LocalDate.now()
        );
        assertDoesNotThrow(() -> apiaryRepository.insertApiary(apiary));
    }

    @Test
    public void testInsertUserWithNullValues() {
        User user = new User(
                0,
                null,
                null,
                null,
                null,
                0
        );
        assertThrows(NullPointerException.class, () -> userRepository.insertUser(user));
    }
}
