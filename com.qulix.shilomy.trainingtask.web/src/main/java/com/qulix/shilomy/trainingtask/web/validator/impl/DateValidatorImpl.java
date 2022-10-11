package com.qulix.shilomy.trainingtask.web.validator.impl;

import com.qulix.shilomy.trainingtask.web.validator.DateValidator;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
