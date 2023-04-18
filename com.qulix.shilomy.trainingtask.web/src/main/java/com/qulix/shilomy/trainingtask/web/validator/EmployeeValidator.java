package com.qulix.shilomy.trainingtask.web.validator;

import com.qulix.shilomy.trainingtask.data.param.EmployeeParam;
import com.qulix.shilomy.trainingtask.web.validator.employee.FirstNameValidator;
import com.qulix.shilomy.trainingtask.web.validator.employee.LastNameValidator;
import com.qulix.shilomy.trainingtask.web.validator.employee.PatronymicValidator;
import com.qulix.shilomy.trainingtask.web.validator.employee.PositionValidator;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Валидатор сотрудника
 */
public class EmployeeValidator {
    public static final String EDIT_EMPLOYEE = "/editEmployee";

    /**
     * Цепочка валидаторов работника
     */
    private static final ValidatorChain employeeValidatorChain = ValidatorChain.link(
            FirstNameValidator.getInstance(),
            LastNameValidator.getInstance(),
            PatronymicValidator.getInstance(),
            PositionValidator.getInstance()
    );

    private EmployeeValidator() {

    }

    /**
     * Запуск цепочки проверок сотрудника
     *
     * @param req <code>ServletRequest</code> запрос пользователя
     * @return true если все параметры проходят проверку, в остальных случаях false
     */
    public static boolean isValid(HttpServletRequest req) {
        String page = req.getRequestURI();
        if (page.equals(EDIT_EMPLOYEE)) {
            req.setAttribute(EmployeeParam.PAGE_MODE.get(), EmployeeParam.EDIT.get());
        }

        return employeeValidatorChain.check(req);
    }
}
