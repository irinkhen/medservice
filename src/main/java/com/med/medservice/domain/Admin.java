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
@RequiredArgsConstructor
@Entity
@Table(name = "admins")
@FieldDefaults(level =  PRIVATE)
public class Admin {
    @Id
    UUID id;
    String firstName;
    String lastName;
    String password;
    @Column(unique = true)
    String email;
    Boolean isActive;
}
