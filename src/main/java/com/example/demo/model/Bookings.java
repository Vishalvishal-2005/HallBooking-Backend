package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    private Long venueId;
    private String name;
    private int totalMembers;
    private String arrival;
    private String departure;
    private String event;
    private String phone;
    private String date;
    private String venueName;
    private Double venuePrice;
    private String venueImage;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // Remove `user_id` field and keep the relationship

    // Getters and Setters
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getVenueId() {
        return venueId;
    }
    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getTotalMembers() {
        return totalMembers;
    }
    public void setTotalMembers(int totalMembers) {
        this.totalMembers = totalMembers;
    }
    public String getArrival() {
        return arrival;
    }
    public void setArrival(String arrival) {
        this.arrival = arrival;
    }
    public String getDeparture() {
        return departure;
    }
    public void setDeparture(String departure) {
        this.departure = departure;
    }
    public String getEvent() {
        return event;
    }
    public void setEvent(String event) {
        this.event = event;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getVenueName() {
        return venueName;
    }
    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }
    public Double getVenuePrice() {
        return venuePrice;
    }
    public void setVenuePrice(Double venuePrice) {
        this.venuePrice = venuePrice;
    }
    public String getVenueImage() {
        return venueImage;
    }
    public void setVenueImage(String venueImage) {
        this.venueImage = venueImage;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
