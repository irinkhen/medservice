package com.med.medservice.repository;

import com.med.medservice.domain.Doctors;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoctorRepository extends CrudRepository<Doctors, UUID> {
    @Override
    Optional<Doctors> findById(UUID id);

    Optional<Doctors> findByEmail(String email);
}
