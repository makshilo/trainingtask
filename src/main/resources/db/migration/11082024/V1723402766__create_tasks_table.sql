CREATE TABLE trainingtask.tasks
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(200),
    project_id BIGINT REFERENCES trainingtask.projects(id),
    estimate SMALLINT,
    start_date DATE,
    end_date DATE,
    status VARCHAR(20),
    employee_id BIGINT REFERENCES trainingtask.employees(id)
);