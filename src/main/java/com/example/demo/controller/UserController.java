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
@CrossOrigin(origins = "https://venuetrack.netlify.app") // ✅ Allow frontend access
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
     * This method takes a {@link User} object containing the user's email and password,
     * checks the credentials against the stored user data, and if valid, generates a JWT token
     * for the user. The method returns a response entity containing the token and user information
     * if the sign-in is successful, or an unauthorized status if the credentials are invalid.
     *
     * @param user the {@link User} object containing the email and password of the user attempting to sign in
     * @return a {@link ResponseEntity} containing either:
     *         - A JSON object with the generated token and user information if sign-in is successful,
     *         - An HTTP status of 401 (Unauthorized) with a message indicating invalid credentials if sign-in fails.
     *
     * @throws IllegalArgumentException if the provided user object is null or contains invalid data.
     * @throws UserNotFoundException if no user is found with the provided email.
     * @throws AuthenticationException if there is an error during authentication process.
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
    /**
     * Creates a new user in the system.
     *
     * This method takes a {@link User} object as input, checks if the email already exists in the system,
     * and if not, hashes the user's password and saves the user to the database.
     * It returns a response indicating the result of the operation.
     *
     * @param user the {@link User} object containing the user's details, including email and password.
     * @return a {@link ResponseEntity} containing a message and the HTTP status code.
     *         - If the email already exists, it returns a message "Email already exists" with HTTP status 409 (CONFLICT).
     *         - If the signup is successful, it returns a message "Signup successful" with HTTP status 201 (CREATED).
     *
     * @throws IllegalArgumentException if the provided user object is null or contains invalid data.
     * @throws Exception if there is an unexpected error during user creation.
     */
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
