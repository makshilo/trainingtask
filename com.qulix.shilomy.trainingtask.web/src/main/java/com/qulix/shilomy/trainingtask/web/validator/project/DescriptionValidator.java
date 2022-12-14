package com.qulix.shilomy.trainingtask.web.validator.project;

import com.qulix.shilomy.trainingtask.web.controller.project.ProjectParam;
import com.qulix.shilomy.trainingtask.web.validator.ValidatorChain;
import com.qulix.shilomy.trainingtask.web.validator.impl.EmptinessCheck;

import javax.servlet.http.HttpServletRequest;

/**
 * Валидатор описания
 */
public class DescriptionValidator extends ValidatorChain {
    private static final String DESCRIPTION_NULL_MESSAGE = "Описание проекта не заполнено";

    /**
     * Единственный объект класса
     */
    private static DescriptionValidator instance;

    /**
     * Получение объекта класса
     *
     * @return объект DescriptionValidator
     */
    public static DescriptionValidator getInstance() {
        if (instance == null) {
            instance = new DescriptionValidator();
        }
        return instance;
    }

    private DescriptionValidator() {

    }

    /**
     * Проверка описания
     *
     * @param req запрос
     * @return если проверка пройдена, результат следующего в цепи, иначе false
     */
    @Override
    public boolean check(HttpServletRequest req) {
        if (EmptinessCheck.isValid(req.getParameter(ProjectParam.DESCRIPTION.get()))) {
            return checkNext(req);
        }
        req.setAttribute(ProjectParam.DESCRIPTION.get() + ProjectParam.ERROR.get(), DESCRIPTION_NULL_MESSAGE);
        return false;
    }
}
