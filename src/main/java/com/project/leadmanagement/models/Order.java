package com.project.leadmanagement.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate orderDate; // Date the order was placed
    private Double amount;       // Total order amount

    @ManyToOne
    @JoinColumn(name = "lead_entry_id")
    @JsonBackReference
    private LeadEntry leadEntry; // Link each order to a lead

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LeadEntry getLeadEntry() {
        return leadEntry;
    }

    public void setLeadEntry(LeadEntry leadEntry) {
        this.leadEntry = leadEntry;
    }
}
