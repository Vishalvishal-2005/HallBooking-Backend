package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signin")
    /**
     * Signs in a user by validating their credentials and generating a JWT token.
     *
     * @param user the User object containing the email and password for authentication
     * @return a ResponseEntity containing a JSON response with the generated token and user details if authentication is successful,
     *         or an unauthorized status with an error message if authentication fails.
     *
     * @throws IllegalArgumentException if the provided user object is null or if the email or password is empty.
     * @throws AuthenticationException if the user cannot be validated due to internal errors during the authentication process.
     */
    public ResponseEntity<?> signIn(@RequestBody User user) {
        User response = userService.validateUser(user.getEmail(), user.getPassword());
        
        if (response != null) {
            String token = jwtUtil.generateToken(response.getEmail());

            // Return JSON response
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("token", token);
            responseBody.put("user", response); // Include user details

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
     * This method accepts a User object, checks if the email already exists in the system,
     * and if not, saves the user. If the email is already associated with an existing user,
     * it returns a conflict response.
     *
     * @param user the User object containing the details of the user to be created
     * @return a ResponseEntity containing a message and the appropriate HTTP status code
     *         - If the email already exists, returns a message "Email already exists"
     *           with HTTP status 409 (CONFLICT).
     *         - If the user is successfully created, returns a message "Signup successful"
     *           with HTTP status 201 (CREATED).
     *
     * @throws IllegalArgumentException if the provided user object is null or invalid.
     * @throws UserServiceException if there is an error while saving the user to the database.
     */
    public ResponseEntity<String> createUser(@RequestBody User user) {
        if (userService.findByEmail(user.getEmail()) != null) {
            return new ResponseEntity<>("Email already exists", HttpStatus.CONFLICT);
        }
        userService.saveUser(user);
        return new ResponseEntity<>("Signup successful", HttpStatus.CREATED);
    }
}
