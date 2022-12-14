package com.qulix.shilomy.trainingtask.web.validator.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Проверка по регулярному выражению
 */
public class RegexpCheck {
    private RegexpCheck() {

    }

    /**
     * Проверка параметра на соответствие регулярному выражению
     *
     * @return true если параметр соответствует, иначе false
     */
    public static boolean isValid(String param, String regexp) {
        final Pattern pattern = Pattern.compile(regexp);
        final Matcher matcher = pattern.matcher(param);

        return matcher.matches();
    }
}
