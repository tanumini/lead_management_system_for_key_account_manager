package com.project.leadmanagement.repositories;

import com.project.leadmanagement.models.LeadEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LeadRepository extends JpaRepository<LeadEntry, Long> {
    @Query("SELECT l FROM LeadEntry l JOIN FETCH l.orders WHERE l.id = :id")
    Optional<LeadEntry> findByIdWithOrders(@Param("id") Long id);

}
