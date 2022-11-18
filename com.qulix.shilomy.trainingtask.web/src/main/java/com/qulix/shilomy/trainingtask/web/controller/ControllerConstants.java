package com.qulix.shilomy.trainingtask.web.controller;

/**
 * Класс который хранит константы для использования в пакете контроллеров
 */
public enum ControllerConstants {
    //Общие константы
    MINUS_SIGN("-"),
    SPACE_SIGN(" "),
    EMPTY_STRING(""),
    ID_PARAM("id"),
    PAGE_MODE_PARAM_NAME("pageMode"),
    CREATE_MODE("create"),
    EDIT_MODE("edit"),
    CURRENT_PROJECT_PARAM("currentProject"),
    PROJECT_LOCK_PARAM("projectLock"),

    PROJECT_LIST("/projects"),
    EDIT_PROJECT_PAGE("/jsp/editProject.jsp"),
    EMPLOYEE_LIST("/employees"),
    EDIT_EMPLOYEE_PAGE("/jsp/editEmployee.jsp"),

    TASK_LIST("/tasks"),
    EDIT_TASK_PAGE("/jsp/editTask.jsp"),

    ERROR_MESSAGES_PARAM("errorMessages");

    private final String param;

    ControllerConstants(String controllerParam) {
        this.param = controllerParam;
    }

    public String get() {
        return param;
    }
}
