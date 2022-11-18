package com.qulix.shilomy.trainingtask.web.controller.task;

public enum TaskFormParams {
    TASK_PARAM("task"),
    TASKS_PARAM("tasks"),
    STATUS_PARAM("status"),
    TASK_NAME("taskName"),
    WORK_PARAM("work"),
    START_YEAR_PARAM("startYear"),
    START_MONTH_PARAM("startMonth"),
    START_DAY_PARAM("startDay"),
    END_YEAR_PARAM("endYear"),
    END_MONTH_PARAM("endMonth"),
    END_DAY_PARAM("endDay"),
    EXECUTOR_PARAM("executor");

    private final String param;

    TaskFormParams(String taskParam) {
        this.param = taskParam;
    }

    public String get() {
        return param;
    }
}
