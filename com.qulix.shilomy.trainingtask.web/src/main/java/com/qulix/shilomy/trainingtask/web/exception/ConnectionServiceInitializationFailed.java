package com.qulix.shilomy.trainingtask.web.exception;

/**
 * Ошибка инициализации сервиса подключений.
 */
public class ConnectionServiceInitializationFailed extends Throwable {
    public ConnectionServiceInitializationFailed(String message, Throwable cause) {
        super(message, cause);
    }
}
