package org.example.app.utils;

import org.example.app.controllers.AppController;
import org.example.app.services.AppService;
import org.example.app.views.AppView;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

public class AppStarter {

    public static void startApp() {
        try {
            FileInputStream configFile = new FileInputStream("mylogs.properties");
            LogManager.getLogManager().readConfiguration(configFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        AppService service = new AppService();
        AppView view = new AppView();
        AppController controller = new AppController(view, service);
        controller.runApp();
    }
}
