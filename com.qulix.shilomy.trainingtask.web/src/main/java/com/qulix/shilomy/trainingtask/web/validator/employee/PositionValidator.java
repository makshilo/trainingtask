package com.qulix.shilomy.trainingtask.web.validator.employee;

import com.qulix.shilomy.trainingtask.data.param.EmployeeParam;
import com.qulix.shilomy.trainingtask.web.validator.ValidatorChain;
import com.qulix.shilomy.trainingtask.web.validator.impl.EmptinessCheck;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Валидатор должности
 */
public class PositionValidator extends ValidatorChain {
    private static final String POSITION_NULL_MESSAGE = "Должность не заполнена";

    /**
     * Единственный объект класса
     */
    private static PositionValidator instance;

    /**
     * Получение объекта класса
     *
     * @return объект PositionValidator
     */
    public static PositionValidator getInstance() {
        if (instance == null) {
            instance = new PositionValidator();
        }
        return instance;
    }

    private PositionValidator() {

    }

    /**
     * Проверка должности
     *
     * @param req запрос
     * @return если проверка пройдена, результат следующего в цепи, иначе false
     */
    @Override
    public boolean check(HttpServletRequest req) {
        if (EmptinessCheck.isValid(req.getParameter(EmployeeParam.POSITION.get()))) {
            return checkNext(req);
        }
        req.setAttribute(EmployeeParam.POSITION.get() + EmployeeParam.ERROR.get(), POSITION_NULL_MESSAGE);
        return false;
    }
}
