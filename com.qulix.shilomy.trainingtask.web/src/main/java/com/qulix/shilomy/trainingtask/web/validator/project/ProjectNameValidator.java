package com.qulix.shilomy.trainingtask.web.validator.project;

import com.qulix.shilomy.trainingtask.data.param.ProjectParam;
import com.qulix.shilomy.trainingtask.web.validator.ValidatorChain;
import com.qulix.shilomy.trainingtask.web.validator.impl.EmptinessCheck;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Валидатор наименования проекта
 */
public class ProjectNameValidator extends ValidatorChain {
    private static final String NAME_NULL_MESSAGE = "Наименование проекта не заполнено";

    /**
     * Единственный объект класса
     */
    private static ProjectNameValidator instance;

    /**
     * Получение объекта класса
     *
     * @return объект ProjectNameValidator
     */
    public static ProjectNameValidator getInstance() {
        if (instance == null) {
            instance = new ProjectNameValidator();
        }
        return instance;
    }

    private ProjectNameValidator() {

    }

    /**
     * Проверка наименования проекта
     *
     * @param req запрос
     * @return если проверка пройдена, результат следующего в цепи, иначе false
     */
    @Override
    public boolean check(HttpServletRequest req) {
        if (EmptinessCheck.isValid(req.getParameter(ProjectParam.NAME.get()))) {
            return checkNext(req);
        }
        req.setAttribute(ProjectParam.NAME.get() + ProjectParam.ERROR.get(), NAME_NULL_MESSAGE);
        return false;
    }
}
