--DROP SCHEMA IF EXISTS general CASCADE;

CREATE SCHEMA IF NOT EXISTS general;

--UTILS SCHEMA

CREATE TABLE general.metrics
(
    id varchar NOT NULL,
    user_id varchar NOT NULL,
    metric varchar NOT NULL,
    value varchar NOT NULL,
    date date NOT NULL,
    CONSTRAINT pk_metrics
        PRIMARY KEY (id)
);



