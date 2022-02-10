package com.med.medservice.model.responses;

import com.med.medservice.model.Role;
import com.med.medservice.model.Specialists;
import com.med.medservice.model.UserStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class DoctorResponse {
    UUID id;
    String firstName;
    String lastName;
    String email;
    Specialists specialization;
    Role role;
    UserStatus status;
    String error;
}
