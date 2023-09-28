package org.example.app.validators.strategy;

import org.example.app.constants.Constants;
import org.example.app.validators.EmailValidator;

import java.util.HashMap;
import java.util.Map;

public class CreateValidationStrategy implements ValidationStrategy {
    @Override
    public Map<String, String> validate(String[] data) {
        Map<String, String> errors = new HashMap<>();

        if (data[0].isEmpty()) {
            errors.put("user name", Constants.INPUT_REQ_MSG);
        }

        if (data[1].isEmpty()) {
            errors.put("first name", Constants.INPUT_REQ_MSG);
        }

        if (data[2].isEmpty()) {
            errors.put("last name", Constants.INPUT_REQ_MSG);
        }

        if (EmailValidator.isEmailValid(data[3])) {
            errors.put("email", Constants.WRONG_EMAIL_MSG);
        }

        return errors;
    }
}
