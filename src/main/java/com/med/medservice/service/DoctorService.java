package com.med.medservice.service;

import com.med.medservice.domain.Doctors;
import com.med.medservice.domain.Specialization;

import java.util.UUID;

public interface DoctorService {
    void saveDataIntoDB(Doctors doctorsTableObject);

    Doctors getDoctorById(UUID id);

    Iterable<Doctors> getDoctorsList();

    Doctors setDoctorInfoByDb(Doctors doctors);

    Specialization getDoctorSpecializationInfo(UUID specialization);

    Doctors updateDoctorInfo(UUID id, Doctors doctors);

    Doctors deleteDoctorFromService(UUID id);
}
