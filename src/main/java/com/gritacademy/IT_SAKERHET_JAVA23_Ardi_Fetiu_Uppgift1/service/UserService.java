package com.gritacademy.IT_SAKERHET_JAVA23_Ardi_Fetiu_Uppgift1.service;

import com.gritacademy.IT_SAKERHET_JAVA23_Ardi_Fetiu_Uppgift1.model.User;
import com.gritacademy.IT_SAKERHET_JAVA23_Ardi_Fetiu_Uppgift1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(String username, String password, String email) { // Change the name here
        // Check if the user already exists
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username already exists.");
        }

        // Create a new User object
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(password)); // Hash the password
        user.setCreatedAt(new Timestamp(System.currentTimeMillis())); // Set creation timestamp

        // Save the user to the repository
        return userRepository.save(user);
    }

    // Retrieve a user by username
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Delete a user by username
    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }
}
