package org.example.app.validators.strategy;

import org.example.app.constants.Constants;
import org.example.app.validators.IdValidator;

import java.util.HashMap;
import java.util.Map;

public class DeleteValidationStrategy implements ValidationStrategy {
    @Override
    public Map<String, String> validate(String[] data) {
        Map<String, String> errors = new HashMap<>();

        if (IdValidator.isIdValid(data[0])) {
            errors.put("id", Constants.WRONG_ID_MSG);
        }

        return errors;
    }
}
