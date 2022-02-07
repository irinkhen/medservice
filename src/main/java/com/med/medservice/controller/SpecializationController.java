package com.med.medservice.controller;

import com.med.medservice.domain.Specialization;
import com.med.medservice.repository.SpecializationRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/specialization")
@AllArgsConstructor
public class SpecializationController {

    private final SpecializationRepository specializationRepository;

    @GetMapping
    public Set<Specialization> getSpecializationList() {
        return new HashSet<Specialization>(specializationRepository.findAll());
    }
}
