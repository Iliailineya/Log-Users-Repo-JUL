package org.example.app.entities;

import lombok.Getter;
import lombok.Setter;

// Класс-модель объекта, который является записью в БД.
// Набор переменных (свойств объекта) соответствует
// столбцам в таблице БД.
@Getter
@Setter
public class User {
    private int id;
    private String user_name;
    private String first_name;
    private String last_name;
    private String phone;
    private String email;
}
