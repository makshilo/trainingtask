package com.qulix.shilomy.trainingtask.web.validator.impl;

import com.qulix.shilomy.trainingtask.web.validator.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Валидатор по регулярному выражению
 */
public class RegexpValidator implements Validator {
    private final String errorMessage;
    private final String param;
    private final String regexp;

    public RegexpValidator(String errorMessage, String param, String regexp) {
        this.errorMessage = errorMessage;
        this.param = param;
        this.regexp = regexp;
    }

    /**
     * Проверка параметра на соответствие регулярному выражению
     * @return если параметр соответствует регулярному выражению пустая строка,
     * в остальных случаях errorMessage
     */
    @Override
    public String isValid() {
        final Pattern pattern = Pattern.compile(regexp);
        final Matcher matcher = pattern.matcher(param);

        return matcher.matches() ? "" : errorMessage;
    }
}
