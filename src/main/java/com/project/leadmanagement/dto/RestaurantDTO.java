package com.project.leadmanagement.dto;

import java.util.List;

public class RestaurantDTO {
    private Long id;
    private String name;
    private String address;
    private List<Long> leadIds; // Only include the necessary fields
    private List<Long> kamIds; // Only include the necessary fields

    // getters and setters
}
