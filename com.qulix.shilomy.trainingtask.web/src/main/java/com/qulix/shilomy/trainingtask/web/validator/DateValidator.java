package com.qulix.shilomy.trainingtask.web.validator;

import java.time.format.DateTimeFormatter;

/**
 * Интерфейс описывает классы проверки даты
 */
public interface DateValidator {

    boolean isValid(DateTimeFormatter dateFormatter, String date);
}
