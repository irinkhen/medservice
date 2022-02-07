package com.med.medservice.repository;

import com.med.medservice.domain.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, UUID> {
    @Override
    Specialization getById(UUID uuid);


}
