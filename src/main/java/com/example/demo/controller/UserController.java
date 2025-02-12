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
 * Handles user sign-in requests.
 *
 * This method processes a login request by verifying the user's credentials.
 * It checks if the user exists in the system and validates the password.
 * If the credentials are valid, it generates a JWT token for the user.
 *
 * @param user The user object containing the email and password for authentication.
 * @return A ResponseEntity containing the JWT token and user information if successful,
 *         or an unauthorized status with an error message if authentication fails.
 *
 * @throws IllegalArgumentException if the provided user object is null.
 * @throws Exception if an unexpected error occurs during the sign-in process.
 *
 * <p>
 * The method performs the following steps:
 * <ol>
 *     <li>Logs the received login request.</li>
 *     <li>Retrieves the user by email from the user service.</li>
 *     <li>If the user is not found, logs an error and returns an unauthorized response.</li>
 *     <li>Logs the found user and compares the stored password with the entered password.</li>
 *     <li>If the passwords do not match, logs an error and returns an unauthorized response.</li>
 *     <li>If authentication is successful, generates a JWT token and prepares the response body.</li>
 *     <li>Returns a successful response with the token and user information.</li>
 * </ol>
 * </p>
 */
public ResponseEntity<?> signIn(@RequestBody User user) {
    System.out.println("🔍 Received login request for email: " + user.getEmail());

    User response = userService.findByEmail(user.getEmail());
    if (response == null) {
        System.out.println("❌ User not found for email: " + user.getEmail());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    System.out.println("✅ Found user: " + response.getEmail());
    System.out.println("🔐 Stored password: " + response.getPassword());
    System.out.println("🔑 Entered password: " + user.getPassword());

    if (!passwordEncoder.matches(user.getPassword(), response.getPassword())) {
        System.out.println("❌ Password mismatch for email: " + user.getEmail());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    String token = jwtUtil.generateToken(response.getEmail());
    System.out.println("✅ Login successful! Token generated: " + token);

    Map<String, Object> responseBody = new HashMap<>();
    responseBody.put("token", token);
    responseBody.put("user", response);

    return ResponseEntity.ok(responseBody);
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
