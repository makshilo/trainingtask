package com.qulix.shilomy.trainingtask.data.exception;

/**
 * Ошибка инициализации сервиса подключений.
 */
public class ConnectionServiceInitializationFailed extends Throwable {
    public ConnectionServiceInitializationFailed(String message, Throwable cause) {
        super(message, cause);
    }
}
