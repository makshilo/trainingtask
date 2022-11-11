package com.qulix.shilomy.trainingtask.web.validator;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstants;

import javax.servlet.http.HttpServletRequest;

public class EmployeeValidator {
    public static final String FIRST_NAME_NULL = "Имя не заполнено";
    public static final String LAST_NAME_NULL = "Фамилмя не заполнена";
    public static final String PATRONYMIC_NULL = "Отчество не заполнено";
    public static final String POSITION_NULL = "Должность не заполнена";

    public static final String EDIT_EMPLOYEE = "/editEmployee";

    private EmployeeValidator() {

    }

    /**
     * Метод который получает данные сущности работника из запроса и проверяет их корректность.
     * @param request <code>ServletRequest</code> обЪект который хранит запрос пользователя
     * @return true если все параметры проходят проверку, в остальных случаях false
     */
    public static boolean isValid(HttpServletRequest request) {
        String page = request.getRequestURI();
        if (page.equals(EDIT_EMPLOYEE)) {
            request.setAttribute(ControllerConstants.PAGE_MODE_PARAM_NAME, ControllerConstants.EDIT_MODE);
        }

        String firstName = request.getParameter(ControllerConstants.FIRST_NAME_PARAM_NAME);
        String lastName = request.getParameter(ControllerConstants.LAST_NAME_PARAM_NAME);
        String patronymic = request.getParameter(ControllerConstants.PATRONYMIC_PARAM_NAME);
        String position = request.getParameter(ControllerConstants.POSITION_PARAM_NAME);

        if (firstName == null || firstName.equals(ControllerConstants.EMPTY_STRING)) {
            request.setAttribute(ControllerConstants.VALIDATION_ERROR_PARAM_NAME, FIRST_NAME_NULL);
            return false;
        } else if (lastName == null || lastName.equals(ControllerConstants.EMPTY_STRING)) {
            request.setAttribute(ControllerConstants.VALIDATION_ERROR_PARAM_NAME, LAST_NAME_NULL);
            return false;
        } else if (patronymic == null || patronymic.equals(ControllerConstants.EMPTY_STRING)) {
            request.setAttribute(ControllerConstants.VALIDATION_ERROR_PARAM_NAME, PATRONYMIC_NULL);
            return false;
        } else if (position == null || position.equals(ControllerConstants.EMPTY_STRING)) {
            request.setAttribute(ControllerConstants.VALIDATION_ERROR_PARAM_NAME, POSITION_NULL);
            return false;
        } else {
            return true;
        }
    }
}
