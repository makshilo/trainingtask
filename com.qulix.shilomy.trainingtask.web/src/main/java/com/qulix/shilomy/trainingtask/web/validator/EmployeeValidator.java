package com.qulix.shilomy.trainingtask.web.validator;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstant;
import com.qulix.shilomy.trainingtask.web.controller.employee.EmployeeFormParam;
import com.qulix.shilomy.trainingtask.web.validator.composite.EmployeeComposite;

import javax.servlet.http.HttpServletRequest;

/**
 * Класс валидатор который выполняет проверку параметров сущности работника на корректность
 */
public class EmployeeValidator {
    public static final String EDIT_EMPLOYEE = "/editEmployee";

    private EmployeeValidator() {

    }

    /**
     * Метод который получает данные сущности работника из запроса и проверяет их корректность.
     * @param req <code>ServletRequest</code> обЪект который хранит запрос пользователя
     * @return true если все параметры проходят проверку, в остальных случаях false
     */
    public static boolean isValid(HttpServletRequest req) {
        String page = req.getRequestURI();
        if (page.equals(EDIT_EMPLOYEE)) {
            req.setAttribute(ControllerConstant.PAGE_MODE_PARAM_NAME.get(), ControllerConstant.EDIT_MODE.get());
        }

        EmployeeComposite composite = new EmployeeComposite(req);

        for (String param : EmployeeFormParam.getStringValues()){
            String error = composite.validate(param);
            if (!error.isEmpty()) {
                req.setAttribute(param + ControllerConstant.ERROR_MESSAGES_PARAM.get(), error);
                return false;
            }
        }
        return true;
    }
}
