package com.med.medservice.repository;

import com.med.medservice.domain.Certificates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CertificatesRepository extends JpaRepository<Certificates, UUID> {
    @Override
    Optional<Certificates> findById(UUID id);
}
