package com.qulix.shilomy.trainingtask.web.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс валидатора для проверки даты
 */
public class DateValidator {
    private final static String REGEXP = "^((\\d{4}-((0[13578]-|1[02]-)(0[1-9]|[12]\\d|3[01])|(0[13456789]-|1[012]-)(0[1-9]|[12]\\d|30)|02-(0[1-9]|1\\d|2[0-8])))|(([02468][048]|[13579][26])00|\\d{2}([13579][26]|0[48]|[2468][048]))-02-29){0,10}$";
    private DateValidator() {

    }

    /**
     * Метод который проверяет дату с использованием
     * передаваемого форматировщика
     * @param date проверяемая строка даты
     * @return true, если строка соответствует формату гггг-мм-дд
     * и false если не соответствует
     */
    public static boolean isValid(String date) {
        final Pattern pattern = Pattern.compile(REGEXP);
        final Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }
}
