package com.project.leadmanagement.dto;

import com.project.leadmanagement.models.Interaction;

import java.time.LocalDate;


public class InteractionRequest {
    private LocalDate interactionDate;
    private String interactionType;
    private String notes;
    private Long leadId;
    private Long restaurantId;
    // Getters and Setters

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

    public Long getLeadId() {
        return leadId;
    }

    public void setLeadId(Long leadId) {
        this.leadId = leadId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Interaction toInteraction() {
        Interaction interaction = new Interaction();
        interaction.setInteractionDate(interactionDate);
        interaction.setInteractionType(interactionType);
        interaction.setNotes(notes);
        // Convert the interaction date to UTC if the timezone is provided


        return interaction;
    }


}
