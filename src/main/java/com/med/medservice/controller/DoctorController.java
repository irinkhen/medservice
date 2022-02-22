package com.med.medservice.controller;

import com.med.medservice.domain.Doctors;
import com.med.medservice.service.DoctorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity<Doctors> getDoctorById(@PathVariable UUID id) {
        Doctors doctors = doctorService.getDoctorById(id);

        if (id == null) {
            log.info("Doctor id cannot be null");
            return new ResponseEntity<>(BAD_REQUEST);
        } else if (doctors == null) return new ResponseEntity<>(NOT_FOUND);

        return ResponseEntity.accepted().body(doctors);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity<Iterable<Doctors>> getDoctorsList() {
        Iterable<Doctors> doctorsList = doctorService.getDoctorsList();
        return ResponseEntity.accepted().body(doctorsList);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('write')")
    public ResponseEntity<Doctors> createDoctor(@RequestBody Doctors doctors) {
        Doctors doctorsTableObject = doctorService.setDoctorInfoByDb(doctors);

        if (doctorsTableObject.getEmail() == null && doctorsTableObject.getPassword() == null) {
            log.info("Email or password cannot be null");
            return ResponseEntity.badRequest().body(doctorsTableObject);
        } else if (doctorsTableObject.getRole() == null) {
            log.info("You need to fill user role");
            return ResponseEntity.badRequest().body(doctorsTableObject);
        }
        doctorService.saveDataIntoDB(doctorsTableObject);

        return ResponseEntity.accepted().body(doctorsTableObject);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('update')")
    public ResponseEntity<Doctors> updateDoctorData(@PathVariable UUID id, @RequestBody Doctors doctors) {
        Doctors doctorsTableObject = doctorService.updateDoctorInfo(id, doctors);
        doctorService.saveDataIntoDB(doctorsTableObject);

        return ResponseEntity.accepted().body(doctorsTableObject);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('delete')")
    public ResponseEntity<Doctors> deleteDoctor(@PathVariable UUID id) {
        Doctors doctorsTableObject = doctorService.deleteDoctorFromService(id);
        doctorService.saveDataIntoDB(doctorsTableObject);

        if (doctorsTableObject.getIsActive()) {
            log.info("User with id {} is not deleted", id);
            return ResponseEntity.badRequest().body(doctorsTableObject);
        }

        return ResponseEntity.accepted().body(doctorsTableObject);
    }

}
