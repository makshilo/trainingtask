package com.qulix.shilomy.trainingtask.web.validator.impl;

public class EmptinessValidator extends CompositeValidator {
    private final String errorMessage;
    private final String param;

    public EmptinessValidator(String errorMessage, String param) {
        this.errorMessage = errorMessage;
        this.param = param;
    }

    @Override
    public String isValid() {
        return !param.isEmpty() ? "" : errorMessage;
    }
}
