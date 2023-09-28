package org.example.app.validators;


import org.example.app.constants.Constants;

// Валідація email
public class EmailValidator {
    public static boolean isEmailValid(String email) {
        return email.isEmpty() || !email.matches(Constants.EMAIL_RGX);
    }
}
