package com.qulix.shilomy.trainingtask.web.validator.impl;

import com.qulix.shilomy.trainingtask.web.validator.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompositeValidator implements Validator {
    protected List<Validator> validators = new ArrayList<>();

    public CompositeValidator(Validator... validators) {
        this.validators.addAll(Arrays.asList(validators));
    }

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

    public void add(Validator validator) {
        this.validators.add(validator);
    }

    public void clear() {
        validators.clear();
    }
}
