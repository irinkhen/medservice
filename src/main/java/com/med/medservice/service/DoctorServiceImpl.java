package com.med.medservice.service;

import com.med.medservice.domain.Doctors;
import com.med.medservice.domain.Specialization;
import com.med.medservice.model.Specialists;
import com.med.medservice.repository.DoctorRepository;
import com.med.medservice.repository.SpecializationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@AllArgsConstructor
@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final SpecializationRepository specializationRepository;

    @Transactional
    public void saveDataIntoDB(Doctors doctorTableObject) {
        doctorRepository.save(doctorTableObject);
        log.info("Data about doctor {} is saved", doctorTableObject.getId());
    }

    public Doctors getDoctorById(UUID id) {
        Doctors doctor;
        if (doctorRepository.findById(id).isPresent()){
            doctor = doctorRepository.findById(id).get();
            log.info("Get doctor with id {}", id);
            return doctor;
        } else {
            log.info("Doctor with id {} not found!", id);
            return null;
        }
    }

    public Iterable<Doctors> getDoctorsList() {
        return doctorRepository.findAll();
    }

    public Doctors setDoctorInfoByDb(Doctors doctor) {
        Doctors doctorTable = new Doctors();
        final UUID id = UUID.randomUUID();

        doctorTable.setId(id);
        doctorTable.setFirstName(doctor.getFirstName());
        doctorTable.setLastName(doctor.getLastName());
        doctorTable.setEmail(doctor.getEmail());
        doctorTable.setPassword(doctor.getPassword());
        doctorTable.setRole(doctor.getRole());
        doctorTable.setIsActive(doctor.getIsActive());
        doctorTable.setSpecialization(doctor.getSpecialization());

        return doctorTable;
    }

    public Specialization getDoctorSpecializationInfo(UUID specializationId) {
        Specialization specializationTable = new Specialization();

        if(specializationRepository.findById(specializationId).isPresent()) {

            Specialists doctorSpecializationName = specializationRepository
                    .findById(specializationId)
                    .get()
                    .getSpecialization();

            specializationTable.setSpecialization(doctorSpecializationName);
            specializationTable.setId(specializationId);

            log.info("Get doctor specialization: {} with id {}", specializationId, doctorSpecializationName);

            return specializationTable;
        }
        else log.info("Specialisation with id {} not found!", specializationId);

        return null;
    }

    public Doctors updateDoctorInfo(UUID id, Doctors doctor) {
        Doctors doctorTable;
        if (doctorRepository.findById(id).isPresent()) {
            doctorTable = doctorRepository.findById(id).get();

            doctorTable.setFirstName(doctor.getFirstName());
            doctorTable.setLastName(doctor.getLastName());
            doctorTable.setEmail(doctor.getEmail());
            doctorTable.setPassword(doctor.getPassword());
            doctorTable.setRole(doctor.getRole());
            doctorTable.setIsActive(doctor.getIsActive());
            doctorTable.setSpecialization(doctor.getSpecialization());

            return doctorTable;
        } else return null;
    }

    public Doctors deleteDoctorFromService(UUID id) {
        Doctors doctorTableObject = getDoctorById(id);
        doctorTableObject.setIsActive(false);
        return doctorTableObject;
    }
}