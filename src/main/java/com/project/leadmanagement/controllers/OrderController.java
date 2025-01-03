package com.project.leadmanagement.controllers;

import com.project.leadmanagement.dto.CreateOrderRequest;
import com.project.leadmanagement.dto.OrderDTO;
import com.project.leadmanagement.models.LeadEntry;
import com.project.leadmanagement.models.Order;
import com.project.leadmanagement.services.LeadService;
import com.project.leadmanagement.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody CreateOrderRequest request) {
        return orderService.createOrder(
                request
        );
    }

    }


// DTO for the request body
