package com.qulix.shilomy.trainingtask.web.validator.impl;

import java.sql.Date;

/**
 * Валидатор пересечения дат
 */
public class DateCollisionCheck {

    private DateCollisionCheck() {

    }

    /**
     * Проверка пересечения дат
     *
     * @return если первая дата до второй или равна ей пустая строка, в остальных случаях errorMessage
     */
    public static boolean isValid(String firstDate, String secondDate) {
        Date start = Date.valueOf(firstDate);
        Date end = Date.valueOf(secondDate);

        return end.after(start) || end.equals(start);
    }
}
