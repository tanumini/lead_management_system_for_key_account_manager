package com.project.leadmanagement.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import  java.util.*;
import java.time.LocalDate;

@Entity
public class LeadEntry {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;  //New, Contacted, Converted

    @OneToMany(mappedBy = "leadEntry", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Order> orders;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    @JsonBackReference("restaurant-lead")
    private  Restaurant restaurant;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CallFrequency callFrequency;

    private LocalDate lastCallDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public CallFrequency getCallFrequency() {
        return callFrequency;
    }

    public void setCallFrequency(CallFrequency callFrequency) {
        this.callFrequency = callFrequency;
    }

    public LocalDate getLastCallDate() {
        return lastCallDate;
    }

    public void setLastCallDate(LocalDate lastCallDate) {
        this.lastCallDate = lastCallDate;
    }
    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}


