package org.example.app.views.user_view;

import org.example.app.views.views_util.InputOutputView;

public class UserCreateView extends InputOutputView {
    public String[] getData() {

        String title = "Enter user name: ";
        String username = inputReader.readString(title);

        title = "Enter first name: ";
        String firstName = inputReader.readString(title);

        title = "Enter last name: ";
        String lastName = inputReader.readString(title);

        title = "Enter phone in format xxx xxx-xxxx: ";
        String phone = inputReader.readString(title);

        title = "Enter email in format example@mail.com: ";
        String email = inputReader.readString(title);

        return new String[]{username, firstName, lastName, phone, email};
    }
}
