package com.project.leadmanagement.repositories;



import com.project.leadmanagement.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByLeadEntry_Id(Long leadId);

    List<Order> findByLeadEntry_IdAndOrderDateBetween(Long leadId, LocalDate startDate, LocalDate endDate);

    List<Order> findByOrderDate(LocalDate orderDate);
}
