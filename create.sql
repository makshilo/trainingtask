create table EMPLOYEE_LIST
(
    EMPLOYEE_LAST_NAME  VARCHAR(50),
    EMPLOYEE_FIRST_NAME VARCHAR(50) not null,
    EMPLOYEE_PATRONYMIC VARCHAR(50),
    EMPLOYEE_POSITION   VARCHAR(50) not null,
    EMPLOYEE_ID         INTEGER identity
        primary key
);

create table PROJECT_LIST
(
    PROJECT_NAME        VARCHAR(100) not null
        constraint PROJECTS_LIST_PROJECT_NAME_UINDEX
            unique,
    PROJECT_DESCRIPTION VARCHAR(1000),
    PROJECT_ID          INTEGER identity
        constraint PROJECT_LIST_EMPLOYEE_LIST_EMPLOYEE_ID_FK
            references EMPLOYEE_LIST (EMPLOYEE_ID)
);

create unique index PROJECTS_LIST_PROJECT_ID_UINDEX
    on PROJECT_LIST (PROJECT_ID);

create unique index SYS_IDX_PROJECTS_LIST_PK_10093
    on PROJECT_LIST (PROJECT_ID);

alter table PROJECT_LIST
    add constraint PROJECTS_LIST_PK
        primary key (PROJECT_ID);

create table PROJECT_EMPLOYEE_JOINT
(
    PROJECT_ID  INTEGER not null
        constraint PROJECT_EMPLOYEE_JOINT_PROJECT_LIST_PROJECT_ID_FK
            references PROJECT_LIST,
    EMPLOYEE_ID INTEGER not null
        constraint PROJECT_EMPLOYEE_JOINT_EMPLOYEE_LIST_EMPLOYEE_ID_FK
            references EMPLOYEE_LIST (EMPLOYEE_ID)
);

create table TASK_LIST
(
    TASK_STATUS       BOOLEAN default FALSE not null,
    TASK_NAME         VARCHAR(50)           not null
        constraint TASK_LIST_TASK_NAME_UINDEX
            unique,
    TASK_PROJECT_NAME VARCHAR(100)          not null
        constraint TASK_LIST_TASK_PROJECT_NAME_UINDEX
            unique,
    TASK_WORK         VARCHAR(1000)         not null,
    TASK_START_DATE   DATE,
    TASK_END_DATE     DATE,
    TASK_EXECUTOR     INTEGER,
    TASK_ID           INTEGER identity
        primary key
        constraint TASK_LIST_EMPLOYEE_LIST_EMPLOYEE_ID_FK
            references EMPLOYEE_LIST (EMPLOYEE_ID)
);

create unique index TASK_LIST_ID_UINDEX
    on TASK_LIST (TASK_ID);


