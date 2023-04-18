/**
* Схема trainingtaskdb
*/
CREATE SCHEMA trainingtaskdb

    /**
     * Таблица сотрудников
     *
     * last_name - фамилия
     * first_name - имя
     * patronymic - отчество
     * employee_position - должность
     * id - идентификатор
     */
    CREATE TABLE trainingtaskdb.employees
    (
        last_name   VARCHAR(50),
        first_name  VARCHAR(50) NOT NULL,
        patronymic  VARCHAR(50),
        employee_position    VARCHAR(50) NOT NULL,
        id          INTEGER identity,
        PRIMARY KEY (id)
    )

    /**
     * Таблица проектов
     *
     * project_name - имя проекта
     * description - описание
     * id - идентификатор
     */
    CREATE TABLE trainingtaskdb.projects
    (
        project_name        VARCHAR(100) NOT NULL,
        description VARCHAR(1000),
        id          INTEGER identity,
        PRIMARY KEY (id)
    )

    /**
     * Таблица сотрудников
     *
     * status - статус
     * task_name - название
     * project - идентификатор проекта которому принадлежит задача
     * task_work - работа
     * start_date - дата начала
     * end_date - дата окончания
     * executor - идентификатор работника
     * id - идентификатор
     *
     * task_list_employees_employee_id_fk - внешний ключ идентификатора работника
     * task_list_projects_project_id_fk - внешний ключ идентификатора проекта
     */
    CREATE TABLE trainingtaskdb.tasks
    (
        status      VARCHAR(20) NOT NULL,
        task_name        VARCHAR(50) NOT NULL,
        project  INTEGER,
        task_work        INTEGER,
        start_date  DATE,
        end_date    DATE,
        executor INTEGER,
        id          INTEGER identity,
        PRIMARY KEY (id),
        CONSTRAINT task_list_employees_employee_id_fk
            FOREIGN KEY (executor) REFERENCES trainingtaskdb.employees
                ON UPDATE CASCADE ON DELETE SET NULL,
        CONSTRAINT task_list_projects_project_id_fk
            FOREIGN KEY (project) REFERENCES trainingtaskdb.projects
                ON UPDATE CASCADE ON DELETE CASCADE
    )