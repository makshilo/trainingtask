create schema TRAININGTASKDB

create table TRAININGTASKDB.EMPLOYEE_LIST
(
    EMPLOYEE_LAST_NAME   VARCHAR(50),
    EMPLOYEE_FIRST_NAME  VARCHAR(50) not null,
    EMPLOYEE_PATRONYMIC  VARCHAR(50),
    EMPLOYEE_POSITION    VARCHAR(50) not null,
    EMPLOYEE_ID          INTEGER identity,
    EMPLOYEE_UNIQUE_HASH VARCHAR(200),
    primary key (EMPLOYEE_ID)
);

create table TRAININGTASKDB.PROJECT_LIST
(
    PROJECT_NAME        VARCHAR(100) not null,
    PROJECT_DESCRIPTION VARCHAR(1000),
    PROJECT_ID          INTEGER identity,
    PROJECT_UNIQUE_HASH VARCHAR(1100),
    primary key (PROJECT_ID)
);

create table TRAININGTASKDB.TASK_LIST
(
    TASK_STATUS      VARCHAR(20) not null,
    TASK_NAME        VARCHAR(50) not null,
    TASK_PROJECT_ID  INTEGER,
    TASK_WORK        INTEGER,
    TASK_START_DATE  DATE,
    TASK_END_DATE    DATE,
    TASK_EXECUTOR_ID INTEGER,
    TASK_ID          INTEGER identity,
    TASK_UNIQUE_HASH VARCHAR(70),
    primary key (TASK_ID),
    constraint TASK_LIST_EMPLOYEE_LIST_EMPLOYEE_ID_FK
        foreign key (TASK_EXECUTOR_ID) references TRAININGTASKDB.EMPLOYEE_LIST
            on update cascade on delete set null,
    constraint TASK_LIST_PROJECT_LIST_PROJECT_ID_FK
        foreign key (TASK_PROJECT_ID) references TRAININGTASKDB.PROJECT_LIST
            on update cascade on delete set null
);