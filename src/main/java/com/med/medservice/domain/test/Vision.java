package com.med.medservice.domain.test;

import com.med.medservice.domain.Certificates;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "vision")
public class Vision extends Certificates {

    @Column
    private Double sinister;

    @Column
    private Double dexter;
}
