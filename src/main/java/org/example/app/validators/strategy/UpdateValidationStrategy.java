package org.example.app.validators.strategy;

import org.example.app.constants.Constants;
import org.example.app.entities.User;
import org.example.app.utils.IdChecker;
import org.example.app.validators.IdValidator;
import org.example.app.validators.PhoneValidator;

import java.util.HashMap;
import java.util.Map;

public class UpdateValidationStrategy implements ValidationStrategy {
    @Override
    public Map<String, String> validate(String[] data) {
        String strId = data[0].trim();
        int id = 0;
        // Map для сбора ошибок
        Map<String, String> errors = new HashMap<>();

        try {
            // Код, который может вызвать исключение
            id = Integer.parseInt(strId);
        } catch(NumberFormatException nfe) {
            errors.put("id", nfe.getMessage());
        }

        if (IdValidator.isIdValid(strId))
            errors.put("id", Constants.WRONG_ID_MSG);

        if (id <= 0)
            errors.put("id", Constants.WRONG_ID_MSG);

        if (IdChecker.isIdExists(id))
            errors.put("id", Constants.ID_NO_EXISTS_MSG);

        if (PhoneValidator.isPhoneValid(data[1]))
            errors.put("phone", Constants.WRONG_PHONE_MSG);

        return errors;
    }

    public User mapData(String[] data) {
        // Создаем объект.
        User user = new User();
        // Устанавливаем значения свойств объекта.
        user.setId(Integer.parseInt(data[0].trim()));
        user.setPhone(data[1].trim());
        // Возвращаем объект.
        return user;
    }
}
