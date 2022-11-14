package com.qulix.shilomy.trainingtask.web.exception;

/**
 * Ошибка получения сущности из результирующего множества
 */
public class EntityExtractionFailedException extends Exception {
    public EntityExtractionFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
