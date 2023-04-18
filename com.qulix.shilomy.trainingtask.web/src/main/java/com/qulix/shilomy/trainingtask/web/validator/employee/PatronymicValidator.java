package com.qulix.shilomy.trainingtask.web.validator.employee;

import com.qulix.shilomy.trainingtask.data.param.EmployeeParam;
import com.qulix.shilomy.trainingtask.web.validator.ValidatorChain;
import com.qulix.shilomy.trainingtask.web.validator.impl.EmptinessCheck;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Валидатор отчества
 */
public class PatronymicValidator extends ValidatorChain {
    private static final String PATRONYMIC_NULL_MESSAGE = "Отчество не заполнено";

    /**
     * Единственный объект класса
     */
    private static PatronymicValidator instance;

    /**
     * Получение объекта класса
     *
     * @return объект PatronymicValidator
     */
    public static PatronymicValidator getInstance() {
        if (instance == null) {
            instance = new PatronymicValidator();
        }
        return instance;
    }

    private PatronymicValidator() {

    }

    /**
     * Проверка отчества
     *
     * @param req запрос
     * @return если проверка пройдена, результат следующего в цепи, иначе false
     */
    @Override
    public boolean check(HttpServletRequest req) {
        if (EmptinessCheck.isValid(req.getParameter(EmployeeParam.PATRONYMIC.get()))) {
            return checkNext(req);
        }
        req.setAttribute(EmployeeParam.PATRONYMIC.get() + EmployeeParam.ERROR.get(), PATRONYMIC_NULL_MESSAGE);
        return false;
    }
}
