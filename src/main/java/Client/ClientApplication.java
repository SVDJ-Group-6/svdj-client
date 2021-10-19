package Client;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ClientApplication extends Application {
    QuizComponent quizComponent = new QuizComponent();
    QuizView view = new QuizView();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        final int logoWidth = 480;
        final int logoHeight = 128;

        //This returns the question/ansewers made by steve's connection to database
        VBox pane = view.getQuizPane();
        //VBox pane = new VBox(10);


        // Set own scene!
        Scene scene  = new Scene(pane);

        //add answer buttons to grid
        HBox gridPane = quizComponent.createAnswers();
        view.getQuestion_Answers_box().getChildren().add(gridPane);


        gridPane.setStyle("-fx-background-color: rgb(192,94,113); -fx-background-radius: 10;");
        gridPane.setAlignment(Pos.CENTER);

        stage.setScene(scene);

        stage.setHeight(720);
        stage.setWidth(1280);
        stage.setResizable(false);
        stage.show();
    }
}

