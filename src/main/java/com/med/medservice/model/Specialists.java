package com.med.medservice.model;

import lombok.Getter;

@Getter
public enum Specialists {
    THERAPIST("therapist"),
    CARDIOLOGIST("cardiologist"),
    PSYCHIATRIST("psychiatrist"),
    OCULIST("oculist");

    private final String specialist;

    Specialists(String specialist) {
        this.specialist = specialist;
    }
}
