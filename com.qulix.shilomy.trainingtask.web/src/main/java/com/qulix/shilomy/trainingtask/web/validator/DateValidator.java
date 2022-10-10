package com.qulix.shilomy.trainingtask.web.validator;

import java.time.format.DateTimeFormatter;

public interface DateValidator {

    int isValid(String year, String month, String day);

    boolean validate(DateTimeFormatter dateFormatter, String date);
}
