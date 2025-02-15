package com.project.leadmanagement.services;

import com.project.leadmanagement.models.KAM;
import com.project.leadmanagement.models.LeadEntry;
import com.project.leadmanagement.models.PointOfContact;
import com.project.leadmanagement.models.Restaurant;
import com.project.leadmanagement.repositories.KAMRepository;
import com.project.leadmanagement.repositories.LeadRepository;
import com.project.leadmanagement.repositories.PointOfContactRepository;
import com.project.leadmanagement.repositories.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private PointOfContactRepository pocRepository;
    @Autowired
    private KAMRepository kamRepository;

    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Optional<Restaurant> getRestaurantById(Long id) {
        return restaurantRepository.findById(id);
    }


    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }
    public Restaurant updateLeadStatusForRestaurant(Long restaurantId, String leadStatus) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);

        if (restaurantOptional.isPresent()) {
            Restaurant restaurant = restaurantOptional.get();


            for (LeadEntry leadEntry : restaurant.getLeads()) {
                leadEntry.setStatus(leadStatus);
                leadRepository.save(leadEntry);
            }
            return restaurant;
        }
        return null;
    }
    public Restaurant addLeadToRestaurant(Long restaurantId, Long leadId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found with id: " + restaurantId));


        LeadEntry lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new EntityNotFoundException("Lead not found with id: " + leadId));


        if (lead.getRestaurant() == null || !lead.getRestaurant().equals(restaurant)) {
            lead.setRestaurant(restaurant);
            leadRepository.save(lead);
        }


        if (!restaurant.getLeads().contains(lead)) {
            restaurant.getLeads().add(lead);
            restaurantRepository.save(restaurant);
        }

        return restaurant;
    }

    public Restaurant addPOCToRestaurant(Long restaurantId, Long pocId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found with id: " + restaurantId));


        PointOfContact poc = pocRepository.findById(pocId)
                .orElseThrow(() -> new EntityNotFoundException("POC not found with id: " + pocId));


        poc.setRestaurant(restaurant);


        if (!restaurant.getPointOfContacts().contains(poc)) {
            restaurant.getPointOfContacts().add(poc);
        }


        pocRepository.save(poc);
        restaurantRepository.save(restaurant);

        return restaurant;
    }
    public List<LeadEntry> getLeadsForRestaurant(Long restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if (restaurant.isPresent()) {
            return restaurant.get().getLeads();
        }
        throw new EntityNotFoundException("Restaurant not found");
    }

    public List<PointOfContact> getPOCsForRestaurant(Long restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if (restaurant.isPresent()) {
            return restaurant.get().getPointOfContacts();
        }
        throw new EntityNotFoundException("Restaurant not found");
    }
    public Restaurant assignRestaurantToKAM(Long restaurantId, Long kamId) {
        KAM kam = kamRepository.findById(kamId)
                .orElseThrow(() -> new EntityNotFoundException("KAM not found with ID: " + kamId));

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found with ID: " + restaurantId));

        restaurant.setKam(kam);
        return restaurantRepository.save(restaurant);
    }



}
