package com.project.leadmanagement.repositories;

import com.project.leadmanagement.models.Interaction;
import com.project.leadmanagement.models.LeadEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InteractionRepository extends JpaRepository<Interaction, Long> {

    @Query(value="SELECT * FROM interaction i WHERE DATE(i.interaction_date) = CURRENT_DATE",nativeQuery = true)
    List<Interaction> findTodayCalls();

    List<Interaction> findByLeadEntry(LeadEntry leadEntry);
}
