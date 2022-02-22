package com.med.medservice.service;

import com.med.medservice.domain.Doctors;
import com.med.medservice.domain.Specialization;
import com.med.medservice.model.Specialists;
import com.med.medservice.repository.DoctorRepository;
import com.med.medservice.repository.SpecializationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final SpecializationRepository specializationRepository;

    @Transactional
    public void saveDataIntoDB(Doctors doctorsTableObject) {
        doctorRepository.save(doctorsTableObject);
        log.info("Data about doctor {} is saved", doctorsTableObject.getId());
    }

    public Doctors getDoctorById(UUID id) {
        Doctors doctors;
        if (doctorRepository.findById(id).isPresent()){
            doctors = doctorRepository.findById(id).get();
            log.info("Get doctor with id {}", id);
            return doctors;
        } else {
            log.info("Doctor with id {} not found!", id);
            return null;
        }
    }

    public Iterable<Doctors> getDoctorsList() {
        return doctorRepository.findAll();
    }

    public Doctors createNewDoctor(Doctors doctorInfo) {
        Doctors doctorsTable = new Doctors();
        final UUID id = UUID.randomUUID();
        Specialization doctorSpecializationInfo = getDoctorSpecializationInfo
                (doctorInfo.getSpecialization().get(0).getId());

        doctorsTable.setId(id);
        doctorsTable.setSpecialization(List.of(doctorSpecializationInfo));
        setDoctorObject(doctorsTable, doctorInfo);

        return doctorsTable;
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

            log.info("Get doctor specialization: {} with id {}", doctorSpecializationName, specializationId);

            return specializationTable;
        }
        else log.info("Specialisation with id {} not found!", specializationId);

        return null;
    }

    public Doctors updateDoctorInfo(UUID id, Doctors doctors) {
        Doctors doctorsTable;
        if (doctorRepository.findById(id).isPresent()) {
            doctorsTable = doctorRepository.findById(id).get();
            log.info("Find doctor with id {}", id);
            setDoctorObject(doctorsTable, doctors);
            return doctorsTable;
        } else {
            log.info("Doctor with id {} not found", id);
            return null;
        }
    }

    public Doctors deleteDoctorFromService(UUID id) {
        Doctors doctorsTable;

        if (doctorRepository.findById(id).isPresent()) {
            doctorsTable = doctorRepository.findById(id).get();
            doctorsTable.setIsActive(false);
            log.info("Doctor with id {} was deleted", id);
            return doctorsTable;
        }

        log.info("Doctor with id {} not found", id);
        return null;
    }

    private void setDoctorObject(Doctors doctorsTable, Doctors doctorsObjectInput) {
        doctorsTable.setFirstName(doctorsObjectInput.getFirstName());
        doctorsTable.setLastName(doctorsObjectInput.getLastName());
        doctorsTable.setEmail(doctorsObjectInput.getEmail());
        doctorsTable.setRole(doctorsObjectInput.getRole());
        doctorsTable.setIsActive(doctorsObjectInput.getIsActive());
        doctorsTable.setStatus(doctorsObjectInput.getStatus());

        String password = BCrypt.hashpw(doctorsObjectInput.getPassword(), BCrypt.gensalt(12));
        doctorsTable.setPassword(password);
    }
}
