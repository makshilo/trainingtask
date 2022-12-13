package com.qulix.shilomy.trainingtask.web.validator.employee;

import com.qulix.shilomy.trainingtask.web.controller.employee.EmployeeParam;
import com.qulix.shilomy.trainingtask.web.validator.ValidatorChain;
import com.qulix.shilomy.trainingtask.web.validator.impl.EmptinessCheck;

import javax.servlet.http.HttpServletRequest;

public class PatronymicValidator extends ValidatorChain {
    private static final String PATRONYMIC_NULL_MESSAGE = "Отчество не заполнено";

    private static PatronymicValidator instance;

    public static PatronymicValidator getInstance() {
        if (instance == null) {
            instance = new PatronymicValidator();
        }
        return instance;
    }

    private PatronymicValidator() {

    }

    @Override
    public boolean check(HttpServletRequest req) {
        if (EmptinessCheck.isValid(req.getParameter(EmployeeParam.PATRONYMIC.get()))) {
            return checkNext(req);
        }
        req.setAttribute(EmployeeParam.PATRONYMIC.get() + EmployeeParam.ERROR.get(), PATRONYMIC_NULL_MESSAGE);
        return false;
    }
}
