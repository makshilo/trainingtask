package com.qulix.shilomy.trainingtask.data.entity.impl;

import com.qulix.shilomy.trainingtask.data.entity.Entity;

/**
 * Проект
 */
public class ProjectEntity implements Entity {
    /**
     * Наименование
     */
    private final String name;

    /**
     * Описание
     */
    private final String description;

    /**
     * Идентификатор
     */
    private final Long id;

    /**
     * Конструктор с заполнением всех полей
     *
     * @param name        Наименование
     * @param description Описание
     * @param id          Идентификатор
     */
    public ProjectEntity(String name, String description, Long id) {
        this.name = name;
        this.description = description;
        this.id = id;
    }

    /**
     * Конструктор без идентификатора
     *
     * @param name        Наименование
     * @param description Описание
     */
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

        if (!name.equals(that.name)) return false;
        if (!description.equals(that.description)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProjectEntity{");
        sb.append("name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
