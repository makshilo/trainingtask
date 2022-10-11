package com.qulix.shilomy.trainingtask.web.validator;

import java.time.format.DateTimeFormatter;

public interface DateValidator {

    boolean isValid(DateTimeFormatter dateFormatter, String date);
}
