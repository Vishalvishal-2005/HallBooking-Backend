package com.example.demo.service;

import com.example.demo.model.BookingVendor;
import com.example.demo.model.User;
import com.example.demo.model.Vendor;
import com.example.demo.repository.BookingVendorRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingVendorService {

    private final BookingVendorRepository bookingVendorRepository;
    private final UserRepository userRepository;
    private final VendorRepository vendorRepository;

    public BookingVendorService(BookingVendorRepository bookingVendorRepository, 
                                UserRepository userRepository,
                                VendorRepository vendorRepository) {
        this.bookingVendorRepository = bookingVendorRepository;
        this.userRepository = userRepository;
        this.vendorRepository = vendorRepository;
    }

    public String bookVendor(String name, String email, String phone, String eventDate, 
                             String eventLocation, Long vendorId, Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Vendor> vendorOpt = vendorRepository.findById(vendorId);

        if (userOpt.isEmpty() || vendorOpt.isEmpty()) {
            return "User or Vendor not found";
        }

        BookingVendor booking = new BookingVendor();
        booking.setUser(userOpt.get());
        booking.setCustomerName(name);
        booking.setEmail(email);
        booking.setPhone(phone);
        booking.setEventDate(eventDate);
        booking.setEventLocation(eventLocation);
        booking.setVendor(vendorOpt.get());

        bookingVendorRepository.save(booking);
        return "Vendor booked successfully!";
    }

    public List<BookingVendor> getUserVendorBookings(Long userId) {
        return bookingVendorRepository.findByUser_Id(userId);
    }

    public List<BookingVendor> getAllVendorBookings() {
        return bookingVendorRepository.findAll();
    }

    public boolean deleteVendorBooking(Long id) {
        if (bookingVendorRepository.existsById(id)) {
            bookingVendorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
