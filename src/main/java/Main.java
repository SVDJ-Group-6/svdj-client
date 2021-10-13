import javafx.application.Application;
import javafx.stage.Stage;
import view.HomeView;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new HomeView().homeScreen());
        primaryStage.show();
    }
}
