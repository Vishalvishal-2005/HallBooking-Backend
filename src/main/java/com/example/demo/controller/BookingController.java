package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BookingVendor;
import com.example.demo.model.Bookings;
import com.example.demo.service.BookingService;
import com.example.demo.service.BookingVendorService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    
    @Autowired
    private BookingService bookingService;
    
    @Autowired
    private BookingVendorService bookingVendorService;
    
    
    @PostMapping("/{userId}/venues/{venueId}")
    public ResponseEntity<String> bookVenueForUser(
            @PathVariable("userId") Long userId, 
            @PathVariable("venueId") Long venueId, 
            @RequestBody Bookings booking) {
        
        bookingService.bookVenueForUser(userId, venueId, booking);
        return ResponseEntity.ok("Venue booked successfully");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Bookings>> getUserBookings(@PathVariable("userId") Long userId) {
        List<Bookings> bookings = bookingService.getUserBookings(userId);
        return ResponseEntity.ok(bookings);
    }
    

    @GetMapping("/admin/all")
    public ResponseEntity<List<Bookings>> getAllBookings() {
        List<Bookings> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }
    @DeleteMapping("/{id}")
public ResponseEntity<String> deleteBooking(@PathVariable Long id) {
    boolean deleted = bookingService.deleteBookingById(id);
    if (deleted) {
        return ResponseEntity.ok("Booking deleted successfully");
    } else {
        return ResponseEntity.status(404).body("Booking not found");
    }
}

@PostMapping("/{userId}")
public ResponseEntity<String> bookVendor(@PathVariable Long userId, @RequestBody Map<String, Object> bookingData) {
    System.out.println("Received Booking Data: " + bookingData);
    System.out.println("User ID: " + userId);

    if (userId == null) {
        return ResponseEntity.badRequest().body("User ID is missing");
    }

    try {
        String result = bookingVendorService.bookVendor(
            (String) bookingData.get("rname"),
            (String) bookingData.get("remail"),
            (String) bookingData.get("rmobile"),
            (String) bookingData.get("rdate"),
            (String) bookingData.get("rlocation"),
            ((Number) bookingData.get("vendorId")).longValue(),
            userId
        );

        return result.equals("User or Vendor not found") 
            ? ResponseEntity.badRequest().body(result) 
            : ResponseEntity.ok(result);
    } catch (Exception e) {
        e.printStackTrace(); // Print full error stack trace
        return ResponseEntity.internalServerError().body("An error occurred while processing your request");
    }
}


@GetMapping("/vendor/{userId}")
public ResponseEntity<List<BookingVendor>> getVendorBookings(@PathVariable Long userId) {
    return ResponseEntity.ok(bookingVendorService.getUserVendorBookings(userId));
}

@GetMapping("/vendor/all")
public ResponseEntity<List<BookingVendor>> getAllVendorBookings() {
    return ResponseEntity.ok(bookingVendorService.getAllVendorBookings());
}

@DeleteMapping("/vendor/{id}")
public ResponseEntity<String> deleteVendorBooking(@PathVariable Long id) {
    return bookingVendorService.deleteVendorBooking(id) 
        ? ResponseEntity.ok("Vendor booking deleted successfully") 
        : ResponseEntity.status(404).body("Vendor booking not found");
}

}
