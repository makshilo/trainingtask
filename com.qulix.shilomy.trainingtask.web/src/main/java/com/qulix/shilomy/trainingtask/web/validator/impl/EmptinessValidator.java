package com.qulix.shilomy.trainingtask.web.validator.impl;

import com.qulix.shilomy.trainingtask.web.validator.Validator;

/**
 * Валидатор пустоты параметра
 */
public class EmptinessValidator implements Validator {
    private final String errorMessage;
    private final String param;

    public EmptinessValidator(String errorMessage, String param) {
        this.errorMessage = errorMessage;
        this.param = param;
    }

    /**
     * Проверка параметра на пустоту
     * @return если параметр не пуст, пустая строка, в остальных случаях errorMessage
     */
    @Override
    public String isValid() {
        return param != null && !param.isEmpty() ? "" : errorMessage;
    }
}
