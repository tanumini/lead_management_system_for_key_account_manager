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
    public KAM addKAM(KAM kam) {
        return kamRepository.save(kam);
    }
    public Optional<KAMInfoDTO> getKAMById(Long id) {
        return kamRepository.getKAMInfoById(id);
    }


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

    public List<Restaurant> getRestaurantsByKAM(Long kamId) {
            return restaurantRepository.findByKamId(kamId);
        }
    public KAM assignKAMToRestaurant(Long kamId, Long restaurantId) {

        KAM kam = kamRepository.findById(kamId)
                .orElseThrow(() -> new EntityNotFoundException("KAM not found with ID: " + kamId));


        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found with ID: " + restaurantId));


        restaurant.setKam(kam);


        restaurantRepository.save(restaurant);

        return kam;
    }

}
