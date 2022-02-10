package com.med.medservice.service;

import com.med.medservice.domain.Certificate;
import com.med.medservice.domain.Doctors;
import com.med.medservice.repository.CertificatesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
@Slf4j
public class CertificateServiceImpl implements  CertificateService {

    private final CertificatesRepository certificatesRepository;

    @Autowired
    private final DoctorService doctorService;

    @Transactional
    public void saveDataIntoDB(Certificate certificateTableObject) {
        certificatesRepository.save(certificateTableObject);
        log.info("Data about certificate {} is saved", certificateTableObject.getId());
    }

    public Certificate getCertificateById(UUID id) {
        Certificate certificate;
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

    public Certificate createCertificateObject(Certificate certificate) {
        Certificate certificateTable = new Certificate();
        final UUID id = UUID.randomUUID();

        certificateTable.setId(id);
        certificateTable.setCreated(LocalDateTime.now());
        setCertificateObject(certificateTable, certificate);

        return certificateTable;
    }

    public List<Certificate> getAllCertificatesList() {
        return certificatesRepository.findAll();
    }

    public Certificate updateCertificateInfo(UUID id, Certificate certificate) {
        Certificate certificateTable;

        if (certificatesRepository.findById(id).isPresent()) {
            certificateTable = certificatesRepository.findById(id).get();
            certificateTable.setChanged(LocalDateTime.now());
            setCertificateObject(certificateTable, certificate);

            return certificateTable;
        } else return null;
    }

    public Certificate deleteCertificateFromAccess(UUID id) {
        Certificate certificateTable;

        if (certificatesRepository.findById(id).isPresent()) {
            certificateTable = certificatesRepository.findById(id).get();
            certificateTable.setIsAvailable(false);
            log.info("Certificate with id {} was deleted", id);
            return certificateTable;
        }
        log.info("Certificate with id {} not found", id);
        return null;
    }

    private Certificate setCertificateObject(Certificate certificateTable, Certificate certificateInput) {
        certificateTable.setPatient(certificateInput.getPatient());
        certificateTable.setVisionTest(certificateInput.getVisionTest());
        certificateTable.setPsychoanalyse(certificateInput.getPsychoanalyse());
        certificateTable.setElectrocardiogram(certificateInput.getElectrocardiogram());
        certificateTable.setDiagnose(certificateInput.getDiagnose());
        certificateTable.setIsAvailable(certificateInput.getIsAvailable());

        setDoctorsSpecialization(certificateTable, certificateInput);

        return certificateTable;
    }

    private void setDoctorsSpecialization(Certificate certificateTable, Certificate certificateInput) {
        Doctors doctorsInfo = doctorService.getDoctorById(certificateInput.getTherapist().getId());
        certificateTable.setTherapist(doctorsInfo);
        UUID doctorId = doctorsInfo.getId();

        if (certificateInput.getPsychoanalyse().getSpecialist() == doctorId) {
            certificateTable.getPsychoanalyse().setSpecialist(doctorId);
        } else {
            UUID specialistInput = certificateInput.getPsychoanalyse().getSpecialist();
            UUID psychiatrist = doctorService.getDoctorById(specialistInput).getId();
            certificateTable.getPsychoanalyse().setSpecialist(psychiatrist);
        }

        if (certificateInput.getVisionTest().getSpecialist() == doctorId) {
            certificateTable.getVisionTest().setSpecialist(doctorId);
        } else {
            UUID specialistInput = certificateInput.getVisionTest().getSpecialist();
            UUID oculist = doctorService.getDoctorById(specialistInput).getId();
            certificateTable.getVisionTest().setSpecialist(oculist);
        }

        if (certificateInput.getElectrocardiogram().getSpecialist() == doctorId) {
            certificateTable.getElectrocardiogram().setSpecialist(doctorId);
        } else {
            UUID specialistInput = certificateInput.getElectrocardiogram().getSpecialist();
            UUID cardiologist = doctorService.getDoctorById(specialistInput).getId();
            certificateTable.getElectrocardiogram().setSpecialist(cardiologist);
        }
    }
}
