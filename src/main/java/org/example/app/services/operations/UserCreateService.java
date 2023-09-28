package org.example.app.services.operations;

import lombok.AllArgsConstructor;
import org.example.app.constants.Constants;
import org.example.app.database.DBCheck;
import org.example.app.entities.User;
import org.example.app.exceptions.CreateException;
import org.example.app.exceptions.DBException;
import org.example.app.repositories.UserCreateRepository;
import org.example.app.validators.EmailValidator;
import org.example.app.validators.PhoneValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@AllArgsConstructor
public class UserCreateService {

    UserCreateRepository repository;
    private static final Logger LOGGER =
            Logger.getLogger(UserCreateService.class.getName());


    public String createUser(String[] data) {
        checkDBExists();

        Map<String, String> errors = validate(data);


        if (!errors.isEmpty()) {
            try {
                throw new CreateException("Check inputs", errors);
            } catch (CreateException ce) {
                LOGGER.log(Level.WARNING, Constants.LOG_DATA_INPTS_WRONG_MSG);
                return ce.getErrors(errors);
            }
        }

        return repository.createUser(mapData(data));
    }

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
        if (PhoneValidator.isPhoneValid(data[3])) {
            errors.put("phone", Constants.WRONG_PHONE_MSG);
        }

        if (EmailValidator.isEmailNotValid(data[4])) {
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

    private void checkDBExists() {
        if (DBCheck.isDBNotExists()) {
            try {
                throw new DBException(Constants.DB_ABSENT_MSG);
            } catch (DBException e) {
                LOGGER.log(Level.SEVERE, Constants.LOG_DB_ABSENT_MSG);
            }
        }
    }
}
