create table patients
(
    id         uuid not null
        primary key,
    email      varchar(255)
        constraint uk_a370hmxgv0l5c9panryr1ji7d
            unique,
    first_name varchar(255),
    last_name  varchar(255)
);

alter table patients
    owner to postgres;

create table certificates
(
    id           uuid not null
        primary key,
    diagnose     varchar(255),
    patient_id   uuid
        constraint fkrmwp3n0q5kxg257whgmgjkde0
            references patients,
    therapist_id uuid
        constraint fkmea9c23feavo5cxw5je17xeij
            references doctors
);

alter table certificates
    owner to postgres;

create table psycho
(
    addictions         boolean,
    dementia           boolean,
    mental_retardation boolean,
    schizophrenia      boolean not null,
    id                 uuid    not null
        primary key
        constraint fkn4viaiv99q6fs1fbpqpdfcxtj
            references certificates,
    doctor_id          uuid
        constraint fkj443nijrxvrgpijyyfhvs6xwp
            references doctors
);

alter table psycho
    owner to postgres;

-- create table patients_certificate
-- (
--     patients_id    uuid not null
--         constraint fkqvmjf2fsq3bg59y8fgauare6v
--             references patients,
--     certificate_id uuid not null
--         constraint fkqoniryq11l4r8hkumfyohqynq
--             references certificates,
--     primary key (patients_id, certificate_id)
-- );
--
-- alter table patients_certificate
--     owner to postgres;

-- create table certificates_psychoanalyse
-- (
--     certificates_id  uuid not null
--         constraint fktqpvkrr4know3cqsuj99u07ro
--             references certificates,
--     psychoanalyse_id uuid not null
--         constraint uk_9sibcwtr3gmcyyks2kjhersnq
--             unique
--         constraint fk3qme3yh2hbl7jfqh990m15i3a
--             references psycho
-- );
--
-- alter table certificates_psychoanalyse
--     owner to postgres;