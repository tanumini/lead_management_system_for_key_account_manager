package com.project.leadmanagement.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Interaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "interaction_date")
    private LocalDate interactionDate;
    private String interactionType;
    private String notes;
    @ManyToOne
    @JoinColumn(name = "lead_id")
    @JsonBackReference
    private LeadEntry leadEntry;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonBackReference("restaurant-interaction")
    private Restaurant restaurant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getInteractionDate() {
        return interactionDate;
    }

    public void setInteractionDate(LocalDate interactionDate) {
        this.interactionDate = interactionDate;
    }

    public String getInteractionType() {
        return interactionType;
    }

    public void setInteractionType(String interactionType) {
        this.interactionType = interactionType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LeadEntry getLead() {
        return leadEntry;
    }

    public void setLead(LeadEntry leadEntry) {
        this.leadEntry = leadEntry;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}
