package com.qulix.shilomy.trainingtask.web.validator;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstants;
import com.qulix.shilomy.trainingtask.web.controller.project.ProjectFormParams;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс валидатор который выполняет проверку параметров сущности проекта на корректность
 */
public class ProjectValidator {
    private static final String PROJECT_NAME_NULL_MESSAGE = "Наименование проекта не заполнено";
    private static final String PROJECT_DESCRIPTION_NULL_MESSAGE = "Описание проекта не заполнено";
    public static final String NAME_NULL = "nameNull";
    public static final String DESCRIPTION_NULL = "descriptionNull";

    public static final String EDIT_PROJECT = "/editProject";

    private ProjectValidator() {

    }

    /**
     * Метод который получает данные сущности проекта из запроса и проверяет их корректность.
     * @param request <code>ServletRequest</code> обЪект который хранит запрос пользователя
     * @return true если все параметры проходят проверку, в остальных случаях false
     */
    public static Map<String, String> isValid(HttpServletRequest request) {
        String page = request.getRequestURI();
        if (page.equals(EDIT_PROJECT)) {
            request.setAttribute(ControllerConstants.PAGE_MODE_PARAM_NAME.get(), ControllerConstants.EDIT_MODE.get());
        }

        String projectName = request.getParameter(ProjectFormParams.PROJECT_NAME_PARAM.get());
        String description = request.getParameter(ProjectFormParams.DESCRIPTION_PARAM.get());

        Map<String, String> errors = new HashMap<>();

        if (projectName == null || projectName.equals(ControllerConstants.EMPTY_STRING.get())) {
            errors.put(NAME_NULL, PROJECT_NAME_NULL_MESSAGE);
        }
        if (description == null || description.equals(ControllerConstants.EMPTY_STRING.get())) {
            errors.put(DESCRIPTION_NULL, PROJECT_DESCRIPTION_NULL_MESSAGE);
        }
        return errors;
    }
}
