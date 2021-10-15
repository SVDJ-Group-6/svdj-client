package view;

import Client.ClientVariables;
import controller.ThemeController;
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
import model.Theme;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;



public class HomeView {
    ThemeController themeController = ThemeController.getInstance();
    Theme theme = themeController.getTheme();
    final double buttonPadding = 17.5;
    final int headerFontSize = 64;
    final int buttonFontSize = 22;

    final int logoWidth = 480;
    final int logoHeight = 128;

    final String buttonColor = theme.getCtaButtonColor();
    final String hoverButtonColor = theme.getPrimaryColor();

    final String fontFamily = "Arial";
    public VBox getHomePane(){
        BackgroundImage bgImage = null;
        FileInputStream logoInput = null;

        try {
            bgImage = new BackgroundImage(new Image(new FileInputStream("./src/main/resources/background.png")),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            logoInput = new FileInputStream("src/main/resources/logo.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image logoImage = new Image(logoInput, logoWidth, logoHeight, false, false);
        ImageView logoView = new ImageView(logoImage);


        HBox logoContainer = new HBox(25);
        logoContainer.setPadding(new Insets(20, 0, 0,20));
        logoContainer.getChildren().addAll(logoView);


        VBox TitleContainer = new VBox(25);
        TitleContainer.setPadding(new Insets(0,0,0,40));

        Text title = new Text("Weten welke subsidie bij jou past?");
        title.setFont(Font.font (fontFamily, FontWeight.BOLD, headerFontSize));
        title.setFill(Color.WHITE);

        Text text = new Text("Maak nu onze vragenlijst");
        text.setFont(Font.font (fontFamily, FontWeight.BOLD, 34));
        text.setFill(Color.WHITE);

        TitleContainer.getChildren().addAll(title,text);


        HBox bottomContainer = new HBox(400);
        bottomContainer.setPadding(new Insets(0, 0, 0,40));
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
        start_vragenlijst.setOnAction(e->{
            Scene scene = new Scene(new QuizView().getQuizPane());
            ClientVariables.stage.setScene(scene);
        });
        Text madeBy = new Text("De beslissingsmatrix wordt medemogelijk gemaakt door\n" + "het Stimuleringsfonds voor de Journalistiek\n");
        madeBy.setFont(Font.font (fontFamily, FontWeight.BOLD, 16));
        madeBy.setFill(Color.WHITE);

        Hyperlink svdjHyperLink = new Hyperlink("svdj.nl");

        svdjHyperLink.setStyle("-fx-border-color: transparent;");
        svdjHyperLink.setFont(Font.font (fontFamily, FontWeight.BOLD, 16));
        svdjHyperLink.setOnAction(e-> {
            try {
                svdjHyperLink.setVisited(false);
                Desktop.getDesktop().browse(new URI("https://www.svdj.nl/"));
            } catch (IOException | URISyntaxException ex) {
                ex.printStackTrace();
            }
        });

        VBox madeByText = new VBox(madeBy,svdjHyperLink);
        madeByText.setAlignment(Pos.BOTTOM_LEFT);


        bottomContainer.getChildren().addAll(start_vragenlijst,madeByText);

        VBox layout = new VBox(25);
        layout.setSpacing(25);
        layout.setMinWidth(1000);
        layout.setBackground(new Background(bgImage));
        layout.getChildren().addAll(logoContainer,TitleContainer,bottomContainer);

        return layout;
    }
}