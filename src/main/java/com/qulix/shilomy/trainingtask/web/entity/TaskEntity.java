package com.qulix.shilomy.trainingtask.web.entity;

import java.util.Date;

public class TaskEntity {
    private final boolean status;
    private final String name;
    private final String projectName;
    private final String work;
    private final Date startDate;
    private final Date endDate;
    private final String executor;
    private final Long id;

    public TaskEntity(boolean status, String name, String projectName, String work, Date startDate, Date endDate, String executor, Long id) {
        this.status = status;
        this.name = name;
        this.projectName = projectName;
        this.work = work;
        this.startDate = startDate;
        this.endDate = endDate;
        this.executor = executor;
        this.id = id;
    }

    public boolean getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getProjectName() {
        return projectName;
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

    public String getExecutor() {
        return executor;
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
        if (!projectName.equals(that.projectName)) return false;
        if (!work.equals(that.work)) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (executor != null ? !executor.equals(that.executor) : that.executor != null) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        int result = (status ? 1 : 0);
        result = 31 * result + name.hashCode();
        result = 31 * result + projectName.hashCode();
        result = 31 * result + work.hashCode();
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (executor != null ? executor.hashCode() : 0);
        result = 31 * result + id.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TaskEntity{" +
                "status=" + status +
                ", name='" + name + '\'' +
                ", projectName='" + projectName + '\'' +
                ", work='" + work + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", executor='" + executor + '\'' +
                ", id=" + id +
                '}';
    }
}
