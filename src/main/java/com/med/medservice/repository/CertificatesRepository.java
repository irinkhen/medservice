package com.med.medservice.repository;

import com.med.medservice.domain.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CertificatesRepository extends JpaRepository<Certificate, UUID> {
    @Override
    Optional<Certificate> findById(UUID id);
}
