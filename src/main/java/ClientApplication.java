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
        Scene scene = new Scene(new QuizView().getQuizPane());
        stage.setScene(scene);
        stage.setHeight(720);
        stage.setWidth(1280);
        stage.show();
    }
}
