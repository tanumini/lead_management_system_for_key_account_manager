package com.project.leadmanagement.dto;


import com.project.leadmanagement.models.CallFrequency;
import com.project.leadmanagement.models.LeadEntry;
import com.project.leadmanagement.models.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LeadEntryDTO {
    private Long id;
    private String status;
    private Long restaurantId;
    private CallFrequency callFrequency;
    private LocalDate lastCallDate;
    private List<Long> orders;

    public LeadEntryDTO(LeadEntry leadEntry) {
        this.id = leadEntry.getId();
        this.status = leadEntry.getStatus();
        this.restaurantId = leadEntry.getRestaurant() != null ? leadEntry.getRestaurant().getId() : null;
        this.callFrequency = leadEntry.getCallFrequency();
        this.lastCallDate = leadEntry.getLastCallDate();
        this.orders = leadEntry.getOrders().stream()
                .map(Order::getId)
                .collect(Collectors.toList());
    }


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

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
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
}
