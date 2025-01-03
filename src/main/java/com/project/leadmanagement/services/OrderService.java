package com.project.leadmanagement.services;

import com.project.leadmanagement.dto.CreateOrderRequest;
import com.project.leadmanagement.models.LeadEntry;
import com.project.leadmanagement.models.Order;
import com.project.leadmanagement.repositories.LeadRepository;
import com.project.leadmanagement.repositories.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private LeadRepository leadRepository;

    public Order createOrder(CreateOrderRequest orderRequest) {
        // Fetch the lead
        Optional<LeadEntry> leadOptional = leadRepository.findById(orderRequest.getLeadId());
        if (leadOptional.isEmpty()) {
            throw new EntityNotFoundException("Lead not found for ID: " + orderRequest.getLeadId());
        }

        LeadEntry leadEntry = leadOptional.get();

        // Create and save the order
        Order order = new Order();
        order.setAmount(orderRequest.getOrderAmount());
        order.setOrderDate(orderRequest.getOrderDate());
        order.setLeadEntry(leadEntry); // Link order to the lead

        return orderRepository.save(order);
    }
    public List<Order> getOrdersByLead(Long leadId) {
        return orderRepository.findByLeadEntry_Id(leadId);
    }

    public List<Order> getOrdersForDateRange(Long leadId, LocalDate startDate, LocalDate endDate) {
        return orderRepository.findByLeadEntry_IdAndOrderDateBetween(leadId, startDate, endDate);
    }

    public List<Order> getOrdersByDate(LocalDate orderDate) {
        return orderRepository.findByOrderDate(orderDate);
    }
}

