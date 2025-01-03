package com.project.leadmanagement.services;

import com.project.leadmanagement.dto.InteractionRequest;
import com.project.leadmanagement.models.Interaction;
import com.project.leadmanagement.models.LeadEntry;
import com.project.leadmanagement.models.Restaurant;
import com.project.leadmanagement.repositories.InteractionRepository;
import com.project.leadmanagement.repositories.LeadRepository;
import com.project.leadmanagement.repositories.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class InteractionService {

    @Autowired
    private InteractionRepository interactionRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private LeadRepository leadRepository;
    public Interaction addInteraction(Interaction interaction, Long leadId, Long restaurantId) {

            // Fetch the associated Lead
            LeadEntry lead = leadRepository.findById(leadId)
                    .orElseThrow(() -> new EntityNotFoundException("Lead not found for ID: " + leadId));

            // Fetch the associated Restaurant
            Restaurant restaurant = restaurantRepository.findById(restaurantId)
                    .orElseThrow(() -> new EntityNotFoundException("Restaurant not found for ID: " + restaurantId));

            // Set the relationships
            interaction.setLead(lead);
            interaction.setRestaurant(restaurant);
            // Save the Interaction
            return interactionRepository.save(interaction);
    }

    public List<Interaction> getAllInteractions() {
        return interactionRepository.findAll();
    }

    public Optional<Interaction> getInteractionById(Long id) {
        return interactionRepository.findById(id);
    }

    public List<Interaction> getTodayCalls() {
        // Placeholder logic to fetch today's calls (to be implemented)
        return interactionRepository.findTodayCalls();  // You can implement a query for today's calls here
    }
    private LocalDate convertToLocalTime(LocalDate utcDate, String timezone) {
        if (timezone != null) {
            return utcDate.atStartOfDay(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.of(timezone)).toLocalDate();
        }
        return utcDate;
    }
}
