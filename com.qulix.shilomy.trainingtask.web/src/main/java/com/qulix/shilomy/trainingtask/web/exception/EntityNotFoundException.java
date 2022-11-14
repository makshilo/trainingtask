package com.qulix.shilomy.trainingtask.web.exception;

/**
 * Ошибка поиска сущности(сущность не найдена)
 */
public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(String message) {
            super(message);
        }

}
