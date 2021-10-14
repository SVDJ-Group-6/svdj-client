package Client;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.AdviceView;
import view.ButtonCreator;
import view.QuizView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ClientApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        final int logoWidth = 480;
        final int logoHeight = 128;

        StackPane pane = new QuizView().getQuizPane();

        // Set own scene!
        Scene scene  = new Scene(pane);
        BackgroundSize backgroundSize = new BackgroundSize(1280, 800, true, true, true, false);
        BackgroundImage bgImage = null;
        try {
            bgImage = new BackgroundImage(new Image(new FileInputStream("./src/main/resources/background.png")),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileInputStream inputstream = new FileInputStream("src/main/resources/logo.png");
        Image logoImage = new Image(inputstream, logoWidth, logoHeight, false, false);
        ImageView logoView = new ImageView(logoImage);

        HBox logoContainer = new HBox(25);
//        logoContainer.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        logoContainer.setPadding(new Insets(20, 0, 0,20));
        logoContainer.getChildren().addAll(logoView);

        HBox question_Answers_box = new HBox();
        question_Answers_box.setMaxWidth(1000);
        question_Answers_box.setMaxHeight(450);
        question_Answers_box.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-background-radius: 10;");





        Button button1 = new ButtonCreator("Answer 1","ccccff").createCustomButton();
        Button button2 = new ButtonCreator("Answer 2","ccccff").createCustomButton();

        Button button4 = new ButtonCreator("Answer 3","ccccff").createCustomButton();
        Button button5 = new ButtonCreator("Answer 4","ccccff").createCustomButton();

        Button button6 = new ButtonCreator("vorige","ccccff").createCustomButton();
        Button button7 = new ButtonCreator("volgende","ccccff").createCustomButton();

        GridPane gridPane = new GridPane();

        int insets = 50;
        gridPane.setHgap(insets * 2);
        gridPane.setVgap(insets);

        gridPane.add(button1, 0, 0);
        gridPane.add(button2, 1, 0);

        gridPane.add(button4, 0, 1);
        gridPane.add(button5, 1, 1);

        question_Answers_box.setAlignment(Pos.CENTER);
        gridPane.setMaxHeight(question_Answers_box.getMaxHeight()/2);


        question_Answers_box.getChildren().add(gridPane);

        //nav stuff here
        GridPane navGrid = new GridPane();
        navGrid.setStyle("-fx-background-color: rgb(100,0,5); -fx-background-radius: 10;");
       // navGrid.add(button6,0,0);
       // navGrid.add(button7,0,2);


        HBox ofHbox = new HBox();
        HBox firstLine = new HBox();
        firstLine.setMaxWidth(100);
        firstLine.setMaxHeight(5);
        firstLine.setStyle("-fx-background-color: rgb(255,255,255); -fx-background-radius: 10;");
        ofHbox.getChildren().add(firstLine);

        Text text = new Text("OF");
        ofHbox.getChildren().add(text);

        HBox secondLine = new HBox();
        secondLine.setMaxWidth(100);
        secondLine.setMaxHeight(5);
        secondLine.setStyle("-fx-background-color: rgb(255,255,255); -fx-background-radius: 10;");
        ofHbox.getChildren().add(secondLine);


        question_Answers_box.getChildren().add(navGrid);



        pane.setBackground(new Background(bgImage));
        pane.getChildren().add(logoContainer);
        pane.getChildren().add(question_Answers_box);
        stage.setScene(scene);

        stage.setHeight(800);
        stage.setWidth(1280);
        stage.setResizable(false);
        stage.show();
    }
}
