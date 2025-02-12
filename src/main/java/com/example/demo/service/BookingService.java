package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Bookings;
import com.example.demo.model.User;
import com.example.demo.repository.BookingsRepository;
import com.example.demo.repository.UserRepository;
@Service
public class BookingService {

    @Autowired
    private BookingsRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;  // Add this to retrieve the user by userId

    public void bookVenueForUser(Long userId, Long venueId, Bookings booking) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        
        booking.setUser(user);  // Set the User object directly
        booking.setVenueId(venueId);
        bookingRepository.save(booking);
    }

    public List<Bookings> getUserBookings(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public List<Bookings> getAllBookings() {
        return bookingRepository.findAll();
    }
    public boolean deleteBookingById(Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
}
