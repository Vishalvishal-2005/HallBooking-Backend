package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "https://venuetrack.netlify.app", allowCredentials = "true")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
@PostMapping("/signin")
/**
 * Authenticates a user based on the provided login request.
 *
 * This method retrieves a user by their email from the user repository.
 * If the user is found, it checks if the provided password matches the stored password.
 * If the authentication is successful, it generates a JWT token for the user and returns
 * an AuthResponse containing the user information and the token.
 *
 * @param loginRequest the login request containing the user's email and password
 * @return a ResponseEntity containing an AuthResponse with user information and JWT token
 * @throws RuntimeException if the user is not found or if the password is invalid
 */
public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
    User user = userRepository.findByEmail(loginRequest.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
        throw new RuntimeException("Invalid password");
    }

    String token = jwtTokenProvider.createToken(user.getEmail());
    return ResponseEntity.ok(new AuthResponse(user, token));
}





    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        if (userService.findByEmail(user.getEmail()) != null) {
            return new ResponseEntity<>("Email already exists", HttpStatus.CONFLICT);
        }
        
        // ✅ Hash password before saving user
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);

        return new ResponseEntity<>("Signup successful", HttpStatus.CREATED);
    }
}
