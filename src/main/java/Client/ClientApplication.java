package Client;

import controller.ThemeController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.HomeView;

public class ClientApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage){
        ClientVariables.stage = stage;
        ThemeController themeController = ThemeController.getInstance();
        ClientVariables.theme = themeController.getTheme();
        Scene scene = new Scene(new HomeView().getHomePane());
        stage.setScene(scene);
        stage.setTitle("svdj-client");
        stage.setWidth(1280);
        stage.setHeight(720);
        stage.setResizable(false);
        stage.show();
    }
}

