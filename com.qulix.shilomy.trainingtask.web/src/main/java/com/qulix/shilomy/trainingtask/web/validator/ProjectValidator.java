package com.qulix.shilomy.trainingtask.web.validator;

import com.qulix.shilomy.trainingtask.web.controller.project.ProjectParam;
import com.qulix.shilomy.trainingtask.web.validator.project.DescriptionValidator;
import com.qulix.shilomy.trainingtask.web.validator.project.ProjectNameValidator;

import javax.servlet.http.HttpServletRequest;

/**
 * Валидатор проекта
 */
public class ProjectValidator {

    public static final String EDIT_PROJECT = "/editProject";

    /**
     * Цепочка валидаторов проекта
     */
    private static final ValidatorChain projectValidatorChain = ValidatorChain.link(
            ProjectNameValidator.getInstance(),
            DescriptionValidator.getInstance()
    );

    private ProjectValidator() {

    }

    /**
     * Запуск цепочки проверок проекта
     *
     * @param req <code>ServletRequest</code> запрос пользователя
     * @return true когда все параметры корректны, в остальных случаях false
     */
    public static boolean validate(HttpServletRequest req) {
        String page = req.getRequestURI();
        if (page.equals(EDIT_PROJECT)) {
            req.setAttribute(ProjectParam.PAGE_MODE.get(), ProjectParam.EDIT.get());
        }

        return projectValidatorChain.check(req);
    }
}
