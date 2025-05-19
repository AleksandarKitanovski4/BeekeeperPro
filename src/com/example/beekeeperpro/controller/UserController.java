package com.example.beekeeperpro.controller;

import com.example.beekeeperpro.model.User;
import com.example.beekeeperpro.service.UserService;

import java.util.List;

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void addUser(User user) {
        try {
            userService.insertUser(user);
            System.out.println("User added successfully");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateUser(User user) {
        try {
            userService.updateUser(user);
            System.out.println("User updated successfully");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteUser(String username) {
        try {
            userService.deleteUser(username);
            System.out.println("User deleted successfully");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void listAllUsers() {
        List<User> users = userService.getAllUsers();
        users.forEach(System.out::println);
        System.out.println("Users found: " + users.size());
    }

    public User findUserByUsername(String username) {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println("User with username " + username + " not found");
        }
        return user;
    }
}
