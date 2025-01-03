package com.project.leadmanagement.controllers;

import com.project.leadmanagement.dto.LeadEntryDTO;
import com.project.leadmanagement.dto.OrderDTO;
import com.project.leadmanagement.models.Interaction;
import com.project.leadmanagement.models.KAM;
import com.project.leadmanagement.models.LeadEntry;
import com.project.leadmanagement.models.Restaurant;
import com.project.leadmanagement.repositories.KAMRepository;
import com.project.leadmanagement.repositories.RestaurantRepository;
import com.project.leadmanagement.services.LeadService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    @Autowired
    private LeadService leadService;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    KAMRepository kamRepository;
    @PostMapping
    public LeadEntry addLead(@RequestBody LeadEntry leadEntry) {

        // Fetch the Restaurant entity using the restaurantId from the request
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(leadEntry.getRestaurant().getId());
        if (restaurantOptional.isEmpty()) {
            throw new EntityNotFoundException("Restaurant not found for ID: " + leadEntry.getRestaurant().getId());
        }
        Restaurant restaurant = restaurantOptional.get();
        leadEntry.setRestaurant(restaurant);

        // Save the LeadEntry (ensure to save it with the correct associations)
        return leadService.addLead(leadEntry);
    }
    @GetMapping("/{leadId}")
    public LeadEntryDTO getLeadById(@PathVariable Long leadId) {
        Optional<LeadEntry> leadEntry = leadService.getLeadById(leadId);
        //return Optional.ofNullable(leadEntry).map(LeadEntryDTO::new);
        LeadEntry entry = leadEntry.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lead not found"));
       return new LeadEntryDTO(entry);
    }


    @GetMapping("/{leadId}/orders")
    public List<OrderDTO> getOrdersByLeadId(@PathVariable Long leadId) {
        Optional<LeadEntry> leadEntryOptional = leadService.getLeadById(leadId);
        LeadEntry leadEntry = leadEntryOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lead not found"));

        // Map orders to OrderDTO and return the list
        return leadEntry.getOrders().stream()
                .map(OrderDTO::new)  // Convert each Order to OrderDTO
                .collect(Collectors.toList());
    }


    @GetMapping
    public List<LeadEntry> getAllLeads() {
        return leadService.getAllLeads();
    }

    @PutMapping("/{id}")
    public LeadEntry updateLeadStatus(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        String status = payload.get("status");
        return leadService.updateLeadStatus(id, status);
    }
    @PutMapping("/{id}/call-frequency")
    public LeadEntry updateCallFrequency(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        String callFrequency = payload.get("callFrequency");
        return leadService.updateCallFrequency(id, callFrequency);
    }

    // Endpoint to get leads requiring a call today
    @GetMapping("/requiring-call-today")
    public List<LeadEntry> getLeadsRequiringCallToday() {
        return leadService.getLeadsRequiringCallToday();
    }

    // Endpoint to update the last call date
    @PutMapping("/{leadId}/update-call-date")
    public void updateLastCallDate(@PathVariable Long leadId) {
        leadService.updateLastCallDate(leadId);
    }

    // Endpoint to get well-performing leads
    @GetMapping("/well-performing")
    public List<LeadEntry> getWellPerformingLeads() {
        return leadService.getWellPerformingLeads();
    }

    // Endpoint to get underperforming leads
    @GetMapping("/underperforming")
    public List<LeadEntry> getUnderperformingLeads() {
        return leadService.getUnderperformingLeads();
    }

    // Endpoint to get the last call date for a lead
    @GetMapping("/{leadId}/last-call")
    public LocalDate getLastCallDate(@PathVariable Long leadId) {
        return leadService.getLastCallDate(leadId);
    }
    @GetMapping("/{leadId}/calls")
    public List<Interaction> getAllCallsForLead(@PathVariable Long leadId) {
        // Fetch all interactions or calls for the given lead
        List<Interaction> interactions = leadService.getCallsForLead(leadId);

        // Return the list of calls
        return interactions;
    }
}
