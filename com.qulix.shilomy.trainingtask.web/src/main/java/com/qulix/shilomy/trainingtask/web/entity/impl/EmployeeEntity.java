package com.qulix.shilomy.trainingtask.web.entity.impl;

import com.qulix.shilomy.trainingtask.web.entity.Entity;

/**
 * Класс сущности работника
 * содержит поля:
 * <li>имя</li>
 * <li>фамилия</li>
 * <li>отчество</li>
 * <li>должность</li>
 * <li>идентификатор</li>
 */
public class EmployeeEntity implements Entity {
    private final String firstName;
    private final String lastName;
    private final String patronymic;
    private final String position;
    private final Long id;

    /**
     * Основной конструктор
     * @param firstName имя
     * @param lastName фамилия
     * @param patronymic отчество
     * @param position должность
     * @param id идентификатор
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
     * @param firstName имя
     * @param lastName фамилия
     * @param patronymic отчество
     * @param position должность
     */
    public EmployeeEntity(String firstName, String lastName, String patronymic, String position) {
        this(firstName, lastName, patronymic, position, null);
    }

    /**
     * Метод получения имени
     * @return поле имени
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Метод получения фамилии
     * @return поле фамилии
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Метод получения отчества
     * @return поле отчества
     */
    public String getPatronymic() {
        return patronymic;
    }

    /**
     * Метод получения должности
     * @return поле должности
     */
    public String getPosition() {
        return position;
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

        EmployeeEntity that = (EmployeeEntity) o;

        if (!firstName.equals(that.firstName)) return false;
        if (!lastName.equals(that.lastName)) return false;
        if (!patronymic.equals(that.patronymic)) return false;
        if (!position.equals(that.position)) return false;
        return id.equals(that.id);
    }

    /**
     * Переопределение метода создания уникального хеш кода объекта
     * @return хеш код
     */
    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + patronymic.hashCode();
        result = 31 * result + position.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }

    /**
     * Переопределение метода строкового представления объекта
     * @return строка с данными объекта
     */
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
