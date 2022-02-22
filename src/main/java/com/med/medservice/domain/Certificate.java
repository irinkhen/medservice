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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.AUTO;
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
public class Certificate {
    @Id
    @GeneratedValue(strategy = AUTO)
    UUID id;

    @ManyToOne
    @Fetch(JOIN)
    Doctors therapist;

    @ManyToOne(cascade = ALL)
    Patients patient;

    @OneToOne(cascade = ALL)
    @Fetch(JOIN)
    Psycho psychoanalyse;

    @OneToOne(cascade = ALL)
    @Fetch(JOIN)
    Electrocardiogram electrocardiogram;

    @OneToOne(cascade = ALL)
    @Fetch(JOIN)
    Vision visionTest;

    LocalDateTime created;

    LocalDateTime changed;

    String diagnose;

    Boolean isAvailable;
}
