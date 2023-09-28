package org.example.app.services;

import org.example.app.controllers.*;
import org.example.app.repositories.*;
import org.example.app.utils.*;
import org.example.app.views.user_view.*;
import org.example.app.exceptions.OptionException;

public class AppService {

    public void createUser() {
        UserCreateRepository repository = new UserCreateRepository();
        UserCreateService service = new UserCreateService(repository);
        UserCreateView view = new UserCreateView();
        ContactCreateController controller = new ContactCreateController(service, view);
        controller.createContact();
    }

    public void readUsers() {
        UserReadRepository repository = new UserReadRepository();
        ContactReadService service = new ContactReadService(repository);
        UserReadView view = new UserReadView();
        ContactReadController controller = new ContactReadController(service, view);
        controller.readContacts();
    }

    public void updateUser() {
        UserUpdateRepository repository = new UserUpdateRepository();
        ContactUpdateService service = new ContactUpdateService(repository);
        UserUpdateView view = new UserUpdateView();
        ContactUpdateController controller = new ContactUpdateController(service, view);
        controller.updateContact();
    }

    public void deleteUser() {
        UserDeleteRepository repository = new UserDeleteRepository();
        ContactDeleteService service = new ContactDeleteService(repository);
        UserDeleteView view = new UserDeleteView();
        ContactDeleteController controller = new ContactDeleteController(service, view);
        controller.deleteContact();
    }

    public void getNoSuchOption(int choice) {
        int[] menuChoices = {0, 1, 2, 3, 4};
        if (!contains(menuChoices, choice)) {
            try {
                throw new OptionException(Constants.INCORRECT_VALUE_MSG);
            } catch (OptionException e) {
                System.out.println(e.getMessage());
                AppStarter.startApp();
            }
        }
    }

    // Проверка наличия ввода в массиве выбора
    public static boolean contains(final int[] options, final int value) {
        boolean result = false;
        for (int i : options) {
            if (i == value) {
                result = true;
                break;
            }
        }
        return result;
    }
}
