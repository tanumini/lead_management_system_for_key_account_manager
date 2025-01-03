package com.project.leadmanagement.repositories;

import com.project.leadmanagement.models.PointOfContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointOfContactRepository extends JpaRepository<PointOfContact, Long> {
}
