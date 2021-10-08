import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.QuizView;

public class ClientApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new QuizView());
        stage.setScene(scene);

        stage.show();
    }
}
