package com.qulix.shilomy.trainingtask.web.validator.employee;

import com.qulix.shilomy.trainingtask.data.param.EmployeeParam;
import com.qulix.shilomy.trainingtask.web.validator.ValidatorChain;
import com.qulix.shilomy.trainingtask.web.validator.impl.EmptinessCheck;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Валидатор имени
 */
public class FirstNameValidator extends ValidatorChain {
    private static final String FIRST_NAME_NULL_MESSAGE = "Имя не заполнено";

    /**
     * Единственный объект класса
     */
    private static FirstNameValidator instance;

    /**
     * Получение объекта класса
     *
     * @return объект FirstNameValidator
     */
    public static FirstNameValidator getInstance() {
        if (instance == null) {
            instance = new FirstNameValidator();
        }
        return instance;
    }

    private FirstNameValidator() {

    }

    /**
     * Проверка имени
     *
     * @param req запрос
     * @return если проверка пройдена, результат следующего в цепи, иначе false
     */
    @Override
    public boolean check(HttpServletRequest req) {
        if (EmptinessCheck.isValid(req.getParameter(EmployeeParam.FIRST_NAME.get()))) {
            return checkNext(req);
        }
        req.setAttribute(EmployeeParam.FIRST_NAME.get() + EmployeeParam.ERROR.get(), FIRST_NAME_NULL_MESSAGE);
        return false;
    }
}
