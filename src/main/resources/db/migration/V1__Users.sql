-- DROP EXTENSION IF EXISTS "uuid-ossp";

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table doctors
(
    id         uuid not null primary key,
    email      varchar(100) constraint uk_caifv0va46t2mu85cg5afmayf unique,
    first_name varchar(30),
    is_active  boolean,
    last_name  varchar(30),
    password   varchar(100),
    role       varchar(12),
    status     varchar(12)
);

alter table doctors owner to postgres;

create table specialization
(
    id             uuid not null primary key,
    specialization varchar(36)
);

alter table specialization owner to postgres;

create table doctors_specialization
(
    doctors_id        uuid not null constraint fknugj2lcctp8puxylxkyfxmngx references doctors,
    specialization_id uuid not null constraint fk98mdfggwiv0p6hr76m1ku8hbs references specialization
);

alter table doctors_specialization owner to postgres;

create table patients
(
    id         uuid not null primary key,
    email      varchar(100) constraint uk_a370hmxgv0l5c9panryr1ji7d unique,
    first_name varchar(30),
    last_name  varchar(30),
    phone      varchar(15) not null constraint uk_3losa44agqkfqpkxfdv7wf1dq unique
);

alter table patients owner to postgres;

INSERT INTO specialization  (id, specialization)
VALUES
       (uuid_generate_v4(), 'THERAPIST'),
       (uuid_generate_v4(), 'CARDIOLOGIST'),
       (uuid_generate_v4(), 'PSYCHIATRIST'),
       (uuid_generate_v4(), 'OCULIST');

INSERT INTO doctors (id, email, first_name, is_active, last_name, password, role, status)
VALUES (uuid_generate_v4(), 'doctor@mail.com', 'doctor', true, 'doctor',
        '$2y$12$O2BTZRimdZFWvaD9dORQNOKzLCPJVjngKM2d1YEP6GS3iK03K8rtO', 'ADMIN', 'ACTIVE');