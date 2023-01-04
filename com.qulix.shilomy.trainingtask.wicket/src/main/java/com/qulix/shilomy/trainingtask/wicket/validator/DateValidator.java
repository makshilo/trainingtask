package com.qulix.shilomy.trainingtask.wicket.validator;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateValidator implements IValidator<String> {
    public static final String DATE_REGEX = "^((\\d{4}-((0[13578]-|1[02]-)(0[1-9]|[12]\\d|3[01])|(0[13456789]-|1[012]-)(0[1-9]|[12]\\d|30)|02-(0[1-9]|1\\d|2[0-8])))|(([02468][048]|[13579][26])00|\\d{2}([13579][26]|0[48]|[2468][048]))-02-29){0,10}$";
    @Override
    public void validate(IValidatable<String> validatable) {
        final Pattern pattern = Pattern.compile(DATE_REGEX);
        final Matcher matcher = pattern.matcher(validatable.getValue());

        if (!matcher.matches()) {
            ValidationError error = new ValidationError(this);
            validatable.error(error);
        }
    }
}
