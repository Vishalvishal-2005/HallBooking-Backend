package com.example.demo.repository;

import com.example.demo.model.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingsRepository extends JpaRepository<Bookings, Long> {
    List<Bookings> findByUserId(Long userId);
    
    // Fetch all bookings with user details
    List<Bookings> findAll();
}
