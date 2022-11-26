package com.qulix.shilomy.trainingtask.web.validator;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstant;
import com.qulix.shilomy.trainingtask.web.controller.project.ProjectFormParam;
import com.qulix.shilomy.trainingtask.web.validator.composite.ProjectComposite;

import javax.servlet.http.HttpServletRequest;

/**
 * Валидатор параметров проекта
 */
public class ProjectValidator {


    public static final String EDIT_PROJECT = "/editProject";

    private ProjectValidator() {

    }

    /**
     * Получение и проверка корректности данных из запроса.
     *
     * @param req <code>ServletRequest</code> запрос пользователя
     * @return таблица имён параметров с ошибками
     */
    public static boolean validate(HttpServletRequest req) {
        String page = req.getRequestURI();
        if (page.equals(EDIT_PROJECT)) {
            req.setAttribute(ControllerConstant.PAGE_MODE_PARAM_NAME.get(), ControllerConstant.EDIT_MODE.get());
        }

        ProjectComposite composite = new ProjectComposite(req);

        for (String param : ProjectFormParam.getStringValues()){
            String error = composite.validate(param);
            if (!error.isEmpty()) {
                req.setAttribute(param + ControllerConstant.ERROR_MESSAGES_PARAM.get(), error);
                return false;
            }
        }
        return true;
    }
}
