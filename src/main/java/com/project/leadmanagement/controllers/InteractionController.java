package com.project.leadmanagement.controllers;

import com.project.leadmanagement.dto.InteractionRequest;
import com.project.leadmanagement.models.Interaction;
import com.project.leadmanagement.services.InteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/interactions")
public class InteractionController {

    @Autowired
    private InteractionService interactionService;

    @PostMapping
    public Interaction createInteraction(@RequestBody InteractionRequest interactionRequest) {
        return interactionService.addInteraction(
                interactionRequest.toInteraction(),
                interactionRequest.getLeadId(),
                interactionRequest.getRestaurantId()
        );
    }
    @GetMapping
    public List<Interaction> getAllInteractions() {
        return interactionService.getAllInteractions();
    }

    @GetMapping("/{id}")
    public Optional<Interaction> getInteractionById(@PathVariable Long id) {
        return interactionService.getInteractionById(id);
    }

}
