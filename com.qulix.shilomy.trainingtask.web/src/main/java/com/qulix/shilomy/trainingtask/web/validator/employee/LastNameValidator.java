package com.qulix.shilomy.trainingtask.web.validator.employee;

import com.qulix.shilomy.trainingtask.data.param.EmployeeParam;
import com.qulix.shilomy.trainingtask.web.validator.ValidatorChain;
import com.qulix.shilomy.trainingtask.web.validator.impl.EmptinessCheck;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Валидатор фамилии
 */
public class LastNameValidator extends ValidatorChain {
    private static final String LAST_NAME_NULL_MESSAGE = "Фамилмя не заполнена";

    /**
     * Единственный объект класса
     */
    private static LastNameValidator instance;

    /**
     * Получение объекта класса
     *
     * @return объект LastNameValidator
     */
    public static LastNameValidator getInstance() {
        if (instance == null) {
            instance = new LastNameValidator();
        }
        return instance;
    }

    private LastNameValidator() {

    }

    /**
     * Проверка фамилии
     *
     * @param req запрос
     * @return если проверка пройдена, результат следующего в цепи, иначе false
     */
    @Override
    public boolean check(HttpServletRequest req) {
        if (EmptinessCheck.isValid(req.getParameter(EmployeeParam.LAST_NAME.get()))) {
            return checkNext(req);
        }
        req.setAttribute(EmployeeParam.LAST_NAME.get() + EmployeeParam.ERROR.get(), LAST_NAME_NULL_MESSAGE);
        return false;
    }
}
