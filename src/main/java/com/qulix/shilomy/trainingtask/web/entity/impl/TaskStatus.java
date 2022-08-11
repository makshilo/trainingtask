package com.qulix.shilomy.trainingtask.web.entity.impl;

import java.util.Map;

public enum TaskStatus {
    NOT_STARTED,
    IN_PROGRESS,
    DONE,
    PAUSED;

    public static final Map<TaskStatus, String> status = Map.of(
            TaskStatus.NOT_STARTED, "Не начата",
            TaskStatus.IN_PROGRESS, "Выполняется",
            TaskStatus.DONE, "Готова",
            TaskStatus.PAUSED, "Приостановлена");

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
}
