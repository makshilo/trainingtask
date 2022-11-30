package com.qulix.shilomy.trainingtask.web.validator.composite;

import com.qulix.shilomy.trainingtask.web.controller.project.ProjectFormParam;
import com.qulix.shilomy.trainingtask.web.validator.Validator;
import com.qulix.shilomy.trainingtask.web.validator.impl.CompositeValidator;
import com.qulix.shilomy.trainingtask.web.validator.impl.EmptinessValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Хранилище проверок проекта
 */
public class ProjectComposite {

    // Таблица соответствия параметров и их валидаторов
    private final Map<String, Validator> projectValidators;

    private static final String NAME_NULL_MESSAGE = "Наименование проекта не заполнено";
    private static final String DESCRIPTION_NULL_MESSAGE = "Описание проекта не заполнено";

    /**
     * Конструктор с заполнением таблицы валидаторов
     * @param req запрос клиента
     */
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

    /**
     * Метод валидации
     * @param paramName имя параметра
     * @return строка ошибки или пустая строка если параметр верен
     */
    public String validate(String paramName) {
        Validator validator = projectValidators.get(paramName);

        return validator != null ? validator.isValid() : "";
    }
}
