package com.qulix.shilomy.trainingtask.web.validator.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Валидатор по регулярному выражению
 */
public class RegexpCheck {
    private RegexpCheck() {

    }

    /**
     * Проверка параметра на соответствие регулярному выражению
     *
     * @return если параметр соответствует регулярному выражению пустая строка,
     * в остальных случаях errorMessage
     */
    public static boolean isValid(String param, String regexp) {
        final Pattern pattern = Pattern.compile(regexp);
        final Matcher matcher = pattern.matcher(param);

        return matcher.matches();
    }
}
