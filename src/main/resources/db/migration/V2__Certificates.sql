create table certificates
(
    id                   uuid not null primary key,
    changed              timestamp,
    created              timestamp,
    diagnose             varchar(255),
    is_available         boolean,
    electrocardiogram_id uuid constraint fkrkqthklxbynh2vlinvs6s31d0 references certificates,
    patient_id           uuid constraint fkrmwp3n0q5kxg257whgmgjkde0 references patients,
    psychoanalyse_id     uuid,
    therapist_id         uuid constraint fkmea9c23feavo5cxw5je17xeij references doctors,
    vision_test_id       uuid constraint fkdmc28wsgevsevxq50xl4r9hb5 references certificates
);

alter table certificates owner to postgres;

create table cardiogram
(
    arrhythmia                 boolean,
    atrial_depolarisation      integer,
    specialist                 uuid,
    tachycardia                boolean,
    ventricular_depolarisation integer,
    id                         uuid not null primary key constraint fk1yhao56serhj4ubgkpwlxdxu9 references certificates
);

alter table cardiogram
    owner to postgres;

create table psycho
(
    addictions         boolean,
    dementia           boolean,
    mental_retardation boolean,
    schizophrenia      boolean not null,
    specialist         uuid,
    id                 uuid not null primary key constraint fkn4viaiv99q6fs1fbpqpdfcxtj references certificates
);

alter table psycho owner to postgres;

create table vision
(
    dexter     double precision,
    sinister   double precision,
    specialist uuid,
    id         uuid not null primary key constraint fkkn1bnfses05oou7i9memvxt8p references certificates
);

alter table vision owner to postgres;