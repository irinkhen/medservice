package com.med.medservice.service;

import com.med.medservice.domain.Certificates;
import com.med.medservice.repository.CertificatesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
@Slf4j
public class CertificateServiceImpl {

    private final CertificatesRepository certificatesRepository;

    @Transactional
    public void saveDataIntoDB(Certificates certificateTableObject) {
        certificatesRepository.save(certificateTableObject);
        log.info("Data about certificate {} is saved", certificateTableObject.getId());
    }

    public Certificates getCertificateById(UUID id) {
        Certificates certificate;
        if (certificatesRepository.findById(id).isPresent()) {
            certificate = certificatesRepository.findById(id).get();
            log.info("Get certificate with id {}", id);
            return certificate;
        }
        else {
            log.info("Certificate with id {} not found!", id);
            return null;
        }
    }

    public Certificates createCertificateObject(Certificates certificate) {
        Certificates certificateTable = new Certificates();
        final UUID id = UUID.randomUUID();

        certificateTable.setId(id);
        certificateTable.setTherapist(certificate.getTherapist());
        certificateTable.setPatient(certificate.getPatient());
        certificateTable.setVisionTest(certificate.getVisionTest());
        certificateTable.setPsychoanalyse(certificate.getPsychoanalyse());
        certificateTable.setElectrocardiogram(certificate.getElectrocardiogram());
        certificateTable.setDiagnose(certificate.getDiagnose());
        certificateTable.setCreated(certificate.getCreated());
        certificateTable.setChanged(certificate.getChanged());
        certificateTable.setIsAvailable(certificate.getIsAvailable());

        certificatesRepository.save(certificateTable);

        return certificateTable;
    }

    public List<Certificates> getAllCertificatesList() {
        return certificatesRepository.findAll();
    }

    public Certificates updateCertificateInfo(UUID id, Certificates certificate) {
        Certificates certificateTable;

        if (certificatesRepository.findById(id).isPresent()) {
            certificateTable = certificatesRepository.findById(id).get();

            certificateTable.setTherapist(certificate.getTherapist());
            certificateTable.setPatient(certificate.getPatient());
            certificateTable.setVisionTest(certificate.getVisionTest());
            certificateTable.setPsychoanalyse(certificate.getPsychoanalyse());
            certificateTable.setElectrocardiogram(certificate.getElectrocardiogram());
            certificateTable.setDiagnose(certificate.getDiagnose());
            certificateTable.setCreated(certificate.getCreated());
            certificateTable.setChanged(certificate.getChanged());
            certificateTable.setIsAvailable(certificate.getIsAvailable());

            return certificateTable;
        } else return null;
    }
}
