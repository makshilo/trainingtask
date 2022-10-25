package com.qulix.shilomy.trainingtask.web.validator.impl;

import com.qulix.shilomy.trainingtask.web.validator.DateValidator;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Класс исполнение валидатора для проверки даты, является синглтоном
 */
public class DateValidatorImpl implements DateValidator {

    private static DateValidatorImpl instance;

    private DateValidatorImpl() {

    }

    public static synchronized DateValidatorImpl getInstance() {
        if (instance == null) {
            instance = new DateValidatorImpl();
        }
        return instance;
    }

    /**
     * Метод который проверяет дату с использованием
     * передаваемого форматировщика
     * @param dateFormatter внешне передаваемый форматировщик
     * @param date проверяемая строка даты
     * @return true, если строка соответствует правилам форматировщика
     * и false если не соответствует
     */
    @Override
    public boolean isValid(DateTimeFormatter dateFormatter, String date) {
        try {
            dateFormatter.parse(date);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
