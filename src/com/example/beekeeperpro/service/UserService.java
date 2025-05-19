package com.example.beekeeperpro.service;

import com.example.beekeeperpro.model.User;
import com.example.beekeeperpro.repository.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void ensureUserExists(String username) {
        if (!userRepository.existByUserName(username)) {
            throw new IllegalArgumentException("User with username " + username + " does not exist");
        }
    }

    public void ensureUserNotExists(String username) {
        if (userRepository.existByUserName(username)) {
            throw new IllegalArgumentException("User with username " + username + " already exists");
        }
    }

    public void insertUser(User user) {
        ensureUserNotExists(user.getUserName());
        userRepository.insertUser(user);
    }

    public void updateUser(User user) {
        ensureUserExists(user.getUserName());
        userRepository.updateUser(user);
    }

    public void deleteUser(String username) {
        ensureUserExists(username);
        userRepository.deleteUserByUserName(username);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }
}
