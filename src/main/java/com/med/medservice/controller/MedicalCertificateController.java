package com.med.medservice.controller;

import com.med.medservice.domain.Certificates;
import com.med.medservice.service.CertificateServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/certificate")
public class MedicalCertificateController {

    @Autowired
    private CertificateServiceImpl certificateService;

    @GetMapping("/{id}")
    public ResponseEntity<Certificates> getCertificateById(@PathVariable UUID id) {
        Certificates certificate = certificateService.getCertificateById(id);
        if (id == null) {
            return new ResponseEntity<>(BAD_REQUEST);
        } else if (certificate == null) {
            return new ResponseEntity<>(NOT_FOUND);
        } else if (!certificate.getIsAvailable())
            return  new ResponseEntity<>(FORBIDDEN);

        return ResponseEntity.accepted().body(certificate);
    }

    @GetMapping
    public ResponseEntity<List<Certificates>> getAllCertificates() {
        List<Certificates> certificatesList = certificateService.getAllCertificatesList();
        return ResponseEntity.accepted().body(certificatesList);
    }

    @PostMapping
    public ResponseEntity<Certificates> createCertificate(Certificates certificate) {
        Certificates certificateObject = certificateService.createCertificateObject(certificate);

        if (certificateObject.getId() == null && certificateObject.getDiagnose() == null) {
            return ResponseEntity.badRequest().body(certificateObject);
        }

        certificateService.saveDataIntoDB(certificateObject);

        return ResponseEntity.accepted().body(certificateObject);
    }

    @PutMapping("/id")
    public void updateCertificateInfo(UUID id) {

    }

    @DeleteMapping("/id")
    public void deleteCertificateFromDB() {

    }


}
