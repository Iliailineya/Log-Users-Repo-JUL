package org.example.app.validators.strategy;

import org.example.app.constants.Constants;
import org.example.app.validators.IdValidator;

import java.util.HashMap;
import java.util.Map;

public class UpdateValidationStrategy implements ValidationStrategy {
    @Override
    public Map<String, String> validate(String[] data) {
        Map<String, String> errors = new HashMap<>();

        if (IdValidator.isIdValid(data[0])) {
            errors.put("id", Constants.WRONG_ID_MSG);
        }

        if (data[1].isEmpty()) {
            errors.put("user name", Constants.WRONG_USER_NAME_MSG);
        }

        return errors;
    }
}
