package org.example.app.validators.strategy;

import org.example.app.constants.Constants;
import org.example.app.entities.User;
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
        if (data[3].isEmpty()) {
            errors.put("phone", Constants.INPUT_REQ_MSG);
        }

        if (!EmailValidator.isEmailValid(data[4])) {
            errors.put("email", Constants.WRONG_EMAIL_MSG);
        }

        return errors;
    }

    public User mapData(String[] data) {
        User user = new User();
        user.setUser_name(data[0]);
        user.setFirst_name(data[1]);
        user.setLast_name(data[2]);
        user.setPhone(data[3]);
        user.setEmail(data[4]);
        return user;
    }
}
