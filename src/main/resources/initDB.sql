DROP TABLE IF EXISTS cars;
DROP TABLE IF EXISTS persons;
DROP TABLE IF EXISTS cities;
DROP SEQUENCE IF EXISTS global_seq CASCADE;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE cities
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    city_name        VARCHAR                 NOT NULL
);
CREATE UNIQUE INDEX cities_unique_name_idx ON cities (city_name);

CREATE TABLE persons
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    first_name      VARCHAR                 NOT NULL,
    last_name       VARCHAR                 NOT NULL,
    patronymic      VARCHAR                 NOT NULL,
    city_id         INTEGER                 NOT NULL,
    FOREIGN KEY (city_id) REFERENCES cities (id)
);
CREATE UNIQUE INDEX persons_unique_first_last_patronymic_idx ON persons (first_name, last_name, patronymic);


CREATE TABLE cars
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    person_id       INTEGER NOT NULL,
    model           VARCHAR,
    license         VARCHAR,
    FOREIGN KEY (person_id) REFERENCES persons (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX cars_unique_model_license_idx ON cars (model, license);

