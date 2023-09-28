package org.example.app.views.user_view;

import org.example.app.views.views_util.InputOutputView;

public class UserDeleteView extends InputOutputView {
    public String[] getData() {
        String title = "Enter user's ID: ";
        String id = inputReader.readString(title);
        return new String[]{id};
    }
}
