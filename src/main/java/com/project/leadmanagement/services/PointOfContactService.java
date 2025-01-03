package com.project.leadmanagement.services;

import com.project.leadmanagement.models.PointOfContact;
import com.project.leadmanagement.models.Restaurant;
import com.project.leadmanagement.repositories.PointOfContactRepository;
import com.project.leadmanagement.repositories.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PointOfContactService {

    @Autowired
    private PointOfContactRepository pointOfContactRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    public PointOfContact addPointOfContact(PointOfContact pointOfContact) {
        return pointOfContactRepository.save(pointOfContact);
    }

    public List<PointOfContact> getAllPOCs() {
        return pointOfContactRepository.findAll();
    }

    public Optional<PointOfContact> getPOCById(Long id) {
        return pointOfContactRepository.findById(id);
    }
    public Restaurant assignPOCToRestaurant(Long restaurantId, Long pocId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found with id: " + restaurantId));

        PointOfContact poc = pointOfContactRepository.findById(pocId)
                .orElseThrow(() -> new EntityNotFoundException("POC not found with id: " + pocId));

        poc.setRestaurant(restaurant); // Assign the restaurant to the POC
        pointOfContactRepository.save(poc); // Persist the changes

        return restaurant; // Return the updated restaurant
    }
}
