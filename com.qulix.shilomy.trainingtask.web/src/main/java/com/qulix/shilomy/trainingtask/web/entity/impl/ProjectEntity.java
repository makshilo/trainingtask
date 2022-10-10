package com.qulix.shilomy.trainingtask.web.entity.impl;

import com.qulix.shilomy.trainingtask.web.entity.Entity;

public class ProjectEntity implements Entity {
    private final String name;
    private final String description;
    private final Long id;

    public ProjectEntity(String name, String description, Long id) {
        this.name = name;
        this.description = description;
        this.id = id;
    }

    public ProjectEntity(String name, String description) {
        this(name, description, null);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectEntity that = (ProjectEntity) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProjectEntity{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                '}';
    }
}
