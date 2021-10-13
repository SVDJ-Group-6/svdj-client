package Admin;

import view.LoginView;

import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.stage.Stage;

public class AdminApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        AdminVariables.stage = primaryStage;

        AdminVariables.stage.setTitle("Admin dashboard");
        AdminVariables.stage.setWidth(1280);
        AdminVariables.stage.setHeight(720);
        AdminVariables.stage.centerOnScreen();
        AdminVariables.stage.setResizable(false);

        AdminVariables.stage.setScene(new LoginView().Login());
        AdminVariables.stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
