package com.qulix.shilomy.trainingtask.web.validator;

import com.qulix.shilomy.trainingtask.web.controller.PageConstant;
import com.qulix.shilomy.trainingtask.web.controller.employee.EmployeeParam;
import com.qulix.shilomy.trainingtask.web.validator.composite.EmployeeComposite;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Валидатор сотрудника
 */
public class EmployeeValidator {
    public static final String EDIT_EMPLOYEE = "/editEmployee";

    private EmployeeValidator() {

    }

    /**
     * Проверка параметров и добавление ошибок на страницу
     * @param req <code>ServletRequest</code> запрос пользователя
     * @return true если все параметры проходят проверку, в остальных случаях false
     */
    public static boolean isValid(HttpServletRequest req) {
        String page = req.getRequestURI();
        if (page.equals(EDIT_EMPLOYEE)) {
            req.setAttribute(PageConstant.PAGE_MODE.get(), PageConstant.EDIT.get());
        }

        EmployeeComposite composite = new EmployeeComposite(req);

        for (String param : Stream.of(EmployeeParam.values()).map(EmployeeParam::get).collect(Collectors.toList())){
            String error = composite.validate(param);
            if (!error.isEmpty()) {
                req.setAttribute(param + PageConstant.ERROR.get(), error);
                return false;
            }
        }
        return true;
    }
}
