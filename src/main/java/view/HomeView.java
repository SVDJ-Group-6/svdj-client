package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HomeView {
    final double buttonPadding = 17.5;
    final int headerFontSize = 64;
    final int buttonFontSize = 22;

    final int logoWidth = 480;
    final int logoHeight = 128;

    final int gridHGap = 75;
    final int gridVGap = 50;
    final String buttonColor = "#9CC2D4";
    final String hoverButtonColor = "#E4F6FF";

    final String logoutButtonColor = "#FFFFFF";

    final String fontFamily = "Arial";
    public Scene getHomeScene() throws FileNotFoundException {

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


        VBox TitleContainer = new VBox(25);
        TitleContainer.setPadding(new Insets(0,0,0,40));
//        TitleContainer.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));

        Text title = new Text("Weten welke subsidie bij jou past?");
        title.setFont(Font.font (fontFamily, FontWeight.BOLD, headerFontSize));
        title.setFill(Color.WHITE);

        Text text = new Text("Maak nu onze vragenlijst");
        text.setFont(Font.font (fontFamily, FontWeight.BOLD, 34));
        text.setFill(Color.WHITE);

        TitleContainer.getChildren().addAll(title,text);


        HBox bottomContainer = new HBox(400);
        bottomContainer.setPadding(new Insets(0, 0, 0,40));
//        bottomContainer.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        bottomContainer.setAlignment(Pos.BOTTOM_LEFT);
        bottomContainer.setPrefHeight(300);
        Button start_vragenlijst = new Button("Start vragenlijst");
        start_vragenlijst.setFont(Font.font(fontFamily, FontWeight.BOLD, buttonFontSize));
        start_vragenlijst.setTextFill(Color.BLACK);
        start_vragenlijst.setAlignment(Pos.CENTER_LEFT);
        start_vragenlijst.setPadding(new Insets(buttonPadding));
        start_vragenlijst.setPrefWidth(326);
        start_vragenlijst.setStyle(String.format("-fx-background-color: %s;", buttonColor));
        start_vragenlijst.setOnMouseEntered(e -> {
            start_vragenlijst.setStyle(String.format("-fx-background-color: %s;", hoverButtonColor));
        });
        start_vragenlijst.setOnMouseExited(e -> {
            start_vragenlijst.setStyle(String.format("-fx-background-color: %s;", buttonColor));
        });
        Text madeBy = new Text("De beslissingsmatrix wordt medemogelijk gemaakt door\n" +
                "het Stimuleringsfonds voor de Journalistiek\n");
        madeBy.setFont(Font.font (fontFamily, FontWeight.BOLD, 16));
        madeBy.setFill(Color.WHITE);

        TextFlow madeByText = new TextFlow(
                madeBy, new Hyperlink("www.svdj.nl")
        );


        bottomContainer.getChildren().addAll(start_vragenlijst,madeByText);

        VBox layout = new VBox(25);
        layout.setSpacing(25);
        layout.setMinWidth(1000);
        layout.setBackground(new Background(bgImage));
        layout.getChildren().addAll(logoContainer,TitleContainer,bottomContainer);

        return new Scene(layout, 1280,720);
    }
}