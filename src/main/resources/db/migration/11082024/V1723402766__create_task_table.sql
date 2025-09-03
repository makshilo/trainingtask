CREATE TABLE trainingtask.task
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(200),
    project_id BIGINT REFERENCES trainingtask.project(id) ON UPDATE CASCADE ON DELETE CASCADE,
    estimate SMALLINT,
    start_date DATE,
    end_date DATE,
    status VARCHAR(20),
    employee_id BIGINT REFERENCES trainingtask.employee(id) ON UPDATE CASCADE ON DELETE SET NULL
);
