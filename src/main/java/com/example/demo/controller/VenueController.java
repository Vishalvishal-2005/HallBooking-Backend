package com.example.demo.controller;

import com.example.demo.model.Venue;
import com.example.demo.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/venues")
public class VenueController {
    
    @Autowired
    private VenueService venueService;
    
    @PostMapping
    public ResponseEntity<Venue> createVenue(@RequestBody Venue venue) {
        return ResponseEntity.ok(venueService.saveVenue(venue));
    }
    @GetMapping
    public ResponseEntity<List<Venue>> getAllVenues() {
        return ResponseEntity.ok(venueService.getAllVenues());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venue> getVenueById(@PathVariable Long id) {
        Optional<Venue> venue = venueService.getVenueById(id);
        return venue.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVenue(@PathVariable Long id) {
        boolean deleted = venueService.deleteVenueById(id);
        if (deleted) {
            return ResponseEntity.ok("Venue deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venue not found");
        }
    }
    

}
