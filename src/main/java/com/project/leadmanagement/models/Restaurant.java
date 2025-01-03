package com.project.leadmanagement.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String contactInfo;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("restaurant-poc")
    private List<PointOfContact> pointOfContacts;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("restaurant-interaction")
    private List<Interaction> interactions;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference("restaurant-lead")
    private List<LeadEntry> leadEntries;

    @ManyToOne
    @JoinColumn(name = "kam_id", nullable = true)
    @JsonBackReference("restaurant-kam")
    private KAM kam; // A restaurant is managed by one KAM

    // Getters and Setters

    public List<LeadEntry> getLeads() {
        return leadEntries;
    }

    public void setLeads(List<LeadEntry> leadEntries) {
        this.leadEntries = leadEntries;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public List<PointOfContact> getPointOfContacts() {
        return pointOfContacts;
    }

    public void setPointOfContacts(List<PointOfContact> pointOfContacts) {
        this.pointOfContacts = pointOfContacts;
    }

    public List<Interaction> getInteractions() {
        return interactions;
    }

    public void setInteractions(List<Interaction> interactions) {
        this.interactions = interactions;
    }

    public KAM getKam() {
        return kam;
    }

    public void setKam(KAM kam) {
        this.kam = kam;
    }
}