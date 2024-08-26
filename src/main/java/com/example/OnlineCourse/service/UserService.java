package com.example.OnlineCourse.service;

import com.example.OnlineCourse.entity.User;
import com.example.OnlineCourse.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        logger.info("Creating user: {}", user.getUsername());
        User createdUser = userRepository.save(user);
        logger.info("User created successfully: {}", createdUser.getId());
        return createdUser;
    }

    public List<User> getAllUsers() {
        logger.debug("Fetching all users");
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        logger.debug("Fetching user with ID: {}", id);
        return userRepository.findById(id);
    }

    public User updateUser(Long id, User userDetails) {
        logger.info("Updating user with ID: {}", id);
        User user = userRepository.findById(id).orElseThrow(() -> {
            logger.error("User not found with ID: {}", id);
            return new IllegalArgumentException("User not found");
        });
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setRole(userDetails.getRole());
        user.setEmail(userDetails.getEmail());
        User updatedUser = userRepository.save(user);
        logger.info("User updated successfully: {}", updatedUser.getId());
        return updatedUser;
    }

    public void deleteUser(Long id) {
        logger.info("Deleting user with ID: {}", id);
        userRepository.deleteById(id);
        logger.info("User deleted successfully: {}", id);
    }
}
