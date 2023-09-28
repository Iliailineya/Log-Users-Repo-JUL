package org.example.app.services.operations;

import lombok.AllArgsConstructor;
import org.example.app.constants.Constants;
import org.example.app.database.DBCheck;
import org.example.app.exceptions.CreateException;
import org.example.app.exceptions.DBException;
import org.example.app.repositories.UserCreateRepository;
import org.example.app.services.UserDataValidationService;
import org.example.app.validators.strategy.CreateValidationStrategy;

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

        UserDataValidationService createService = new UserDataValidationService(new CreateValidationStrategy());
        Map<String, String> errors = createService.validateUser(data);


        if (!errors.isEmpty()) {
            try {
                throw new CreateException("Check inputs", errors);
            } catch (CreateException ce) {
                LOGGER.log(Level.WARNING, Constants.LOG_DATA_INPTS_WRONG_MSG);
                return ce.getErrors(errors);
            }
        }

        return repository.createUser(createService.mapData(data));
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
