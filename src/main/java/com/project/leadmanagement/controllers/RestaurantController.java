package com.project.leadmanagement.controllers;

import com.project.leadmanagement.models.LeadEntry;
import com.project.leadmanagement.models.Order;
import com.project.leadmanagement.models.PointOfContact;
import com.project.leadmanagement.models.Restaurant;
import com.project.leadmanagement.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.addRestaurant(restaurant);
    }

    @GetMapping("/{id}")
    public Optional<Restaurant> getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @PutMapping("/{id}")
    public Restaurant updateRestaurantStatus(@PathVariable Long id, @RequestParam String leadStatus) {
        return restaurantService.updateLeadStatusForRestaurant(id, leadStatus);
    }
    @PutMapping("/{restaurantId}/leads")
    public Restaurant addLeadToRestaurant(@PathVariable Long restaurantId, @RequestBody Map<String, Long> requestBody) {
        Long leadId = requestBody.get("leadId");
        return restaurantService.addLeadToRestaurant(restaurantId, leadId);
    }
    @PutMapping("/{restaurantId}/pocs")
    public Restaurant addPOCToRestaurant(@PathVariable Long restaurantId, @RequestBody Map<String, Long> requestBody) {
        Long pocId = requestBody.get("pocId");
        return restaurantService.addPOCToRestaurant(restaurantId, pocId);
    }
    @GetMapping("/{restaurantId}/leads")
    public List<LeadEntry> getLeadsForRestaurant(@PathVariable Long restaurantId) {
        return restaurantService.getLeadsForRestaurant(restaurantId);
    }

    @GetMapping("/{restaurantId}/pocs")
    public List<PointOfContact> getPOCsForRestaurant(@PathVariable Long restaurantId) {
        return restaurantService.getPOCsForRestaurant(restaurantId);
    }
    @GetMapping("/{restaurantId}/orders")
    public List<Order> getOrdersForRestaurant(@PathVariable Long restaurantId) {
        // Fetch all leads for the restaurant
        List<LeadEntry> leads = restaurantService.getLeadsForRestaurant(restaurantId);

        // Create a list to store all orders
        List<Order> orders = new ArrayList<>();

        // Loop through all the leads and get their orders
        for (LeadEntry lead : leads) {
            orders.addAll(lead.getOrders()); // Assuming 'getOrders()' returns a list of orders
        }

        return orders;
    }

}
