package com.qulix.shilomy.trainingtask.web.validator.employee;

import com.qulix.shilomy.trainingtask.web.controller.employee.EmployeeParam;
import com.qulix.shilomy.trainingtask.web.validator.ValidatorChain;
import com.qulix.shilomy.trainingtask.web.validator.impl.EmptinessCheck;

import javax.servlet.http.HttpServletRequest;

public class FirstNameValidator extends ValidatorChain {
    private static final String FIRST_NAME_NULL_MESSAGE = "Имя не заполнено";

    private static FirstNameValidator instance;

    public static FirstNameValidator getInstance() {
        if (instance == null) {
            instance = new FirstNameValidator();
        }
        return instance;
    }

    private FirstNameValidator() {

    }

    @Override
    public boolean check(HttpServletRequest req) {
        if (EmptinessCheck.isValid(req.getParameter(EmployeeParam.FIRST_NAME.get()))) {
            return checkNext(req);
        }
        req.setAttribute(EmployeeParam.FIRST_NAME.get() + EmployeeParam.ERROR.get(), FIRST_NAME_NULL_MESSAGE);
        return false;
    }
}
