package com.qulix.shilomy.trainingtask.web.entity.impl;

import com.qulix.shilomy.trainingtask.web.entity.Entity;

import java.sql.Date;

/**
 * Класс сущности задачи
 * содержит поля:
 * <li>статус</li>
 * <li>наименование</li>
 * <li>идентификатор проекта</li>
 * <li>работа</li>
 * <li>дата начала</li>
 * <li>дата окончания</li>
 * <li>идентификатор работника</li>
 * <li>идентификатор</li>
 */
public class TaskEntity implements Entity {
    private final TaskStatus status;
    private final String name;
    private final Long projectId;
    private final String work;
    private final Date startDate;
    private final Date endDate;
    private final Long executorId;
    private final Long id;

    /**
     * Основной конструктор
     * @param status статус
     * @param name наименование
     * @param projectId идентификатор проекта
     * @param work работа
     * @param startDate дата начала
     * @param endDate дата окончания
     * @param executorId идентификатор работника
     * @param id идентификатор
     */
    public TaskEntity(TaskStatus status, String name, Long projectId, String work, Date startDate, Date endDate, Long executorId, Long id) {
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
     * @param status статус
     * @param name наименование
     * @param projectId идентификатор проекта
     * @param work работа
     * @param startDate дата начала
     * @param endDate дата окончания
     * @param executorId идентификатор работника
     */
    public TaskEntity(TaskStatus status, String name, Long projectId, String work, Date startDate, Date endDate, Long executorId) {
        this(status, name, projectId, work, startDate, endDate, executorId, null);
    }

    /**
     * Метод получения статуса
     * @return поле статуса
     */
    public TaskStatus getStatus() {
        return status;
    }

    /**
     * Метод получения наименования
     * @return поле наименования
     */
    public String getName() {
        return name;
    }

    /**
     * Метод получения идентификатора проекта
     * @return поле идентификатора проекта
     */
    public Long getProjectId() {
        return projectId;
    }

    /**
     * Метод получения работы
     * @return поле работы
     */
    public String getWork() {
        return work;
    }

    /**
     * Метод получения даты начала
     * @return поле даты начала
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Метод получения даты окончания
     * @return поле даты окончания
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Метод получения идентификатора работника
     * @return поле идентификатора работника
     */
    public Long getExecutorId() {
        return executorId;
    }

    /**
     * Метод получения идентификатора
     * @return поле идентификатора
     */
    public Long getId() {
        return id;
    }

    /**
     * Переопределение метода сравнения объекта
     * @param o объект для сравнения
     * @return результат сравнения
     */
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

    /**
     * Переопределение метода создания уникального хеш кода объекта
     * @return хеш код
     */
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

    /**
     * Переопределение метода строкового представления объекта
     * @return строка с данными объекта
     */
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
