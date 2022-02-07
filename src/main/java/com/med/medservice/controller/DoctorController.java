package com.med.medservice.controller;

import com.med.medservice.domain.Doctors;
import com.med.medservice.service.DoctorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/v1/doctor")
@AllArgsConstructor
@Slf4j
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/{id}")
    public ResponseEntity<Doctors> getDoctorById(@PathVariable UUID id) {
        Doctors doctor = doctorService.getDoctorById(id);
        if (id == null) {
            log.info("Doctor with id {} not found!", id);
            return new ResponseEntity<>(BAD_REQUEST);
        } else if (doctor == null) return new ResponseEntity<>(NOT_FOUND);
        return ResponseEntity.accepted().body(doctor);
    }

    @GetMapping
    public ResponseEntity<Iterable<Doctors>> getDoctorsList() {
        Iterable<Doctors> doctorsList = doctorService.getDoctorsList();
        return ResponseEntity.accepted().body(doctorsList);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Doctors> createDoctor(@RequestBody Doctors doctor) {
        Doctors doctorTableObject = doctorService.setDoctorInfoByDb(doctor);
        if (doctorTableObject.getEmail() == null && doctorTableObject.getPassword() == null) {
            log.info("Email or password cannot be null");
            return ResponseEntity.badRequest().body(doctorTableObject);
        } else if (doctorTableObject.getRole() == null) {
            log.info("You need to fill user role");
            return ResponseEntity.badRequest().body(doctorTableObject);
        }

        doctorService.saveDataIntoDB(doctorTableObject);
        return ResponseEntity.accepted().body(doctorTableObject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctors> updateDoctorData(@PathVariable UUID id, @RequestBody Doctors doctor) {
        Doctors doctorTableObject = doctorService.updateDoctorInfo(id, doctor);
        doctorService.saveDataIntoDB(doctorTableObject);

        return ResponseEntity.accepted().body(doctorTableObject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Doctors> deleteDoctor(@PathVariable UUID id) {
        Doctors doctorTableObject = doctorService.deleteDoctorFromService(id);
        doctorService.saveDataIntoDB(doctorTableObject);
        if (doctorTableObject.getIsActive()) {
            log.info("User with id {} is not deleted", id);
            return ResponseEntity.badRequest().body(doctorTableObject);
        }
        else return ResponseEntity.accepted().body(doctorTableObject);
    }

}
