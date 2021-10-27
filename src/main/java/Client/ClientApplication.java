package Client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import view.*;

public class ClientApplication extends Application {
    QuizView view = new QuizView();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage){
        //This returns the question/ansewers made by steve's connection to database
        VBox pane = view.drawQuiz();
        // Set own scene!
        Scene scene  = new Scene(pane);
        stage.setScene(scene);

        stage.setHeight(720);
        stage.setWidth(1280);
        stage.setResizable(false);
        stage.show();
    }
}

