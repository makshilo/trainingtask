package com.qulix.shilomy.trainingtask.web.validator.employee;

import com.qulix.shilomy.trainingtask.web.controller.employee.EmployeeParam;
import com.qulix.shilomy.trainingtask.web.validator.ValidatorChain;
import com.qulix.shilomy.trainingtask.web.validator.impl.EmptinessCheck;

import javax.servlet.http.HttpServletRequest;

public class LastNameValidator extends ValidatorChain {
    private static final String LAST_NAME_NULL_MESSAGE = "Фамилмя не заполнена";

    private static LastNameValidator instance;

    public static LastNameValidator getInstance() {
        if (instance == null) {
            instance = new LastNameValidator();
        }
        return instance;
    }

    private LastNameValidator() {

    }

    @Override
    public boolean check(HttpServletRequest req) {
        if (EmptinessCheck.isValid(req.getParameter(EmployeeParam.LAST_NAME.get()))) {
            return checkNext(req);
        }
        req.setAttribute(EmployeeParam.LAST_NAME.get() + EmployeeParam.ERROR.get(), LAST_NAME_NULL_MESSAGE);
        return false;
    }
}
