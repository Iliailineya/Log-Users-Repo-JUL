package org.example.app.database;

import org.example.app.constants.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Класс-коннектор к БД.
public class DBConn {

    public static Connection connect() {

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(Constants.DB_DRIVER +
                            Constants.DB_BASE_URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
