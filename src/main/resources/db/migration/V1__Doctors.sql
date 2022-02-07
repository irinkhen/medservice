-- SET search_path TO public;
-- DROP EXTENSION IF EXISTS "uuid-ossp";
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table doctors
(
    id         uuid not null
        primary key,
    email      varchar(255)
        constraint uk_caifv0va46t2mu85cg5afmayf
            unique,
    first_name varchar(255),
    is_active  boolean,
    last_name  varchar(255),
    password   varchar(255),
    role       varchar(255)
);

alter table doctors
    owner to postgres;

create table specialization
(
    id             uuid not null
        primary key,
    specialization varchar(255)
);

alter table specialization
    owner to postgres;

create table doctors_specialization
(
    doctors_id        uuid not null
        constraint fknugj2lcctp8puxylxkyfxmngx
            references doctors,
    specialization_id uuid not null
        constraint fk98mdfggwiv0p6hr76m1ku8hbs
            references specialization
);

alter table doctors_specialization
    owner to postgres;

INSERT INTO specialization  (id, specialization) VALUES
                                  (uuid_generate_v4(), 'THERAPIST'),
                                  (uuid_generate_v4(), 'HEMATOLOGIST'),
                                  (uuid_generate_v4(), 'PSYCHIATRIST'),
                                  (uuid_generate_v4(), 'OCULIST');