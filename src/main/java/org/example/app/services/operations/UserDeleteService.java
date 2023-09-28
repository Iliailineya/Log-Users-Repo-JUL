package org.example.app.services.operations;

import lombok.AllArgsConstructor;
import org.example.app.constants.Constants;
import org.example.app.database.DBCheck;
import org.example.app.exceptions.DBException;
import org.example.app.exceptions.DeleteException;
import org.example.app.repositories.UserDeleteRepository;
import org.example.app.services.UserDataValidationService;
import org.example.app.validators.strategy.DeleteValidationStrategy;

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

        UserDataValidationService deleteService = new UserDataValidationService(new DeleteValidationStrategy());

        Map<String, String> errors = deleteService.validateUser(data);

        if (!errors.isEmpty()) {
            try {
                throw new DeleteException("Check inputs for delete data.", errors);
            } catch (DeleteException ue) {
                LOGGER.log(Level.WARNING, Constants.LOG_DATA_INPTS_WRONG_MSG);
                return ue.getErrors(errors);
            }
        }

        return repository.deleteUser(deleteService.mapData(data));
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
