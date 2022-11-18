package com.qulix.shilomy.trainingtask.web.validator;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstants;
import com.qulix.shilomy.trainingtask.web.controller.employee.EmployeeFormParams;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс валидатор который выполняет проверку параметров сущности работника на корректность
 */
public class EmployeeValidator {
    public static final String FIRST_NAME_NULL_MESSAGE = "Имя не заполнено";
    public static final String LAST_NAME_NULL_MESSAGE = "Фамилмя не заполнена";
    public static final String PATRONYMIC_NULL_MESSAGE = "Отчество не заполнено";
    public static final String POSITION_NULL_MESSAGE = "Должность не заполнена";

    public static final String FIRST_NAME_NULL = "firstNameNull";
    public static final String LAST_NAME_NULL = "lastNameNull";
    public static final String PATRONYMIC_NULL = "patronymicNull";
    public static final String POSITION_NULL = "positionNull";

    public static final String EDIT_EMPLOYEE = "/editEmployee";

    private EmployeeValidator() {

    }

    /**
     * Метод который получает данные сущности работника из запроса и проверяет их корректность.
     * @param request <code>ServletRequest</code> обЪект который хранит запрос пользователя
     * @return true если все параметры проходят проверку, в остальных случаях false
     */
    public static Map<String, String> isValid(HttpServletRequest request) {
        String page = request.getRequestURI();
        if (page.equals(EDIT_EMPLOYEE)) {
            request.setAttribute(ControllerConstants.PAGE_MODE_PARAM_NAME.get(), ControllerConstants.EDIT_MODE.get());
        }

        String firstName = request.getParameter(EmployeeFormParams.EMPLOYEE_FIRST_NAME.get());
        String lastName = request.getParameter(EmployeeFormParams.EMPLOYEE_LAST_NAME.get());
        String patronymic = request.getParameter(EmployeeFormParams.PATRONYMIC_PARAM.get());
        String position = request.getParameter(EmployeeFormParams.POSITION_PARAM.get());

        Map<String, String> errors = new HashMap<>();

        if (firstName == null || firstName.equals(ControllerConstants.EMPTY_STRING.get())) {
            errors.put(FIRST_NAME_NULL, FIRST_NAME_NULL_MESSAGE);
        }
        if (lastName == null || lastName.equals(ControllerConstants.EMPTY_STRING.get())) {
            errors.put(LAST_NAME_NULL, LAST_NAME_NULL_MESSAGE);
        }
        if (patronymic == null || patronymic.equals(ControllerConstants.EMPTY_STRING.get())) {
            errors.put(PATRONYMIC_NULL, PATRONYMIC_NULL_MESSAGE);
        }
        if (position == null || position.equals(ControllerConstants.EMPTY_STRING.get())) {
            errors.put(POSITION_NULL, POSITION_NULL_MESSAGE);
        }

        return errors;
    }
}
