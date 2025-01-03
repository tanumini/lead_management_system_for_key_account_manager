package com.project.leadmanagement.services;

import com.project.leadmanagement.models.*;
import com.project.leadmanagement.repositories.InteractionRepository;
import com.project.leadmanagement.repositories.KAMRepository;
import com.project.leadmanagement.repositories.LeadRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LeadService {

    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private KAMRepository kamRepository;
    @Autowired
    private InteractionRepository interactionRepository;

    public LeadEntry addLead(LeadEntry leadEntry) {
        return leadRepository.save(leadEntry);
    }

    public Optional<LeadEntry> getLeadById(Long id) {
        return leadRepository.findByIdWithOrders(id);
    }

    public List<LeadEntry> getAllLeads() {
        return leadRepository.findAll();
    }

    public LeadEntry updateLeadStatus(Long id, String status) {
        Optional<LeadEntry> lead = leadRepository.findById(id);
        if (lead.isPresent()) {
            lead.get().setStatus(status);
            return leadRepository.save(lead.get());
        }
        return null;
    }

    public LeadEntry assignLeadToKAM(Long leadId, Long kamId) {
        KAM kam = kamRepository.findById(kamId)
                .orElseThrow(() -> new EntityNotFoundException("KAM not found with ID: " + kamId));

        LeadEntry lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new EntityNotFoundException("Lead not found with ID: " + leadId));

        return leadRepository.save(lead);
    }

    public List<LeadEntry> getLeadsRequiringCallToday() {
        LocalDate today = LocalDate.now();
        return leadRepository.findAll().stream()
                .filter(lead -> isLeadDueForCall(lead, today))
                .collect(Collectors.toList());
    }

    private boolean isLeadDueForCall(LeadEntry lead, LocalDate today) {
        if (lead.getCallFrequency() == null || lead.getLastCallDate() == null) {
            return false;
        }

        switch (lead.getCallFrequency()) {
            case DAILY:
                return lead.getLastCallDate().isBefore(today);
            case WEEKLY:
                return lead.getLastCallDate().plusWeeks(1).isBefore(today);
            case MONTHLY:
                return lead.getLastCallDate().plusMonths(1).isBefore(today);
            default:
                return false;
        }
    }

    public void updateLastCallDate(Long leadId) {
        LeadEntry lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new EntityNotFoundException("Lead not found"));
        lead.setLastCallDate(LocalDate.now());
        leadRepository.save(lead);
    }

    public List<LeadEntry> getWellPerformingLeads() {
        return leadRepository.findAll().stream()
                .filter(lead -> isPerformingWell(lead))
                .collect(Collectors.toList());
    }

    private boolean isPerformingWell(LeadEntry lead) {
        double totalRevenue = lead.getOrders().stream()
                .mapToDouble(Order::getAmount)
                .sum();


        return lead.getOrders().size() > 5 && totalRevenue > 1000;
    }


    public List<LeadEntry> getUnderperformingLeads() {
        return leadRepository.findAll().stream()
                .filter(this::isUnderperforming)
                .collect(Collectors.toList());
    }

    private boolean isUnderperforming(LeadEntry lead) {

        double totalRevenue = lead.getOrders().stream()
                .mapToDouble(Order::getAmount)
                .sum();

        return lead.getOrders().size() < 2 || totalRevenue < 500;
    }

    public LocalDate getLastCallDate(Long leadId) {
        Optional<LeadEntry> leadOptional = leadRepository.findById(leadId);
        if (leadOptional.isEmpty()) {
            throw new EntityNotFoundException("Lead not found for ID: " + leadId);
        }

        LeadEntry lead = leadOptional.get();
        return lead.getLastCallDate();
    }

    public List<Interaction> getCallsForLead(Long leadId) {
        LeadEntry leadEntry = leadRepository.findById(leadId)
                .orElseThrow(() -> new EntityNotFoundException("Lead not found with ID: " + leadId));


        return interactionRepository.findByLeadEntry(leadEntry);
    }

    public LeadEntry updateCallFrequency(Long id, String cf) {
        Optional<LeadEntry> lead = leadRepository.findById(id);
        if (lead.isPresent()) {
            lead.get().setCallFrequency(CallFrequency.valueOf(cf));
            return leadRepository.save(lead.get());
        }
        return null;
    }
}