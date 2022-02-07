package com.med.medservice.domain;

import com.med.medservice.model.Role;
import com.med.medservice.model.UserStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "doctors")
@FieldDefaults(level =  PRIVATE)
public class Doctors {
    @Id
    UUID id;
    String firstName;
    String lastName;
    String password;
    @Column(unique = true)
    String email;
    Boolean isActive;
    @OneToMany(cascade = ALL)
    List<Specialization> specialization;
    @Enumerated(value = STRING)
    Role role;
    @Enumerated(value = STRING)
    UserStatus status;
}
