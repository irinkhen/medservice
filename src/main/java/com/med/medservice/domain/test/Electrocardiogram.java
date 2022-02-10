package com.med.medservice.domain.test;

import com.med.medservice.domain.Certificate;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@Entity
@Table(name = "cardiogram")
@FieldDefaults(level = PRIVATE)
public class Electrocardiogram extends Certificate {
    UUID specialist;
    Integer atrialDepolarisation;
    Integer ventricularDepolarisation;
    Boolean tachycardia;
    Boolean arrhythmia;
}
