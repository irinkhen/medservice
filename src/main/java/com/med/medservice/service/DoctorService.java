package com.med.medservice.service;

import com.med.medservice.domain.Doctors;
import com.med.medservice.domain.Specialization;

import java.util.UUID;

public interface DoctorService {
    void saveDataIntoDB(Doctors doctorTableObject);

    Doctors getDoctorById(UUID id);

    Iterable<Doctors> getDoctorsList();

    Doctors setDoctorInfoByDb(Doctors doctor);

    Specialization getDoctorSpecializationInfo(UUID specialization);

    Doctors updateDoctorInfo(UUID id, Doctors doctor);

    Doctors deleteDoctorFromService(UUID id);
}
