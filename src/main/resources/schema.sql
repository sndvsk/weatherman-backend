create table formstorage
(
    latitude    DOUBLE not null,
    longitude   DOUBLE not null,
    start_date  DATE   not null,
    end_date    DATE   not null,
    api_names   VARCHAR(64)
);

create table openmeteo
(
    date        DATE   not null,
    temperature DOUBLE not null
);

create table yrno
(
    date        DATE   not null,
    temperature DOUBLE not null
);

create table weatherapi
(
    date        DATE   not null,
    temperature DOUBLE not null
);

create table graph
(
    date                    DATE   not null,
    yrno_temperature        DOUBLE not null,
    weatherapi_temperature  DOUBLE not null,
    openmeteo_temperature   DOUBLE not null
);



