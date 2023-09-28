package org.example.app.validators;

import org.example.app.utils.Constants;

// Валидация Id
public class IdValidator {
    public static boolean isIdValid(String id) {
        return id.isEmpty() || !id.matches(Constants.ID_RGX );
    }
}
