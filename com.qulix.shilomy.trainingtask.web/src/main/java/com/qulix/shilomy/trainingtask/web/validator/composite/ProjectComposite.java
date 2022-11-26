package com.qulix.shilomy.trainingtask.web.validator.composite;

import com.qulix.shilomy.trainingtask.web.controller.project.ProjectFormParam;
import com.qulix.shilomy.trainingtask.web.validator.Validator;
import com.qulix.shilomy.trainingtask.web.validator.impl.CompositeValidator;
import com.qulix.shilomy.trainingtask.web.validator.impl.EmptinessValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ProjectComposite {
    private static final String NAME_NULL_MESSAGE = "Наименование проекта не заполнено";
    private static final String DESCRIPTION_NULL_MESSAGE = "Описание проекта не заполнено";


    private final Map<String, Validator> projectValidators;

    public ProjectComposite(HttpServletRequest req) {
        projectValidators = Map.of(

                ProjectFormParam.PROJECT_NAME_PARAM.get(), new CompositeValidator(
                        new EmptinessValidator(NAME_NULL_MESSAGE, req.getParameter(ProjectFormParam.PROJECT_NAME_PARAM.get()))
                ),

                ProjectFormParam.DESCRIPTION_PARAM.get(), new CompositeValidator(
                        new EmptinessValidator(DESCRIPTION_NULL_MESSAGE, req.getParameter(ProjectFormParam.DESCRIPTION_PARAM.get()))
                )
        );
    }

    public String validate(String paramName) {
        Validator validator = projectValidators.get(paramName);

        return validator != null ? validator.isValid() : "";
    }
}
