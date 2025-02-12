package com.example.demo.repository;

import com.example.demo.model.BookingVendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingVendorRepository extends JpaRepository<BookingVendor, Long> {
    List<BookingVendor> findByUser_Id(Long userId); // Correct Mapping
}
