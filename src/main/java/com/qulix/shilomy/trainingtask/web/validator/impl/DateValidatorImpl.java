package com.qulix.shilomy.trainingtask.web.validator.impl;

import com.qulix.shilomy.trainingtask.web.validator.DateValidator;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidatorImpl implements DateValidator {

    private final DateTimeFormatter dateFormatter;

    public DateValidatorImpl(DateTimeFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    @Override
    public boolean isValid(String date) {
            try {
                dateFormatter.parse(date);
            } catch (DateTimeParseException e) {
                return false;
            }
            return true;
    }
}
