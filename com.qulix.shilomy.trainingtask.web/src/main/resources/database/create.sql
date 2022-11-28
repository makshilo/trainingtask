-- Создание схемы проекта
CREATE SCHEMA trainingtaskdb

    -- создание таблицы сотрудников
    CREATE TABLE trainingtaskdb.employee_list
    (
        last_name   VARCHAR(50),
        first_name  VARCHAR(50) NOT NULL,
        patronymic  VARCHAR(50),
        employee_position    VARCHAR(50) NOT NULL,
        id          INTEGER identity,
        PRIMARY KEY (id)
    )

    -- создание таблицы проектов
    CREATE TABLE trainingtaskdb.project_list
    (
        project_name        VARCHAR(100) NOT NULL,
        description VARCHAR(1000),
        id          INTEGER identity,
        PRIMARY KEY (id)
    )

    -- создание таблицы задач
    CREATE TABLE trainingtaskdb.task_list
    (
        status      VARCHAR(20) NOT NULL,
        task_name        VARCHAR(50) NOT NULL,
        project  INTEGER, -- идентификатор проекта которому принадлежит задача
        task_work        INTEGER,
        start_date  DATE,
        end_date    DATE,
        executor INTEGER, -- идентификатор работника
        id          INTEGER identity,
        PRIMARY KEY (id),
        CONSTRAINT task_list_employee_list_employee_id_fk
            FOREIGN KEY (executor) REFERENCES trainingtaskdb.employee_list -- ключ получения идентификатора работника
                ON UPDATE CASCADE ON DELETE SET NULL,
        CONSTRAINT task_list_project_list_project_id_fk
            FOREIGN KEY (project) REFERENCES trainingtaskdb.project_list -- ключ получения идентификатора проекта
                ON UPDATE CASCADE ON DELETE CASCADE
    )