package com.qulix.shilomy.trainingtask.web.validator.composite;

import com.qulix.shilomy.trainingtask.web.controller.employee.EmployeeFormParam;
import com.qulix.shilomy.trainingtask.web.validator.Validator;
import com.qulix.shilomy.trainingtask.web.validator.impl.CompositeValidator;
import com.qulix.shilomy.trainingtask.web.validator.impl.EmptinessValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Хранилище проверок сотрудника
 */
public class EmployeeComposite {

    // Таблица соответствия параметров и их валидаторов
    private final Map<String, Validator> employeeValidators;

    public static final String FIRST_NAME_NULL_MESSAGE = "Имя не заполнено";
    public static final String LAST_NAME_NULL_MESSAGE = "Фамилмя не заполнена";
    public static final String PATRONYMIC_NULL_MESSAGE = "Отчество не заполнено";
    public static final String POSITION_NULL_MESSAGE = "Должность не заполнена";

    /**
     * Конструктор с заполнением таблицы валидаторов
     * @param req запрос клиента
     */
    public EmployeeComposite(HttpServletRequest req) {
        employeeValidators = Map.of(

                EmployeeFormParam.EMPLOYEE_FIRST_NAME.get(), new CompositeValidator(
                        new EmptinessValidator(FIRST_NAME_NULL_MESSAGE, req.getParameter(EmployeeFormParam.EMPLOYEE_FIRST_NAME.get()))
                ),

                EmployeeFormParam.EMPLOYEE_LAST_NAME.get(), new CompositeValidator(
                        new EmptinessValidator(LAST_NAME_NULL_MESSAGE, req.getParameter(EmployeeFormParam.EMPLOYEE_LAST_NAME.get()))
                ),

                EmployeeFormParam.PATRONYMIC_PARAM.get(), new CompositeValidator(
                        new EmptinessValidator(PATRONYMIC_NULL_MESSAGE, req.getParameter(EmployeeFormParam.PATRONYMIC_PARAM.get()))
                ),

                EmployeeFormParam.POSITION_PARAM.get(), new CompositeValidator(
                        new EmptinessValidator(POSITION_NULL_MESSAGE, req.getParameter(EmployeeFormParam.POSITION_PARAM.get()))
                )
        );
    }

    /**
     * Метод валидации
     * @param paramName имя параметра
     * @return строка ошибки или пустая строка если параметр верен
     */
    public String validate(String paramName) {
        Validator validator = employeeValidators.get(paramName);

        return validator != null ? validator.isValid() : "";
    }
}
