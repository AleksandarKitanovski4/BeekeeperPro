package com.example.beekeeperpro.controller;

import com.example.beekeeperpro.model.User;
import com.example.beekeeperpro.repository.UserRepository;
import com.example.beekeeperpro.service.UserService;
import com.example.beekeeperpro.utility.DatabaseCleaner;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {

    private static UserController userController;

    @BeforeAll
    public static void setUp() {
        DatabaseCleaner.clearDatabase();
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);
        userController = new UserController(userService);
    }

    private static final String TEST_USERNAME = "testuser";

    @Test
    @Order(1)
    public void testAddUser() {
        User user = new User(
                0,
                TEST_USERNAME,
                "Test User",
                "test@example.com",
                LocalDate.now(),
                3
        );
        userController.addUser(user);
    }

    @Test
    @Order(2)
    public void testFindUserByUsername() {
        User found = userController.findUserByUsername(TEST_USERNAME);
        assertNotNull(found, "User should be found after being added");
        assertEquals(TEST_USERNAME, found.getUserName());
    }

    @Test
    @Order(3)
    public void testListAllUsers() {
        userController.listAllUsers();
    }

    @Test
    @Order(4)
    public void testUpdateUser() {
        User user = new User(
                0,
                TEST_USERNAME,
                "Updated User",
                "updated@example.com",
                LocalDate.now(),
                5
        );
        userController.updateUser(user);
    }

    @Test
    @Order(5)
    public void testDeleteUser() {
        userController.deleteUser(TEST_USERNAME);
    }
}
