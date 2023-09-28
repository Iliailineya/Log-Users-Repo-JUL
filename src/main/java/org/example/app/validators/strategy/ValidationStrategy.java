package org.example.app.validators.strategy;

import java.util.Map;

public interface ValidationStrategy {
    Map<String, String> validate(String[] data);
}

