CREATE TABLE trainingtask.employees
(
    id         SERIAL PRIMARY KEY,
    surname    VARCHAR(200),
    name       VARCHAR(200),
    patronymic VARCHAR(200),
    position   VARCHAR(100)
);