package com.med.medservice.model.responses;

import com.med.medservice.domain.Patients;
import com.med.medservice.domain.test.Electrocardiogram;
import com.med.medservice.domain.test.Psycho;
import com.med.medservice.domain.test.Vision;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

import static lombok.AccessLevel.PUBLIC;

@Builder
@FieldDefaults(level = PUBLIC)
public class CertificateResponse {
    UUID id;
    UUID doctorId;
    Patients patient;
    Psycho psychoanalyse;
    Electrocardiogram electrocardiogram;
    Vision visionTest;
    LocalDateTime created;
    LocalDateTime changed;
    String diagnose;
    String error;
}
