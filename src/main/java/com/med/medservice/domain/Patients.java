package com.med.medservice.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name = "patients")
@FieldDefaults(level =  PRIVATE)
public class Patients {
    @Id
    UUID id;
    String firstName;
    String lastName;
    @Column(unique = true)
    String email;
    @Column(unique = true, nullable = false)
    String phone;
}
