CREATE TABLE trainingtask.tasks
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(200),
    project_id INT REFERENCES trainingtask.projects(id),
    estimate INT,
    start_date DATE,
    end_date DATE,
    status VARCHAR(20),
    employee_id INT REFERENCES trainingtask.employees(id)
);