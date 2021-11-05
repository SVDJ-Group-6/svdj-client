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
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class HomeView {
    ThemeController themeController = ThemeController.getInstance();
    Theme theme = themeController.getTheme();
    
    final double BUTTON_PADDING = 17.5;
    final int HEADER_FONT_SIZE = 64;
    final int BUTTON_FONT_SIZE = 22;

    final int LOGO_WIDTH = 480;
    final int LOGO_HEIGHT = 128;

   final String BUTTON_STYLE = "-fx-background-color: %s;";
    final int NODE_SPACING = 25;

    final String fontFamily = "Arial";
    public VBox getHomePane(){
        BackgroundImage bgImage = null;
        InputStream logoInput = null;

        bgImage = new BackgroundImage(new Image(getClass().getClassLoader().getResourceAsStream("background.png")),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        logoInput = getClass().getClassLoader().getResourceAsStream("logo.png");

        Image logoImage = new Image(logoInput, LOGO_WIDTH, LOGO_HEIGHT, false, false);
        ImageView logoView = new ImageView(logoImage);


        HBox logoContainer = new HBox(NODE_SPACING);
        logoContainer.setPadding(new Insets(20, 0, 0,20));
        logoContainer.getChildren().addAll(logoView);


        VBox TitleContainer = new VBox(NODE_SPACING);
        TitleContainer.setPadding(new Insets(0,0,0,40));

        Text title = new Text("Weten welke subsidie bij jou past?");
        title.setFont(Font.font (fontFamily, FontWeight.BOLD, HEADER_FONT_SIZE));
        title.setFill(Color.web(ClientVariables.theme.getPrimaryColor()));

        Text text = new Text("Maak nu onze vragenlijst");
        text.setFont(Font.font (fontFamily, FontWeight.BOLD, 34));
        text.setFill(Color.web(ClientVariables.theme.getPrimaryColor()));

        TitleContainer.getChildren().addAll(title,text);

        HBox bottomContainer = new HBox(400);
        bottomContainer.setPadding(new Insets(0, 0, 0,40));
        bottomContainer.setAlignment(Pos.BOTTOM_LEFT);
        bottomContainer.setPrefHeight(300);

        Button start_vragenlijst = new Button("Start vragenlijst");
        start_vragenlijst.setFont(Font.font(fontFamily, FontWeight.BOLD, BUTTON_FONT_SIZE));
        start_vragenlijst.setTextFill(Color.web(ClientVariables.theme.getSecondaryColor()));
        start_vragenlijst.setAlignment(Pos.CENTER_LEFT);
        start_vragenlijst.setPadding(new Insets(BUTTON_PADDING));
        start_vragenlijst.setPrefWidth(326);
        start_vragenlijst.setStyle(String.format(BUTTON_STYLE, ClientVariables.theme.getCtaButtonColor()));
        start_vragenlijst.setOnMouseEntered(e -> {
            start_vragenlijst.setStyle(String.format(BUTTON_STYLE, ClientVariables.theme.getSelectedButtonColor()));
        });
        start_vragenlijst.setOnMouseExited(e -> {
            start_vragenlijst.setStyle(String.format(BUTTON_STYLE, ClientVariables.theme.getCtaButtonColor()));
        });
        start_vragenlijst.setOnAction(e->{
            Scene scene = new Scene(new QuizView().getQuizPane());
            ClientVariables.stage.setScene(scene);
        });

        Text madeBy = new Text("De beslissingsmatrix wordt medemogelijk gemaakt door\n" + "het Stimuleringsfonds voor de Journalistiek\n");
        madeBy.setFont(Font.font (fontFamily, FontWeight.BOLD, 16));
        madeBy.setFill(Color.web(ClientVariables.theme.getPrimaryColor()));

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
        
        VBox layout = new VBox(NODE_SPACING);
        layout.setMinWidth(1000);
        layout.setBackground(new Background(bgImage));
        layout.getChildren().addAll(logoContainer,TitleContainer,bottomContainer);

        return layout;
    }
}