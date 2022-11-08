package com.qulix.shilomy.trainingtask.web.entity.impl;

import com.qulix.shilomy.trainingtask.web.entity.Entity;

import java.sql.Date;

/**
 * POJO класс для сущности задачи.
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

    public TaskEntity(TaskStatus status, String name, Long projectId, String work, Date startDate, Date endDate, Long executorId) {
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

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
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
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (projectId != null ? !projectId.equals(that.projectId) : that.projectId != null) return false;
        if (work != null ? !work.equals(that.work) : that.work != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (executorId != null ? !executorId.equals(that.executorId) : that.executorId != null) return false;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        int result = status != null ? status.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
        result = 31 * result + (work != null ? work.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (executorId != null ? executorId.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TaskEntity{" +
                "status=" + status +
                ", name='" + name + '\'' +
                ", projectId=" + projectId +
                ", work='" + work + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", executorId=" + executorId +
                ", id=" + id +
                '}';
    }
}
