package com.med.medservice.domain.test;

import com.med.medservice.domain.Certificates;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Table;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@Entity
@Table(name = "cardiogram")
@FieldDefaults(level = PRIVATE)
public class Electrocardiogram extends Certificates {
    Integer atrialDepolarisation;
    Integer ventricularDepolarisation;
    Boolean tachycardia;
    Boolean arrhythmia;
}
