package Admin;

import view.LoginView;

import java.io.FileNotFoundException;

import controller.ThemeController;
import javafx.application.Application;
import javafx.stage.Stage;

public class AdminApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        AdminVariables.stage = primaryStage;

        ThemeController themeController = ThemeController.getInstance();
        AdminVariables.theme = themeController.getTheme();

        AdminVariables.stage.setTitle("Admin dashboard");
        AdminVariables.stage.setWidth(1280);
        AdminVariables.stage.setHeight(720);
        AdminVariables.stage.centerOnScreen();
        AdminVariables.stage.setResizable(false);

//        AdminVariables.stage.setScene(new EmailView().getEmailPane());
        AdminVariables.stage.setScene(new LoginView().getLoginScene());
        AdminVariables.stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
