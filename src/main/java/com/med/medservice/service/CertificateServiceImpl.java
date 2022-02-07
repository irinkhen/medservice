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
public class CertificateServiceImpl implements  CertificateService {

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
        setCertificateObject(certificateTable, certificate);

        return certificateTable;
    }

    public List<Certificates> getAllCertificatesList() {
        return certificatesRepository.findAll();
    }

    public Certificates updateCertificateInfo(UUID id, Certificates certificate) {
        Certificates certificateTable;

        if (certificatesRepository.findById(id).isPresent()) {
            certificateTable = certificatesRepository.findById(id).get();

            setCertificateObject(certificateTable, certificate);

            return certificateTable;
        } else return null;
    }

    public Certificates deleteCertificateFromAccess(UUID id) {
        Certificates certificateTable;

        if (certificatesRepository.findById(id).isPresent()) {
            certificateTable = certificatesRepository.findById(id).get();
            certificateTable.setIsAvailable(false);
            log.info("Certificate with id {} was deleted", id);
            return certificateTable;
        }
        log.info("Certificate with id {} not found", id);
        return null;
    }

    private Certificates setCertificateObject(Certificates certificateTable, Certificates certificateInput) {
        certificateTable.setTherapist(certificateInput.getTherapist());
        certificateTable.setPatient(certificateInput.getPatient());
        certificateTable.setVisionTest(certificateInput.getVisionTest());
        certificateTable.setPsychoanalyse(certificateInput.getPsychoanalyse());
        certificateTable.setElectrocardiogram(certificateInput.getElectrocardiogram());
        certificateTable.setDiagnose(certificateInput.getDiagnose());
        certificateTable.setCreated(certificateInput.getCreated());
        certificateTable.setChanged(certificateInput.getChanged());
        certificateTable.setIsAvailable(certificateInput.getIsAvailable());

        return certificateTable;
    }
}
