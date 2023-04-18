package com.qulix.shilomy.trainingtask.data.param;

/**
 * Константы сотрудника
 */
public enum EmployeeParam {

    /**
     * идентификатор
     */
    ID("id"),

    /**
     * сотрудник
     */
    EMPLOYEE("employee"),

    /**
     * сотрудники
     */
    EMPLOYEES("employees"),

    /**
     * имя
     */
    FIRST_NAME("firstName"),

    /**
     * фамилия
     */
    LAST_NAME("lastName"),

    /**
     * отчество
     */
    PATRONYMIC("patronymic"),

    /**
     * должность
     */
    POSITION("position"),

    /**
     * режим страницы
     */
    PAGE_MODE("pageMode"),

    /**
     * создание
     */
    CREATE("create"),

    /**
     * изменение
     */
    EDIT("edit"),

    /**
     * ошибка
     */
    ERROR("Error"),

    /**
     * путь к списку
     */
    EMPLOYEE_LIST("/employees"),

    /**
     * путь к странице редактирования
     */
    EDIT_EMPLOYEE_PAGE("/jsp/editEmployee.jsp");

    private final String param;

    EmployeeParam(String employeeParam) {
        this.param = employeeParam;
    }

    public String get() {
        return param;
    }
}
