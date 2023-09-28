package org.example.app.services.operations;

import lombok.AllArgsConstructor;
import org.example.app.database.DBCheck;
import org.example.app.entities.User;
import org.example.app.exceptions.DBException;
import org.example.app.repositories.UserReadRepository;
import org.example.app.constants.Constants;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

@AllArgsConstructor
public class UserReadService {

    UserReadRepository repository;
    private static final Logger LOGGER =
            Logger.getLogger(UserReadService.class.getName());

    public String readUsers() {
        checkDBExists();

        List<User> users = repository.readUsers();

        if (users != null) {
            if (!users.isEmpty()) {
                return formatUserList(users);
            } else {
                LOGGER.log(Level.WARNING, Constants.LOG_DATA_ABSENT_MSG);
                return Constants.DATA_ABSENT_MSG;
            }
        } else {
            LOGGER.log(Level.WARNING, Constants.LOG_DATA_ABSENT_MSG);
            return Constants.DATA_ABSENT_MSG;
        }

    }

    private String formatUserList(List<User> users) {
        AtomicInteger count = new AtomicInteger(0);
        StringBuilder stringBuilder = new StringBuilder();
        users.forEach(user -> stringBuilder.append(count.incrementAndGet())
                .append(") id - ")
                .append(user.getId())
                .append(", user_name - ")
                .append(user.getUser_name())
                .append(", first_name - ")
                .append(user.getFirst_name())
                .append(", last_name - ")
                .append(user.getLast_name())
                .append(", phone - ")
                .append(user.getPhone())
                .append(", email - ")
                .append(user.getEmail())
                .append("\n"));
        return stringBuilder.toString();
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
