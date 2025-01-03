package com.project.leadmanagement.controllers;

import com.project.leadmanagement.dto.KAMInfoDTO;
import com.project.leadmanagement.models.KAM;
import com.project.leadmanagement.models.Restaurant;
import com.project.leadmanagement.services.KAMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/kams")
public class KAMController {

    @Autowired
    private KAMService kamService;

    // Add a new KAM
    @PostMapping
    public KAM addKAM(@RequestBody KAM kam) {
        return kamService.addKAM(kam);
    }

    // Get KAM by ID
    @GetMapping("/{id}")
    public Optional<KAMInfoDTO> getKAMById(@PathVariable Long id) {
        return kamService.getKAMById(id);
    }

    // Get all KAMs
    @GetMapping
    public List<KAM> getAllKAMs() {
        return kamService.getAllKAMs();
    }
    @PutMapping("/{id}")
    public KAM updateKAM(@PathVariable Long id, @RequestBody KAM kamDetails) {
        return kamService.updateKAM(id, kamDetails);
    }
//    @GetMapping("/{kamId}/leads")
//    public List<LeadEntry> getLeadsByKAM(@PathVariable Long kamId) {
//        return kamService.getLeadsByKAM(kamId);
//    }
    @DeleteMapping("/{id}")
    public void deleteKAM(@PathVariable Long id) {
        kamService.deleteKAM(id);
    }

    // Assign a KAM to a restaurant (linking KAM to restaurant)
    @PutMapping("/{kamId}/restaurants")
    public KAM assignKAMToRestaurant(@PathVariable Long kamId,  @RequestBody Map<String, Long> requestBody ){
        Long restaurantId = requestBody.get("restaurantId");
        return kamService.assignKAMToRestaurant(kamId, restaurantId);
    }


    // Get all restaurants assigned to a specific KAM
    @GetMapping("/{kamId}/restaurants")
    public List<Restaurant> getRestaurantsByKAM(@PathVariable Long kamId) {
        return kamService.getRestaurantsByKAM(kamId);
    }


}
