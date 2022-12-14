package com.qulix.shilomy.trainingtask.web.validator.impl;

/**
 * Проверка пустоты параметра
 */
public class EmptinessCheck {

    private EmptinessCheck() {

    }

    /**
     * Проверка параметра на пустоту
     *
     * @return если параметр не пуст true, иначе false
     */
    public static boolean isValid(String param) {
        return param != null && !param.isEmpty();
    }
}
