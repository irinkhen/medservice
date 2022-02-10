package com.med.medservice.service;

import com.med.medservice.domain.Certificate;

import java.util.List;
import java.util.UUID;

public interface CertificateService {

    void saveDataIntoDB(Certificate certificateTableObject);

    Certificate getCertificateById(UUID id);

    Certificate createCertificateObject(Certificate certificate);

    List<Certificate> getAllCertificatesList();

    Certificate updateCertificateInfo(UUID id, Certificate certificate);

    Certificate deleteCertificateFromAccess(UUID id);
}
