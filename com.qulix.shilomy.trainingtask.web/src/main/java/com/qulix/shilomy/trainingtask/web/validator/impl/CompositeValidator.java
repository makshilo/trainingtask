package com.qulix.shilomy.trainingtask.web.validator.impl;

import com.qulix.shilomy.trainingtask.web.validator.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Хранилище валидаторов
 */
public class CompositeValidator implements Validator {

    // Список валидаторов
    protected List<Validator> validators = new ArrayList<>();

    /**
     * Конструктор с добавлением валидаторов в список
     * @param validators валидаторы для добавления
     */
    public CompositeValidator(Validator... validators) {
        this.validators.addAll(Arrays.asList(validators));
    }

    /**
     * Прохождение по всем валидаторам в списке
     * @return строка ошибки или пустая строка если ошибок нет
     */
    @Override
    public String isValid() {
        for (Validator validator : validators) {
            String error = validator.isValid();
            if (!error.isEmpty()) {
                return error;
            }
        }
        return "";
    }

    /**
     * Добавление валидатора в список
     * @param validator валидатор для добавления
     */
    public void add(Validator validator) {
        this.validators.add(validator);
    }

    /**
     * Очистка списка валидаторов
     */
    public void clear() {
        validators.clear();
    }
}
