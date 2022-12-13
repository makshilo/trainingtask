package com.qulix.shilomy.trainingtask.web.validator.employee;

import com.qulix.shilomy.trainingtask.web.controller.employee.EmployeeParam;
import com.qulix.shilomy.trainingtask.web.validator.ValidatorChain;
import com.qulix.shilomy.trainingtask.web.validator.impl.EmptinessCheck;

import javax.servlet.http.HttpServletRequest;

public class PositionValidator extends ValidatorChain {
    private static final String POSITION_NULL_MESSAGE = "Должность не заполнена";

    private static PositionValidator instance;

    public static PositionValidator getInstance() {
        if (instance == null) {
            instance = new PositionValidator();
        }
        return instance;
    }

    private PositionValidator() {

    }

    @Override
    public boolean check(HttpServletRequest req) {
        if (EmptinessCheck.isValid(req.getParameter(EmployeeParam.POSITION.get()))) {
            return checkNext(req);
        }
        req.setAttribute(EmployeeParam.POSITION.get() + EmployeeParam.ERROR.get(), POSITION_NULL_MESSAGE);
        return false;
    }
}
