package com.qulix.shilomy.trainingtask.web.validator;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstants;

import javax.servlet.http.HttpServletRequest;

public class ProjectValidator {
    private static final String PROJECT_NAME_NULL = "Наименование проекта не заполнено";
    private static final String PROJECT_DESCRIPTION_NULL = "Описание проекта не заполнено";
    public static final String EDIT_PROJECT = "/editProject";

    private ProjectValidator() {

    }

    /**
     * Метод который получает данные сущности проекта из запроса и проверяет их корректность.
     * @param request <code>ServletRequest</code> обЪект который хранит запрос пользователя
     * @return true если все параметры проходят проверку, в остальных случаях false
     */
    public static boolean isValid(HttpServletRequest request) {
        String page = request.getRequestURI();
        if (page.equals(EDIT_PROJECT)) {
            request.setAttribute(ControllerConstants.PAGE_MODE_PARAM_NAME, ControllerConstants.EDIT_MODE);
        }

        String projectName = request.getParameter(ControllerConstants.PROJECT_NAME_PARAM);
        String description = request.getParameter(ControllerConstants.DESCRIPTION_PARAM_NAME);

        if (projectName == null || projectName.equals(ControllerConstants.EMPTY_STRING)) {
            request.setAttribute(ControllerConstants.VALIDATION_ERROR_PARAM_NAME, PROJECT_NAME_NULL);
            return false;
        } else if (description == null || description.equals(ControllerConstants.EMPTY_STRING)) {
            request.setAttribute(ControllerConstants.VALIDATION_ERROR_PARAM_NAME, PROJECT_DESCRIPTION_NULL);
            return false;
        } else {
            return true;
        }
    }
}
