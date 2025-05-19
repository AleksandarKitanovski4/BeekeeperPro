package com.example.beekeeperpro.service;

import com.example.beekeeperpro.model.User;
import com.example.beekeeperpro.repository.UserRepository;
import com.example.beekeeperpro.utility.DatabaseCleaner;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {

    private static UserService userService;
    private static final String TEST_USERNAME = "testuser";

    @BeforeAll
    public static void setUp() {
        DatabaseCleaner.clearDatabase();
        UserRepository userRepository = new UserRepository();
        userService = new UserService(userRepository);
    }

    @Test
    @Order(1)
    public void testInsertUser() {
        User user = new User(
                0,
                TEST_USERNAME,
                "Service Test User",
                "service@example.com",
                LocalDate.now(),
                2
        );
        userService.insertUser(user);
        System.out.println("Inserted user: " + user);
    }

    @Test
    @Order(2)
    public void testGetUserByUsername() {
        User user = userService.getUserByUsername(TEST_USERNAME);
        assertNotNull(user);
        assertEquals(TEST_USERNAME, user.getUserName());
    }

    @Test
    @Order(3)
    public void testGetAllUsers() {
        List<User> users = userService.getAllUsers();
        assertFalse(users.isEmpty());
    }

    @Test
    @Order(4)
    public void testUpdateUser() {
        User updatedUser = new User(
                0,
                TEST_USERNAME,
                "Updated Name",
                "updated@example.com",
                LocalDate.now(),
                4
        );
        userService.updateUser(updatedUser);
    }

    @Test
    @Order(5)
    public void testDeleteUser() {
        userService.deleteUser(TEST_USERNAME);
    }
}
