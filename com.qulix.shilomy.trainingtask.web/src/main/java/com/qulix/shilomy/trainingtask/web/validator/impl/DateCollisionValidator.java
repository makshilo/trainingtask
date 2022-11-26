package com.qulix.shilomy.trainingtask.web.validator.impl;

import java.sql.Date;

public class DateCollisionValidator extends CompositeValidator {
    private final String firstDate;
    private final String secondDate;
    private final String errorMessage;

    public DateCollisionValidator(String errorMessage, String firstDate, String secondDate) {
        this.firstDate = firstDate;
        this.secondDate = secondDate;
        this.errorMessage = errorMessage;
    }

    @Override
    public String isValid() {
        Date start = Date.valueOf(firstDate);
        Date end = Date.valueOf(secondDate);

        return end.after(start) || end.equals(start) ? "" : errorMessage;
    }
}
