package com.qulix.shilomy.trainingtask.web.controller.employee;

public enum EmployeeFormParams {
    EMPLOYEE_PARAM("employee"),
    EMPLOYEES_PARAM("employees"),
    EMPLOYEE_FIRST_NAME("firstName"),
    EMPLOYEE_LAST_NAME("lastName"),
    PATRONYMIC_PARAM("patronymic"),
    POSITION_PARAM("position");

    private final String param;

    EmployeeFormParams(String employeeParam) {
        this.param = employeeParam;
    }

    public String get() {
        return param;
    }
}
