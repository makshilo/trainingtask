package com.qulix.shilomy.trainingtask.web.entity.impl;

import com.qulix.shilomy.trainingtask.web.entity.Entity;

/**
 * Класс сущности проекта
 * содержит поля:
 * <li>Наименование</li>
 * <li>Описание</li>
 * <li>Идентификатор</li>
 */
public class ProjectEntity implements Entity {
    private final String name;
    private final String description;
    private final Long id;

    /**
     * Основной конструктор
     * @param name Наименование
     * @param description Описание
     * @param id Идентификатор
     */
    public ProjectEntity(String name, String description, Long id) {
        this.name = name;
        this.description = description;
        this.id = id;
    }

    /**
     * Конструктор без идентификатора
     * @param name Наименование
     * @param description Описание
     */
    public ProjectEntity(String name, String description) {
        this(name, description, null);
    }

    /**
     * Метод получения наименования
     * @return поле наименования
     */
    public String getName() {
        return name;
    }

    /**
     * Метод получения описания
     * @return поле описания
     */
    public String getDescription() {
        return description;
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

        ProjectEntity that = (ProjectEntity) o;

        if (!name.equals(that.name)) return false;
        if (!description.equals(that.description)) return false;
        return id.equals(that.id);
    }

    /**
     * Переопределение метода создания уникального хеш кода объекта
     * @return хеш код
     */
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }

    /**
     * Переопределение метода строкового представления объекта
     * @return строка с данными объекта
     */
    @Override
    public String toString() {
        return "ProjectEntity{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                '}';
    }
}
