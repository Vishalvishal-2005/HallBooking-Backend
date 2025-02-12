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
 * This method processes a login request by validating the provided user credentials.
 * It checks if the email and password are provided, retrieves the user by email,
 * and verifies the password. If successful, it generates a JWT token for the user.
 *
 * @param user The user object containing the email and password for authentication.
 * @return A ResponseEntity containing the response body with a token and user information
 *         if the login is successful, or an error message if the login fails.
 *
 * @throws IllegalArgumentException if the user object is null.
 * @throws AuthenticationException if the user is not found or if the password does not match.
 *
 * <p>
 * Possible responses:
 * <ul>
 *     <li>HTTP 200 OK - Login successful, returns a token and user information.</li>
 *     <li>HTTP 400 BAD REQUEST - If email or password is null.</li>
 *     <li>HTTP 401 UNAUTHORIZED - If the user is not found or if the password is incorrect.</li>
 * </ul>
 * </p>
 */
public ResponseEntity<?> signIn(@RequestBody User user) {
    System.out.println("🔍 Received login request: " + user);

    if (user.getEmail() == null || user.getPassword() == null) {
        System.out.println("❌ Email or Password is null!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email and password are required");
    }

    User response = userService.findByEmail(user.getEmail());
    if (response == null) {
        System.out.println("❌ User not found for email: " + user.getEmail());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

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
