package com.med.medservice.domain;

import com.med.medservice.domain.test.Electrocardiogram;
import com.med.medservice.domain.test.Psycho;
import com.med.medservice.domain.test.Vision;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.InheritanceType.JOINED;
import static lombok.AccessLevel.PRIVATE;
import static org.hibernate.annotations.FetchMode.JOIN;

@Entity
@Table(name = "certificates")
@Inheritance(strategy = JOINED)
@FieldDefaults(level = PRIVATE)
@RequiredArgsConstructor
@Getter
@Setter
public class Certificates {
    @Id
    UUID id;

    @ManyToOne(cascade = ALL)
    Doctors therapist;

    @ManyToOne(cascade = ALL)
    Patients patient;

    @OneToMany(cascade = ALL, targetEntity = Certificates.class)
    @Fetch(JOIN)
    @JoinColumn
    List<Psycho> psychoanalyse;

    @OneToMany(cascade = ALL, targetEntity = Certificates.class)
    @Fetch(JOIN)
    @JoinColumn
    List<Electrocardiogram> electrocardiogram;

    //@OneToMany(cascade = ALL, targetEntity = Certificates.class)
    @OneToOne
    @Fetch(JOIN)
    @JoinColumn
    Vision visionTest;

    LocalDateTime created;
    LocalDateTime changed;
    String diagnose;
    Boolean isAvailable;
}
