package com.qulix.shilomy.trainingtask.data.param;

/**
 * Константы задачи
 */
public enum TaskParam {

    /**
     * идентификатор
     */
    ID("id"),

    /**
     * задача
     */
    TASK("task"),

    /**
     * задачи
     */
    TASKS("tasks"),

    /**
     * проект
     */
    PROJECT("project"),

    /**
     * статус
     */
    STATUS("status"),

    /**
     * имя задачи
     */
    TASK_NAME("taskName"),

    /**
     * работа задачи
     */
    WORK("work"),

    /**
     * год начала
     */
    START_YEAR("startYear"),

    /**
     * месяц начала
     */
    START_MONTH("startMonth"),

    /**
     * день начала
     */
    START_DAY("startDay"),

    /**
     * дата начала
     */
    START_DATE("startDate"),

    /**
     * год окончания
     */
    END_YEAR("endYear"),

    /**
     * месяц окончания
     */
    END_MONTH("endMonth"),

    /**
     * день окончания
     */
    END_DAY("endDay"),

    /**
     * дата окончания
     */
    END_DATE("endDate"),

    /**
     * пересечение дат
     */
    DATE_COLLISION("dateCollision"),

    /**
     * исполнитель
     */
    EXECUTOR("executor"),

    /**
     * режим страницы
     */
    PAGE_MODE("pageMode"),

    /**
     * создание
     */
    CREATE("create"),

    /**
     * изменение
     */
    EDIT("edit"),

    /**
     * ошибка
     */
    ERROR("Error"),

    /**
     * путь к списку
     */
    TASK_LIST("/tasks"),

    /**
     * путь к странице изменения
     */
    EDIT_TASK_PAGE("/jsp/editTask.jsp"),

    /**
     * текущий проект
     */
    CURRENT_PROJECT("currentProject"),

    /**
     * блокировка проекта
     */
    PROJECT_LOCK("projectLock"),

    MINUS("-");

    private final String param;

    TaskParam(String taskParam) {
        this.param = taskParam;
    }

    public String get() {
        return param;
    }
}
