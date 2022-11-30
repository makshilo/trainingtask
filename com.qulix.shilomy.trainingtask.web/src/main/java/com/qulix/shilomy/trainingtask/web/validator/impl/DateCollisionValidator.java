package com.qulix.shilomy.trainingtask.web.validator.impl;

import com.qulix.shilomy.trainingtask.web.validator.Validator;

import java.sql.Date;

/**
 * Валидатор пересечения дат
 */
public class DateCollisionValidator implements Validator {
    private final String firstDate;
    private final String secondDate;
    private final String errorMessage;

    public DateCollisionValidator(String errorMessage, String firstDate, String secondDate) {
        this.firstDate = firstDate;
        this.secondDate = secondDate;
        this.errorMessage = errorMessage;
    }

    /**
     * Проверка пересечения дат
     * @return если первая дата до второй или равна ей пустая строка, в остальных случаях errorMessage
     */
    @Override
    public String isValid() {
        Date start = Date.valueOf(firstDate);
        Date end = Date.valueOf(secondDate);

        return end.after(start) || end.equals(start) ? "" : errorMessage;
    }
}
