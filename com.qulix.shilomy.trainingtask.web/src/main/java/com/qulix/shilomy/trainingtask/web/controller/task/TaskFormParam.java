package com.qulix.shilomy.trainingtask.web.controller.task;

import java.util.ArrayList;
import java.util.List;

public enum TaskFormParam {
    STATUS_PARAM("status"),
    TASK_NAME("taskName"),
    WORK_PARAM("work"),
    START_YEAR_PARAM("startYear"),
    START_MONTH_PARAM("startMonth"),
    START_DAY_PARAM("startDay"),
    START_DATE_PARAM("startDate"),
    END_YEAR_PARAM("endYear"),
    END_MONTH_PARAM("endMonth"),
    END_DAY_PARAM("endDay"),
    END_DATE_PARAM("endDate"),
    DATE_COLLISION("dateCollision"),
    EXECUTOR_PARAM("executor");

    private final String param;

    TaskFormParam(String taskParam) {
        this.param = taskParam;
    }

    public String get() {
        return param;
    }

    public static List<String> getStringValues() {
        List<String> list = new ArrayList<>();
        for (TaskFormParam value : TaskFormParam.values()) {
            list.add(value.get());
        }

        return list;
    }
}
