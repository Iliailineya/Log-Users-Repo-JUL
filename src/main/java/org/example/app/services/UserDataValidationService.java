package org.example.app.services;

import org.example.app.validators.strategy.ValidationStrategy;

import java.util.Map;

public class UserDataValidationService {
    private final ValidationStrategy validationStrategy;

    public UserDataValidationService(ValidationStrategy validationStrategy) {
        this.validationStrategy = validationStrategy;
    }

    public Map<String, String> validateUser(String[] data) {
        return validationStrategy.validate(data);
    }
}

