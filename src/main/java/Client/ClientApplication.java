package Client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import view.*;

public class ClientApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage){
        ClientVariables.stage = stage;
        // Set own scene!
        QuizView quizView = new QuizView();
        Scene scene = new Scene(quizView.drawQuiz());
        stage.setScene(scene);

        stage.setHeight(720);
        stage.setWidth(1280);
        stage.setResizable(false);
        stage.show();
    }
}

