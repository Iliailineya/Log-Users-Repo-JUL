package org.example.app.validators.strategy;

import org.example.app.entities.User;

import java.util.Map;

public interface ValidationStrategy {
    Map<String, String> validate(String[] data);
    User mapData(String[] data);
}

