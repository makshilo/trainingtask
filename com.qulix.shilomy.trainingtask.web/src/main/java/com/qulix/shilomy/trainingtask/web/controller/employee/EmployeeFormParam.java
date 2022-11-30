package com.qulix.shilomy.trainingtask.web.controller.employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Хранилище параметров формы сотрудника
 */
public enum EmployeeFormParam {
    EMPLOYEE_FIRST_NAME("firstName"),
    EMPLOYEE_LAST_NAME("lastName"),
    PATRONYMIC_PARAM("patronymic"),
    POSITION_PARAM("position");

    private final String param;

    EmployeeFormParam(String employeeParam) {
        this.param = employeeParam;
    }

    public String get() {
        return param;
    }

    public static List<String> getStringValues() {
        List<String> list = new ArrayList<>();
        for (EmployeeFormParam value :
                EmployeeFormParam.values()) {
            list.add(value.get());
        }
        return list;
    }
}
