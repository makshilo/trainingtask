package com.qulix.shilomy.trainingtask.web.validator;

import java.time.format.DateTimeFormatter;

/**
 * Интерфейс описывает классы проверки даты
 */
public interface DateValidator {
    /**
     * Описание метода проверки даты с помощью считывателя даты.
     * @param dateFormatter проверяющий считыватель
     * @param date проверяемая строка
     * @return true если строка содержит корректную дату, false если нет
     */
    boolean isValid(DateTimeFormatter dateFormatter, String date);
}
