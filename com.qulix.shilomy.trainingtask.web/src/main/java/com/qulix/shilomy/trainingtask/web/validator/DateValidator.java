package com.qulix.shilomy.trainingtask.web.validator;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Класс валидатора для проверки даты
 */
public class DateValidator {
    private DateValidator() {

    }

    /**
     * Метод который проверяет дату с использованием
     * передаваемого форматировщика
     * @param dateFormatter внешне передаваемый форматировщик
     * @param date проверяемая строка даты
     * @return true, если строка соответствует правилам форматировщика
     * и false если не соответствует
     */
    public static boolean isValid(DateTimeFormatter dateFormatter, String date) {
        try {
            dateFormatter.parse(date);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
