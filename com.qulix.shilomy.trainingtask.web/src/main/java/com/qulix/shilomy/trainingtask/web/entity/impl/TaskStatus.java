package com.qulix.shilomy.trainingtask.web.entity.impl;

/**
 * Перечисление возможных статусов для сущноти задачи.
 */
public enum TaskStatus {
    NOT_STARTED("Не начата"),
    IN_PROGRESS("В процессе"),
    DONE("Завершена"),
    PAUSED("Отложена");

    private final String status;

    TaskStatus(String status) {
        this.status = status;
    }

    /**
     * Метод получения статуса по имени.
     * @param name имя статуса
     * @return status
     */
    public static TaskStatus of(String name) {
        switch (name) {
            case "NOT_STARTED":
                return NOT_STARTED;
            case "IN_PROGRESS":
                return IN_PROGRESS;
            case "DONE":
                return DONE;
            case "PAUSED":
                return PAUSED;
            default:
                throw new IllegalArgumentException("Unknown task status: " + name);
        }
    }

    /**
     * Метод получения строки из объекта
     * @return поле статуса
     */
    public String getStatus() {
        return status;
    }
}
