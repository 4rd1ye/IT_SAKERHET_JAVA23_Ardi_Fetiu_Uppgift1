package com.gritacademy.IT_SAKERHET_JAVA23_Ardi_Fetiu_Uppgift1.controller;

import com.gritacademy.IT_SAKERHET_JAVA23_Ardi_Fetiu_Uppgift1.model.User;
import com.gritacademy.IT_SAKERHET_JAVA23_Ardi_Fetiu_Uppgift1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "user";
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody com.gritacademy.IT_SAKERHET_JAVA23_Ardi_Fetiu_Uppgift1.DTO.UserDTO userDTO) {
        User createdUser = userService.registerUser(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail());
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        Optional<User> user = userService.getUserByUsername(username);
        return user.isPresent() ? ResponseEntity.ok(user.get()) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.ok("User deleted");
    }

    @GetMapping("/search/{username}")
    public ResponseEntity<?> searchUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.getUserByUsername(username);
        return user.isPresent() ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }
}
