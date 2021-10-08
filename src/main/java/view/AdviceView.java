package view;

import controller.AdviceController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Advice;
import observer.AdviceObserver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AdviceView implements AdviceObserver {


    private AdviceController adviceController;
    private Advice advice;

    public AdviceView() {

    }

    public Scene AdviceView() throws FileNotFoundException {
        BackgroundSize backgroundSize = new BackgroundSize(1280, 720, true, true, true, false);
        BackgroundImage background = null;
        try {
            background = new BackgroundImage(new Image(new FileInputStream("./src/main/resources/background.png")),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Image logo = new Image(new FileInputStream("./src/main/resources/SVDJ_logo.png"));
        ImageView logoView = new ImageView(logo);
        logoView.setFitHeight(128);
        logoView.setFitWidth(480);
        logoView.setPreserveRatio(true);


        HBox titleContainer = new HBox();
        Text title = new Text();
        title.setText("Bedankt voor het gebruiken van ons applicatie");
        title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
        title.setFill(Color.WHITE);
        titleContainer.setAlignment(Pos.CENTER);
        titleContainer.getChildren().addAll(title);


        VBox layout = new VBox(25);
        layout.setBackground(new Background(background));
        layout.setPadding(new Insets(25));
        layout.getChildren().addAll(logoView, titleContainer);

        return new Scene(layout, 1280, 720);
    }


    public AdviceView(int adviceId){}

    @Override
    public void update(Advice advice) {

    }
}
