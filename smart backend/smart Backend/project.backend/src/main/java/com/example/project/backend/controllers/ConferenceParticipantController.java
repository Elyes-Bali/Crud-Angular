package com.example.project.backend.controllers;

import com.example.project.backend.entity.ConferenceParticipation;
import com.example.project.backend.services.conferenceParticipationService.ConferenceParticipationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conference-participation")
@CrossOrigin(origins = "http://localhost:4200/")
public class ConferenceParticipantController {
    private final ConferenceParticipationService conferenceParticipationService;

    // Constructor Injection
    public ConferenceParticipantController(ConferenceParticipationService conferenceParticipationService) {
        this.conferenceParticipationService = conferenceParticipationService;
    }

    // Endpoint to get all conference participations
    @GetMapping("/all")
    public List<ConferenceParticipation> getAllConferences() {
        return conferenceParticipationService.getAllConferences();
    }

    // Endpoint to update acceptance status
    @PutMapping("/update/{id}/acceptance") // Use PATCH for partial updates
    public ResponseEntity<ConferenceParticipation> updateAcceptanceStatus(@PathVariable Long id, @RequestParam boolean accepted) {
        try {
            ConferenceParticipation updatedParticipation = conferenceParticipationService.updateAcceptanceStatus(id, accepted);
            return ResponseEntity.ok(updatedParticipation); // Return the updated conference participation
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return 404 if not found
        }
    }

}
