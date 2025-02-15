package com.project.leadmanagement.repositories;

import com.project.leadmanagement.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
List<Restaurant> findByKamId(Long kamId);
}
