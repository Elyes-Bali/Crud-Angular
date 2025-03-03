package com.example.project.backend.services.conferenceParticipationService;

import com.example.project.backend.entity.ConferenceParticipation;
import com.example.project.backend.repositories.ConferenceParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConferenceParticipationService {

    @Autowired
    private ConferenceParticipationRepository conferenceParticipationRepository;

    // Method to get all conference participations
    public List<ConferenceParticipation> getAllConferences() {
        return conferenceParticipationRepository.findAll();
    }

    // Method to update the acceptance status of a participant
    public ConferenceParticipation updateAcceptanceStatus(Long id, boolean accepted) {
        Optional<ConferenceParticipation> conferenceParticipationOptional = conferenceParticipationRepository.findById(id);

        if (conferenceParticipationOptional.isPresent()) {
            ConferenceParticipation conferenceParticipation = conferenceParticipationOptional.get();
            conferenceParticipation.setAccepted(accepted);
            return conferenceParticipationRepository.save(conferenceParticipation); // Save updated participation
        } else {
            throw new RuntimeException("ConferenceParticipation not found with id: " + id); // Can improve exception handling
        }
    }
}
