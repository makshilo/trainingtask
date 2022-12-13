package com.qulix.shilomy.trainingtask.web.validator.project;

import com.qulix.shilomy.trainingtask.web.controller.project.ProjectParam;
import com.qulix.shilomy.trainingtask.web.validator.ValidatorChain;
import com.qulix.shilomy.trainingtask.web.validator.impl.EmptinessCheck;

import javax.servlet.http.HttpServletRequest;

public class ProjectNameValidator extends ValidatorChain {
    private static final String NAME_NULL_MESSAGE = "Наименование проекта не заполнено";

    private static ProjectNameValidator instance;

    public static ProjectNameValidator getInstance() {
        if (instance == null) {
            instance = new ProjectNameValidator();
        }
        return instance;
    }

    private ProjectNameValidator() {

    }

    @Override
    public boolean check(HttpServletRequest req) {
        if (EmptinessCheck.isValid(req.getParameter(ProjectParam.NAME.get()))) {
            return checkNext(req);
        }
        req.setAttribute(ProjectParam.NAME.get() + ProjectParam.ERROR.get(), NAME_NULL_MESSAGE);
        return false;
    }
}
