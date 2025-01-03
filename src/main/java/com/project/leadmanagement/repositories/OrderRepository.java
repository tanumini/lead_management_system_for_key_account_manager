package com.project.leadmanagement.repositories;



import com.project.leadmanagement.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Custom query methods if needed

    // Find orders by Lead ID
    List<Order> findByLeadEntry_Id(Long leadId);

    // Find orders by Lead ID and within a date range
    List<Order> findByLeadEntry_IdAndOrderDateBetween(Long leadId, LocalDate startDate, LocalDate endDate);

    // Find all orders on a specific date
    List<Order> findByOrderDate(LocalDate orderDate);
}
