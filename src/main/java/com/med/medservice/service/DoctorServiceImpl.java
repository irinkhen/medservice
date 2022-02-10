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

    public Doctors setDoctorInfoByDb(Doctors doctors) {
        Doctors doctorsTable = new Doctors();
        final UUID id = UUID.randomUUID();

        doctorsTable.setId(id);
        setDoctorObject(doctorsTable, doctors);

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

            log.info("Get doctor specialization: {} with id {}", specializationId, doctorSpecializationName);

            return specializationTable;
        }
        else log.info("Specialisation with id {} not found!", specializationId);

        return null;
    }

    public Doctors updateDoctorInfo(UUID id, Doctors doctors) {
        Doctors doctorsTable;
        if (doctorRepository.findById(id).isPresent()) {
            doctorsTable = doctorRepository.findById(id).get();
            setDoctorObject(doctorsTable, doctors);

            return doctorsTable;
        } else return null;
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
        Specialization doctorSpecializationInfo = getDoctorSpecializationInfo
                (doctorsObjectInput.getSpecialization().get(0).getId());
        doctorsTable.setFirstName(doctorsObjectInput.getFirstName());
        doctorsTable.setLastName(doctorsObjectInput.getLastName());
        doctorsTable.setEmail(doctorsObjectInput.getEmail());
        doctorsTable.setPassword(doctorsObjectInput.getPassword());
        doctorsTable.setRole(doctorsObjectInput.getRole());
        doctorsTable.setIsActive(doctorsObjectInput.getIsActive());
        doctorsTable.setStatus(doctorsObjectInput.getStatus());
        doctorsTable.setSpecialization(List.of(doctorSpecializationInfo));
    }
}
