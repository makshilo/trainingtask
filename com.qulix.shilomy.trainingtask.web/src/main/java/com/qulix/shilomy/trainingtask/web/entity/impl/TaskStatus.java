package com.qulix.shilomy.trainingtask.web.entity.impl;

public enum TaskStatus {
    NOT_STARTED("Не начата"),
    IN_PROGRESS("В процессе"),
    DONE("Завершена"),
    PAUSED("Отложена");

    private final String status;

    TaskStatus(String status) {
        this.status = status;
    }

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

    public String getStatus() {
        return status;
    }
}
