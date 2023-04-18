package com.qulix.shilomy.trainingtask.data.entity.impl;

import com.qulix.shilomy.trainingtask.data.entity.Entity;

/**
 * Сотрудник
 */
public class EmployeeEntity implements Entity {
    /**
     * Имя
     */
    private final String firstName;

    /**
     * Фамилия
     */
    private final String lastName;

    /**
     * Отчество
     */
    private final String patronymic;

    /**
     * Должность
     */
    private final String position;

    /**
     * Идентификатор
     */
    private final Long id;

    /**
     * Конструктор с заполнением всех полей
     *
     * @param firstName  имя
     * @param lastName   фамилия
     * @param patronymic отчество
     * @param position   должность
     * @param id         идентификатор
     */
    public EmployeeEntity(String firstName, String lastName, String patronymic, String position, Long id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.position = position;
        this.id = id;
    }

    /**
     * Конструктор без идентификатора
     *
     * @param firstName  имя
     * @param lastName   фамилия
     * @param patronymic отчество
     * @param position   должность
     */
    public EmployeeEntity(String firstName, String lastName, String patronymic, String position) {
        this(firstName, lastName, patronymic, position, null);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getPosition() {
        return position;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeEntity that = (EmployeeEntity) o;

        if (!firstName.equals(that.firstName)) return false;
        if (!lastName.equals(that.lastName)) return false;
        if (!patronymic.equals(that.patronymic)) return false;
        if (!position.equals(that.position)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + patronymic.hashCode();
        result = 31 * result + position.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EmployeeEntity{");
        sb.append("firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", patronymic='").append(patronymic).append('\'');
        sb.append(", position='").append(position).append('\'');
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
