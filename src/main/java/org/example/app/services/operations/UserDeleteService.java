package org.example.app.services.operations;

import lombok.AllArgsConstructor;
import org.example.app.constants.Constants;
import org.example.app.database.DBCheck;
import org.example.app.entities.User;
import org.example.app.exceptions.DBException;
import org.example.app.exceptions.DeleteException;
import org.example.app.repositories.UserDeleteRepository;
import org.example.app.utils.IdChecker;
import org.example.app.validators.IdValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@AllArgsConstructor
public class UserDeleteService {

    UserDeleteRepository repository;
    private final static Logger LOGGER =
            Logger.getLogger(UserDeleteService.class.getName());

    public String deleteUser(String[] data) {
        checkDBExists();

        Map<String, String> errors = validate(data);

        if (!errors.isEmpty()) {
            try {
                throw new DeleteException("Check inputs for delete data.", errors);
            } catch (DeleteException ue) {
                LOGGER.log(Level.WARNING, Constants.LOG_DATA_INPTS_WRONG_MSG);
                return ue.getErrors(errors);
            }
        }

        return repository.deleteUser(mapData(data));
    }

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

        return errors;
    }

    // Преобразовываем массив данных в объект.
    public User mapData(String[] data) {
        // Создаем объект.
        User user = new User();
        // Устанавливаем значения свойств объекта.
        user.setId(Integer.parseInt(data[0].trim()));
        // Возвращаем объект.
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
