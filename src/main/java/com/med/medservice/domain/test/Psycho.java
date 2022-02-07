package com.med.medservice.domain.test;

import com.med.medservice.domain.Certificates;
import com.med.medservice.domain.Doctors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static javax.persistence.CascadeType.ALL;
import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@Entity
@Table(name = "psycho")
@FieldDefaults(level =  PRIVATE)
public class Psycho extends Certificates {
    @ManyToOne(cascade = ALL)
    Doctors doctor;
    @Column(nullable = false)
    Boolean schizophrenia;
    Boolean addictions;
    Boolean dementia;
    Boolean mentalRetardation;
}
