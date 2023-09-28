package org.example.app.services.operations;

import org.example.app.constants.Constants;
import org.example.app.database.DBCheck;
import org.example.app.exceptions.DBException;
import org.example.app.exceptions.UpdateException;
import org.example.app.repositories.UserUpdateRepository;
import org.example.app.services.UserDataValidationService;
import org.example.app.validators.strategy.UpdateValidationStrategy;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserUpdateService {

    UserUpdateRepository repository;
    private static final Logger LOGGER =
            Logger.getLogger(UserUpdateService.class.getName());

    public UserUpdateService(UserUpdateRepository repository) {
        this.repository = repository;
    }

    public String updateUser(String[] data) {
        checkDBExists();

        UserDataValidationService updateService = new UserDataValidationService(new UpdateValidationStrategy());

        Map<String, String> errors = updateService.validateUser(data);

        if (errors.size() > 0) {
            try {
                throw new UpdateException("Check inputs for update data.", errors);
            } catch (UpdateException ue) {
                LOGGER.log(Level.WARNING, Constants.LOG_DATA_INPTS_WRONG_MSG);
                return ue.getErrors(errors);
            }
        }

        return repository.updateUser(updateService.mapData(data));
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
