package com.qulix.shilomy.trainingtask.web.validator.impl;

/**
 * Валидатор пустоты параметра
 */
public class EmptinessCheck {

    private EmptinessCheck() {

    }

    /**
     * Проверка параметра на пустоту
     * @return если параметр не пуст, пустая строка, в остальных случаях errorMessage
     */
    public static boolean isValid(String param) {
        return param != null && !param.isEmpty();
    }
}
