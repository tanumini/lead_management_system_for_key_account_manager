package com.project.leadmanagement.services;



import com.project.leadmanagement.dto.KAMInfoDTO;
import com.project.leadmanagement.models.KAM;
import com.project.leadmanagement.models.LeadEntry;
import com.project.leadmanagement.models.Restaurant;
import com.project.leadmanagement.repositories.KAMRepository;
import com.project.leadmanagement.repositories.LeadRepository;
import com.project.leadmanagement.repositories.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KAMService {

    @Autowired
    private KAMRepository kamRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private LeadRepository leadRepository;
    // Add a new KAM
    public KAM addKAM(KAM kam) {
        return kamRepository.save(kam);
    }

     //Get KAM by ID
    public Optional<KAMInfoDTO> getKAMById(Long id) {
        return kamRepository.getKAMInfoById(id);
    }


//    public Optional<KAMInfoDTO> getKAMById(Long id) {
//        Optional<KAM> kam = kamRepository.findById(id);
//        if (kam.isPresent()) {
//            KAM kamEntity = kam.get();
//
//            // Get associated LeadEntry IDs for the KAM
//            List<Long> leadEntryIds = new ArrayList<>();
//            for (LeadEntry lead : leadRepository.findByKam(kamEntity)) {
//                leadEntryIds.add(lead.getId());
//            }
//
//            // Get associated Restaurant IDs for the KAM
//            List<Long> restaurantIds = new ArrayList<>();
//            for (Restaurant restaurant : restaurantRepository.findByKamId(id)) {
//                restaurantIds.add(restaurant.getId());
//            }
//
//            // Construct and return KAMInfoDTO
//            KAMInfoDTO kamInfoDTO = new KAMInfoDTO(
//                    kamEntity.getId(),
//                    kamEntity.getName(),
//                    kamEntity.getEmail(),
//                    leadEntryIds,
//                    restaurantIds
//            );
//
//            return Optional.of(kamInfoDTO);
//        }
//        return Optional.empty(); // Return empty if KAM not found
//    }
    // Get all KAMs
    public List<KAM> getAllKAMs() {
        return kamRepository.findAll();
    }

    public KAM updateKAM(Long id, KAM kamDetails) {
        Optional<KAM> existingKAM = kamRepository.findById(id);
        if (existingKAM.isPresent()) {
            KAM kam = existingKAM.get();
            kam.setName(kamDetails.getName());
            kam.setEmail(kamDetails.getEmail());
            return kamRepository.save(kam);
        }
        throw new EntityNotFoundException("KAM not found with ID " + id);
    }
    public void deleteKAM(Long id) {
        Optional<KAM> kam = kamRepository.findById(id);
        if (kam.isPresent()) {
            kamRepository.delete(kam.get());
        } else {
            throw new EntityNotFoundException("KAM not found with ID " + id);
        }
    }
//    public List<LeadEntry> getLeadsByKAM(Long kamId) {
//        Optional<KAM> kam = kamRepository.findById(kamId);
//        if (kam.isPresent()) {
//            return kam.get().getLeads();
//        }
//        throw new EntityNotFoundException("KAM not found with ID " + kamId);
//    }


    // Get all restaurants for a KAM
    public List<Restaurant> getRestaurantsByKAM(Long kamId) {
            return restaurantRepository.findByKamId(kamId);
        }
    public KAM assignKAMToRestaurant(Long kamId, Long restaurantId) {
        // Fetch the KAM entity
        KAM kam = kamRepository.findById(kamId)
                .orElseThrow(() -> new EntityNotFoundException("KAM not found with ID: " + kamId));

        // Fetch the Restaurant entity
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found with ID: " + restaurantId));

        // Assign the KAM to the restaurant
        restaurant.setKam(kam);

        // Save the updated restaurant
        restaurantRepository.save(restaurant);

        return kam;
    }

}
