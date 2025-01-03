package com.project.leadmanagement.dto;

import com.project.leadmanagement.models.Order;

import java.time.LocalDate;

public class OrderDTO {

    private Long id;
    private LocalDate orderDate;
    private Double amount;

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.orderDate = order.getOrderDate();
        this.amount = order.getAmount();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
