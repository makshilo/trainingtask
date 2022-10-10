package com.qulix.shilomy.trainingtask.web.entity.impl;

import com.qulix.shilomy.trainingtask.web.entity.Entity;

public class EmployeeEntity implements Entity {
    private final String firstName;
    private final String lastName;
    private final String patronymic;
    private final String position;
    private final Long id;

    public EmployeeEntity(String firstName, String lastName, String patronymic, String position, Long id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.position = position;
        this.id = id;
    }

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
        return "EmployeeEntity{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", position='" + position + '\'' +
                ", id=" + id +
                '}';
    }
}
