package com.project.leadmanagement.dto;

import java.util.List;

public class KAMInfoDTO {
    private Long kamId;
    private String kamName;
    private String kamEmail;
    private List<Long> leadEntryIds;
    private List<Long> restaurantIds;

    public KAMInfoDTO(Long kamId, String kamName, String kamEmail, List<Long> leadEntryIds, List<Long> restaurantIds) {
        this.kamId = kamId;
        this.kamName = kamName;
        this.kamEmail = kamEmail;
        this.leadEntryIds = leadEntryIds;
        this.restaurantIds = restaurantIds;
    }

    public Long getKamId() {
        return kamId;
    }

    public void setKamId(Long kamId) {
        this.kamId = kamId;
    }

    public String getKamName() {
        return kamName;
    }

    public void setKamName(String kamName) {
        this.kamName = kamName;
    }

    public String getKamEmail() {
        return kamEmail;
    }

    public void setKamEmail(String kamEmail) {
        this.kamEmail = kamEmail;
    }

    public List<Long> getLeadEntryIds() {
        return leadEntryIds;
    }

    public void setLeadEntryIds(List<Long> leadEntryIds) {
        this.leadEntryIds = leadEntryIds;
    }

    public List<Long> getRestaurantIds() {
        return restaurantIds;
    }

    public void setRestaurantIds(List<Long> restaurantIds) {
        this.restaurantIds = restaurantIds;
    }
}
