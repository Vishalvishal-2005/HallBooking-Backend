package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.model.User;
import com.example.demo.model.Venue;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
        @Autowired
        private VenueService venueService;

        @Autowired
        private PasswordEncoder passwordEncoder; // Injecting Password Encoder
    
        public User saveUser(User user) {
            // Hash the password before saving
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User validateUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }
    public void bookVenueForUser(Long userId, Long venueId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            Optional<Venue> venueOpt = venueService.getVenueById(venueId);
            if (venueOpt.isPresent()) {
                Venue venue = venueOpt.get();
                user.getVenues().add(venue);
                userRepository.save(user);
            } else {
                throw new RuntimeException("Venue not found");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public List<Venue> getUserBookings(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getVenues();
    }

}
