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
@Table(name = "vision")
@FieldDefaults(level = PRIVATE)
public class Vision extends Certificate {
    UUID specialist;
    Double sinister;
    Double dexter;
}
