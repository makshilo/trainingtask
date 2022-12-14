package com.qulix.shilomy.trainingtask.web.exception;

/**
 * Ошибка доступа в базу данных
 */
public class DatabaseAccessException extends Throwable {
    public DatabaseAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
