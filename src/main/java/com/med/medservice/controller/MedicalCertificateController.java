package com.med.medservice.controller;

import com.med.medservice.domain.Certificates;
import com.med.medservice.service.CertificateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/certificate")
public class MedicalCertificateController {

    @Autowired
    private CertificateService certificateService;

    @GetMapping("/{id}")
    public ResponseEntity<Certificates> getCertificateById(@PathVariable UUID id) {
        Certificates certificate = certificateService.getCertificateById(id);
        if (id == null) {
            log.info("Certificate id cannot be null");
            return new ResponseEntity<>(BAD_REQUEST);
        } else if (certificate == null) {
            log.info("Certificate with id {} not found", id);
            return new ResponseEntity<>(NOT_FOUND);
        } else if (!certificate.getIsAvailable()) {
            log.info("Certificate with id {} is unavailable", id);
            return new ResponseEntity<>(FORBIDDEN);
        }

        return ResponseEntity.accepted().body(certificate);
    }

    @GetMapping
    public ResponseEntity<List<Certificates>> getAllCertificates() {
        List<Certificates> certificatesList = certificateService.getAllCertificatesList();
        return ResponseEntity.accepted().body(certificatesList);
    }

    @PostMapping
    public ResponseEntity<Certificates> createCertificate(@RequestBody Certificates certificate) {
        Certificates certificateObject = certificateService.createCertificateObject(certificate);

        if (certificateObject.getDiagnose() == null) {
            log.info("Diagnose cannot be null");
            return ResponseEntity.badRequest().body(certificateObject);
        }

        certificateService.saveDataIntoDB(certificateObject);

        return ResponseEntity.accepted().body(certificateObject);
    }

    @PutMapping("/id")
    public ResponseEntity<Certificates> updateCertificateData(
            @PathVariable UUID id,
            @RequestBody Certificates certificate) {
        Certificates certificateTable = certificateService.updateCertificateInfo(id, certificate);
        certificateService.saveDataIntoDB(certificateTable);

        return ResponseEntity.accepted().body(certificateTable);
    }

    @DeleteMapping("/id")
    public ResponseEntity<Certificates> deleteCertificateFromDB(@PathVariable UUID id) {
        Certificates certificateTable = certificateService.deleteCertificateFromAccess(id);
        certificateService.saveDataIntoDB(certificateTable);

        if (certificateTable.getIsAvailable()) {
            log.info("User with id {} is not deleted", id);
            return ResponseEntity.badRequest().body(certificateTable);
        }
        return ResponseEntity.accepted().body(certificateTable);
    }


}
