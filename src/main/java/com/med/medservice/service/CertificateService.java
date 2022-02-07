package com.med.medservice.service;

import com.med.medservice.domain.Certificates;

import java.util.List;
import java.util.UUID;

public interface CertificateService {

    void saveDataIntoDB(Certificates certificateTableObject);

    Certificates getCertificateById(UUID id);

    Certificates createCertificateObject(Certificates certificate);

    List<Certificates> getAllCertificatesList();

    Certificates updateCertificateInfo(UUID id, Certificates certificate);

    Certificates deleteCertificateFromAccess(UUID id);
}
