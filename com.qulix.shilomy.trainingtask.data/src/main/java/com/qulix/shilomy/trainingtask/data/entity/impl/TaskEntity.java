package com.qulix.shilomy.trainingtask.data.entity.impl;

import com.qulix.shilomy.trainingtask.data.entity.Entity;

import java.time.LocalDate;

/**
 * Задача
 */
public class TaskEntity implements Entity {
    /**
     * Статус
     */
    private final TaskStatus status;

    /**
     * Наименование
     */
    private final String name;

    /**
     * Идентификатор проекта
     */
    private final Long projectId;

    /**
     * Работа
     */
    private final String work;

    /**
     * Дата начала
     */
    private final LocalDate startDate;

    /**
     * Дата окончания
     */
    private final LocalDate endDate;

    /**
     * Идентификатор исполнителя
     */
    private final Long executorId;

    /**
     * Идентификатор
     */
    private final Long id;

    /**
     * Конструктор с заполнением всех полей
     *
     * @param status     статус
     * @param name       наименование
     * @param projectId  идентификатор проекта
     * @param work       работа
     * @param startDate  дата начала
     * @param endDate    дата окончания
     * @param executorId идентификатор работника
     * @param id         идентификатор
     */
    public TaskEntity(TaskStatus status, String name, Long projectId, String work, LocalDate startDate, LocalDate endDate, Long executorId, Long id) {
        this.status = status;
        this.name = name;
        this.projectId = projectId;
        this.work = work;
        this.startDate = startDate;
        this.endDate = endDate;
        this.executorId = executorId;
        this.id = id;
    }

    /**
     * Конструктор без идентификатора
     *
     * @param status     статус
     * @param name       наименование
     * @param projectId  идентификатор проекта
     * @param work       работа
     * @param startDate  дата начала
     * @param endDate    дата окончания
     * @param executorId идентификатор работника
     */
    public TaskEntity(TaskStatus status, String name, Long projectId, String work, LocalDate startDate, LocalDate endDate, Long executorId) {
        this(status, name, projectId, work, startDate, endDate, executorId, null);
    }

    public TaskStatus getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public Long getProjectId() {
        return projectId;
    }

    public String getWork() {
        return work;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Long getExecutorId() {
        return executorId;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskEntity that = (TaskEntity) o;

        if (status != that.status) return false;
        if (!name.equals(that.name)) return false;
        if (!projectId.equals(that.projectId)) return false;
        if (!work.equals(that.work)) return false;
        if (!startDate.equals(that.startDate)) return false;
        if (!endDate.equals(that.endDate)) return false;
        if (!executorId.equals(that.executorId)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        int result = status.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + projectId.hashCode();
        result = 31 * result + work.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + executorId.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TaskEntity{");
        sb.append("status=").append(status);
        sb.append(", name='").append(name).append('\'');
        sb.append(", projectId=").append(projectId);
        sb.append(", work='").append(work).append('\'');
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", executorId=").append(executorId);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
