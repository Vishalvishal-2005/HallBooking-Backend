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
     * Signs in a user by validating their credentials and generating a JWT token.
     *
     * This method takes a {@link User} object from the request body, retrieves the user by email,
     * and checks if the provided password matches the stored password. If the credentials are valid,
     * it generates a JWT token and returns it along with the user information in the response.
     *
     * @param user the {@link User} object containing the email and password for authentication
     * @return a {@link ResponseEntity} containing either the generated token and user information
     *         if authentication is successful, or an unauthorized status with an error message
     *         if the credentials are invalid.
     *
     * @throws IllegalArgumentException if the provided user object is null or contains invalid data.
     * @throws UserNotFoundException if no user is found with the provided email.
     * @throws AuthenticationException if there is an error during the authentication process.
     */
    public ResponseEntity<?> signIn(@RequestBody User user) {
        User response = userService.findByEmail(user.getEmail());

        if (response != null && passwordEncoder.matches(user.getPassword(), response.getPassword())) {
            String token = jwtUtil.generateToken(response.getEmail());

            // Return JSON response
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("token", token);
            responseBody.put("user", response);

            return ResponseEntity.ok(responseBody);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
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
