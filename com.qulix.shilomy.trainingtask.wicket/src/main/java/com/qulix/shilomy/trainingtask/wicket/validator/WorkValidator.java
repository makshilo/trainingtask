package com.qulix.shilomy.trainingtask.wicket.validator;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorkValidator implements IValidator<String> {
    private static final String WORK_REGEX = "^[0-9]+";
    @Override
    public void validate(IValidatable<String> validatable) {
        final Pattern pattern = Pattern.compile(WORK_REGEX);
        final Matcher matcher = pattern.matcher(validatable.getValue());

        if (!matcher.matches()) {
            ValidationError error = new ValidationError(this);
            validatable.error(error);
        }
    }
}
