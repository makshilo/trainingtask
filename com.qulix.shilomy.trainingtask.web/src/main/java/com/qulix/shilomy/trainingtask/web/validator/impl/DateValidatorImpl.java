package com.qulix.shilomy.trainingtask.web.validator.impl;

import com.qulix.shilomy.trainingtask.web.validator.DateValidator;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class DateValidatorImpl implements DateValidator {

    public static final String DATE_FORMAT = "uuuu-MM-dd";
    public static final String YEAR_FORMAT = "uuuu";
    public static final String DAY_FORMAT = "dd";

    public DateValidatorImpl() {

    }

    @Override
    public int isValid(String year, String month, String day) {
        String date = year + "-" + month + "-" + day;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT).withResolverStyle(ResolverStyle.STRICT);
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern(YEAR_FORMAT).withResolverStyle(ResolverStyle.STRICT);
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern(DAY_FORMAT).withResolverStyle(ResolverStyle.STRICT);
        if (!validate(dateFormatter, date)) {
            if (validate(yearFormatter, year)) {
                if (validate(dayFormatter, day)) {
                    return 4; // такой даты не существует
                } else {
                    return 2; // ошибка в дне
                }
            } else {
                if (validate(dayFormatter, day)) {
                    return 1; // ошибка в году
                } else {
                    return 3; // Ошибка в году и в дне
                }
            }
        } else {
            return 0; // дата верна
        }
    }

    @Override
    public boolean validate(DateTimeFormatter dateFormatter, String date) {
        try {
            dateFormatter.parse(date);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
