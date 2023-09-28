package org.example.app.database;

import org.example.app.constants.Constants;

import java.io.File;

// Класс-проверка наличия файла БД.
public class DBCheck {
    public static boolean isDBNotExists() {
        return !new File(Constants.DB_BASE_URL +
                Constants.DB_NAME).exists();
    }
}