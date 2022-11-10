package com.qulix.shilomy.trainingtask.web.controller;

public class ControllerConstants {
    //Общие константы
    public static final String ID_PARAM_NAME = "id";
    public static final String MINUS_SIGN = "-";
    public static final String SPACE_SIGN = " ";
    public static final String EMPTY_STRING = "";

    //Параметры сущности работника
    public static final String EMPLOYEE_PARAM_NAME = "employee";
    public static final String EMPLOYEES_PARAM_NAME = "employees";
    public static final String FIRST_NAME_PARAM_NAME = "firstName";
    public static final String LAST_NAME_PARAM_NAME = "lastName";
    public static final String PATRONYMIC_PARAM_NAME = "patronymic";
    public static final String POSITION_PARAM_NAME = "position";
    public static final String COMMAND_EMPLOYEE_LIST = "/employees";

    //Параметры сущности проекта
    public static final String PROJECTS_PARAM_NAME = "projects";
    public static final String PROJECT_NAME_PARAM = "projectName";
    public static final String DESCRIPTION_PARAM_NAME = "description";
    public static final String COMMAND_PROJECT_LIST = "/projects";

    //Параметры сущности задачи
    public static final String TASK_PARAM_NAME = "task";
    public static final String TASKS_PARAM_NAME = "tasks";
    public static final String STATUS_PARAM_NAME = "status";
    public static final String TASK_NAME_PARAM = "taskName";
    public static final String PROJECT_PARAM_NAME = "project";
    public static final String WORK_PARAM_NAME = "work";
    public static final String START_YEAR_PARAM_NAME = "startYear";
    public static final String START_MONTH_PARAM_NAME = "startMonth";
    public static final String START_DAY_PARAM_NAME = "startDay";
    public static final String END_YEAR_PARAM_NAME = "endYear";
    public static final String END_MONTH_PARAM_NAME = "endMonth";
    public static final String END_DAY_PARAM_NAME = "endDay";
    public static final String EXECUTOR_PARAM_NAME = "executor";
    public static final String CURRENT_PROJECT_PARAM_NAME = "currentProject";
    public static final String PROJECT_LOCK_PARAM = "projectLock";
    public static final String COMMAND_TASK_LIST = "/tasks";


    public static final String PAGE_MODE_PARAM_NAME = "pageMode";
    public static final String CREATE_MODE = "create";
    public static final String EDIT_MODE = "edit";

    public static final String EMPLOYEES_PAGE = "/jsp/employeeList.jsp";
    public static final String EDIT_EMPLOYEE_PAGE = "/jsp/editEmployee.jsp";

    public static final String PROJECT_LIST_PAGE = "/jsp/projectList.jsp";
    public static final String EDIT_PROJECT_PAGE = "/jsp/editProject.jsp";

    public static final String TASK_LIST_PAGE = "/jsp/taskList.jsp";
    public static final String EDIT_TASK_PAGE = "/jsp/editTask.jsp";

    public static final String VALIDATION_ERROR_PARAM_NAME = "validationError";
}
