package com.qulix.shilomy.trainingtask.data.exception;

/**
 * Ошибка доступа в базу данных
 */
public class DatabaseAccessException extends Throwable {
    public DatabaseAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
