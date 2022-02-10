package com.med.medservice.domain.test;

import com.med.medservice.domain.Certificate;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@Entity
@Table(name = "psycho")
@FieldDefaults(level =  PRIVATE)
public class Psycho extends Certificate {
    UUID specialist;
    @Column(nullable = false)
    Boolean schizophrenia;
    Boolean addictions;
    Boolean dementia;
    Boolean mentalRetardation;
}
