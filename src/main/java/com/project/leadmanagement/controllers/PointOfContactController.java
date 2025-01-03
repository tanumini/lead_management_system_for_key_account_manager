package com.project.leadmanagement.controllers;

import com.project.leadmanagement.models.PointOfContact;
import com.project.leadmanagement.services.PointOfContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pocs")
public class PointOfContactController {

    @Autowired
    private PointOfContactService pointOfContactService;

    @PostMapping
    public PointOfContact addPointOfContact(@RequestBody PointOfContact pointOfContact) {
        return pointOfContactService.addPointOfContact(pointOfContact);
    }

    @GetMapping
    public List<PointOfContact> getAllPOCs() {
        return pointOfContactService.getAllPOCs();
    }

    @GetMapping("/{id}")
    public Optional<PointOfContact> getPOCById(@PathVariable Long id) {
        return pointOfContactService.getPOCById(id);
    }
}

