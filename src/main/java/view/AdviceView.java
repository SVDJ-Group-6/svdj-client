package view;

import Client.ClientVariables;
import controller.AdviceController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import model.Advice;
import observer.AdviceObserver;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AdviceView implements AdviceObserver {
    private AdviceController adviceController = AdviceController.getInstance();
    private Text givenAdvice;
    private Text description;
    private Text web_url;
    private Text intro_video;

    final double BUTTON_PADDING = 17.5;
    final int BUTTON_WIDTH = 326;
    final int BUTTON_FONT_SIZE = 22;

    final int TITLE_FONT_SIZE = 50;
    final int UNDER_TITLE_FONT_SIZE = 24;
    final int DEFAULT_TEXT_FONT_SIZE = 16;
    final String FONT_FAMILY = "Arial";

    final int LOGO_WIDTH = 480;
    final int LOGO_HEIGHT = 128;

    final int ADVICE_CONTAINER_WIDTH = 670;
    final int ADVICE_CONTAINER_WRAPPING_WIDTH = 650;
    final int DESCRIPTION_WIDTH = 800;
    final int DESCRIPTION_CONTAINER_HEIGHT = 170;
    final int DESCRIPTION_WRAPPING_WIDTH = 750;
    final int INFORMATION_WIDTH = 500;
    final int HOME_CONTAINER_HEIGHT = 100;
    final int HOME_CONTAINER_WIDTH = 400;
    final int ADVICE_WIDTH = 400;
    final int CONTACT_WIDTH = 400;
    final int EMAIL_CONTAINER_WIDTH = 400;
    final int EMAIL_TEXTFIELD_WIDTH = 270;
    final int EMAIL_TEXTFIELD_HEIGHT = 59;
    final int EMAIL_ICON = 22;
    final int TEXT_CONTAINER_PADDING = 10;
    final int TEXT_CONTAINER_WIDTH = 800;
    final int TEXT_CONTAINER_HEIGHT = 400;
    final int BUTTON_CONTAINER_PADDING = 30;
    final int BUTTON_CONTAINER_WIDTH = 500;
    final int BODY_CONTAINER_PADDING = 30;
    final int LAYOUT_CONTAINER_PADDING = 10;
    final int TEXT_CONTAINER_INSETS = 25;

    final String CONTACT_URL = "https://www.svdj.nl/contact/";

    final String FX_BACKGROUND_COLOR = "-fx-background-color: %s;";

    private VBox buttonContainer = new VBox(BUTTON_CONTAINER_PADDING);
    private VBox contactContainer = new VBox();
    private HBox sendEmailContainer = new HBox();

    public AdviceView(int adviceId) {
        givenAdvice = new Text();
        description = new Text();
        web_url = new Text();
        intro_video = new Text();

        adviceController.registerObserver(this);
        adviceController.loadAdvice(adviceId);
    }

    public VBox getAdvicePane() {
        BackgroundImage background = null;
        Image logo = null;

        try {
            background = new BackgroundImage(new Image(new FileInputStream("./src/main/resources/background.png")),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    BackgroundSize.DEFAULT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            logo = new Image(new FileInputStream("./src/main/resources/SVDJ_logo.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ImageView logoView = new ImageView(logo);
        logoView.setFitHeight(LOGO_HEIGHT);
        logoView.setFitWidth(LOGO_WIDTH);
        logoView.setPreserveRatio(true);

        VBox titleContainer = new VBox();
        titleContainer.setPadding(new Insets(16, 16, 16, 10));
        // titleContainer.setStyle(paddingSize(16));
        Text title = new Text();
        title.setText("Bedankt voor het gebruiken van ons applicatie");
        title.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, FontPosture.REGULAR, TITLE_FONT_SIZE));
        title.setFill(Color.web(ClientVariables.theme.getPrimaryColor()));
        titleContainer.getChildren().addAll(title);

        VBox adviceContainer = new VBox();
        adviceContainer.setMaxWidth(ADVICE_CONTAINER_WIDTH);
        adviceContainer.setStyle(paddingSize(10));
        Text adviceText = new Text();
        adviceText.setWrappingWidth(ADVICE_CONTAINER_WRAPPING_WIDTH);
        adviceText.setText(
                "Uit het vragenlijst is gebleken dat u mogelijk in aanmerking komt voor: " + givenAdvice.getText());
        adviceText.setFont(Font.font(FONT_FAMILY, FontPosture.REGULAR, UNDER_TITLE_FONT_SIZE));
        adviceText.setFill(Color.web(ClientVariables.theme.getPrimaryColor()));
        adviceContainer.getChildren().addAll(adviceText);

        VBox descriptionContainer = new VBox();
        descriptionContainer.setMinWidth(DESCRIPTION_WIDTH);
        descriptionContainer.setStyle(paddingSize(10));
        descriptionContainer.setMinHeight(DESCRIPTION_CONTAINER_HEIGHT);
        descriptionContainer.setMaxHeight(DESCRIPTION_CONTAINER_HEIGHT);
        Text descriptionText = new Text();
        descriptionText.setWrappingWidth(DESCRIPTION_WRAPPING_WIDTH);
        descriptionText.setText(description.getText());
        descriptionText.setFont(Font.font(FONT_FAMILY, FontPosture.REGULAR, DEFAULT_TEXT_FONT_SIZE));
        descriptionText.setFill(Color.web(ClientVariables.theme.getPrimaryColor()));
        ScrollPane sp = new ScrollPane();
        sp.setContent(descriptionText);
        sp.setHbarPolicy(ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        sp.setStyle("-fx-background: transparent;" + "-fx-border-color: transparent;"
                + "-fx-background-color: transparent");
        descriptionContainer.getChildren().addAll(sp);

        Hyperlink svdjLink = new Hyperlink("www.svdj.nl");
        svdjLink.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, DEFAULT_TEXT_FONT_SIZE));
        web_url.setText("https://www.svdj.nl/");
        svdjLink.setOnAction(e -> {
            try {
                svdjLink.setVisited(false);
                Desktop.getDesktop().browse(new URI(web_url.getText()));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (URISyntaxException uriSyntaxException) {
                uriSyntaxException.printStackTrace();
            }

        });

        svdjLink.setStyle("-fx-border-color: transparent;");

        Text moreInformationText = new Text("Voor meer informatie, ga naar: ");
        moreInformationText.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, DEFAULT_TEXT_FONT_SIZE));
        moreInformationText.setFill(Color.web(ClientVariables.theme.getPrimaryColor()));

        VBox moreInformationContainer = new VBox();
        moreInformationContainer.setMaxWidth(INFORMATION_WIDTH);
        moreInformationContainer.setStyle(paddingSize(8));
        moreInformationContainer.getChildren().addAll(moreInformationText, svdjLink);

        VBox homeButtonContainer = new VBox();
        homeButtonContainer.setPrefHeight(HOME_CONTAINER_HEIGHT);
        homeButtonContainer.setMaxWidth(HOME_CONTAINER_WIDTH);
        homeButtonContainer.setAlignment(Pos.CENTER_LEFT);

        Button homeButton = new Button("Home");
        homeButton.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, BUTTON_FONT_SIZE));
        homeButton.setTextFill(Color.web(ClientVariables.theme.getSecondaryColor()));
        homeButton.setAlignment(Pos.CENTER_LEFT);
        homeButton.setPadding(new Insets(BUTTON_PADDING));
        homeButton.setPrefWidth(BUTTON_WIDTH);
        homeButton.setStyle(String.format(FX_BACKGROUND_COLOR, ClientVariables.theme.getPrimaryColor())
                + "-fx-background-radius: 0;");
        homeButton.setOnMouseEntered(e -> {
            homeButton.setStyle(String.format(FX_BACKGROUND_COLOR, ClientVariables.theme.getSelectedButtonColor())
                    + "-fx-background-radius: 0;");
        });
        homeButton.setOnMouseExited(e -> {
            homeButton.setStyle(String.format(FX_BACKGROUND_COLOR, ClientVariables.theme.getPrimaryColor())
                    + "-fx-background-radius: 0;");
        });
        homeButton.setOnMouseClicked(e -> {
            buttonContainer.getChildren().removeAll(buttonContainer.getChildren());
            adviceController.unregisterObserver(this);
            ClientVariables.stage.setScene(new Scene(new HomeView().getHomePane()));
        });
        homeButtonContainer.getChildren().addAll(homeButton);

        contactContainer.setMaxWidth(CONTACT_WIDTH);
        contactContainer.setAlignment(Pos.CENTER);

        Button contactButton = new Button("Ik wil graag contact opnemen");
        contactButton.setFont(Font.font(FONT_FAMILY, FontWeight.NORMAL, DEFAULT_TEXT_FONT_SIZE));
        contactButton.setTextFill(Color.web(ClientVariables.theme.getSecondaryColor()));
        contactButton.setAlignment(Pos.CENTER_LEFT);
        contactButton.setPadding(new Insets(BUTTON_PADDING));
        contactButton.setPrefWidth(BUTTON_WIDTH);
        contactButton.setStyle(String.format(FX_BACKGROUND_COLOR, ClientVariables.theme.getCtaButtonColor())
                + "-fx-background-radius: 0;");
        contactButton.setOnMouseEntered(e -> {
            contactButton.setStyle(String.format(FX_BACKGROUND_COLOR, ClientVariables.theme.getSelectedButtonColor())
                    + "-fx-background-radius: 0;");
        });
        contactButton.setOnMouseExited(e -> {
            contactButton.setStyle(String.format(FX_BACKGROUND_COLOR, ClientVariables.theme.getCtaButtonColor())
                    + "-fx-background-radius: 0;");
        });
        contactButton.setOnMouseClicked(e -> {
            try {
                Desktop.getDesktop().browse(new URI(CONTACT_URL));
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
        });
        contactContainer.getChildren().addAll(contactButton);

        sendEmailContainer.setMaxWidth(EMAIL_CONTAINER_WIDTH);
        sendEmailContainer.setAlignment(Pos.CENTER);

        TextField emailTextfield = new TextField();
        emailTextfield.setPrefWidth(EMAIL_TEXTFIELD_WIDTH);
        emailTextfield.setPrefHeight(EMAIL_TEXTFIELD_HEIGHT);
        emailTextfield.setFont(Font.font(FONT_FAMILY, FontWeight.NORMAL, DEFAULT_TEXT_FONT_SIZE));
        emailTextfield.setPadding(new Insets(0, 0, 0, 12));
        emailTextfield.setPromptText("Mail mijn advies!");
        emailTextfield.setStyle("-fx-background-radius: 0; -fx-text-fill: #000000");

        FileInputStream input = null;

        try {
            input = new FileInputStream("./src/main/resources/email_logo.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Image image = new Image(input);

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(EMAIL_ICON);
        imageView.setFitWidth(EMAIL_ICON);

        Button sendEmailButton = new Button("", imageView);
        sendEmailButton.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, DEFAULT_TEXT_FONT_SIZE));
        sendEmailButton.setTextFill(Color.web(ClientVariables.theme.getPrimaryColor()));
        sendEmailButton.setAlignment(Pos.BASELINE_CENTER);
        sendEmailButton.setPadding(new Insets(BUTTON_PADDING));
        sendEmailButton.setStyle(String.format(FX_BACKGROUND_COLOR, ClientVariables.theme.getCtaButtonColor())
                + "-fx-background-radius: 0;");
        sendEmailButton.setOnMouseEntered(e -> {
            sendEmailButton.setStyle(String.format(FX_BACKGROUND_COLOR, ClientVariables.theme.getSelectedButtonColor())
                    + "-fx-background-radius: 0;");
        });
        sendEmailButton.setOnMouseExited(e -> {
            sendEmailButton.setStyle(String.format(FX_BACKGROUND_COLOR, ClientVariables.theme.getCtaButtonColor())
                    + "-fx-background-radius: 0;");
        });
        sendEmailButton.setOnMouseClicked(e -> {
            System.out.println("Email is verstuurd");
        });
        sendEmailContainer.getChildren().addAll(emailTextfield, sendEmailButton);

        VBox textContainer = new VBox(TEXT_CONTAINER_PADDING);
        textContainer.setMaxWidth(TEXT_CONTAINER_WIDTH);
        textContainer.setMinHeight(TEXT_CONTAINER_HEIGHT);
        textContainer.getChildren().addAll(adviceContainer, descriptionContainer, moreInformationContainer,
                homeButtonContainer);

        buttonContainer.setPrefWidth(BUTTON_CONTAINER_WIDTH);
        buttonContainer.setAlignment(Pos.BOTTOM_CENTER);

        HBox bodyContainer = new HBox(BODY_CONTAINER_PADDING);
        bodyContainer.getChildren().addAll(textContainer, buttonContainer);

        VBox layout = new VBox(LAYOUT_CONTAINER_PADDING);
        layout.setBackground(new Background(background));
        layout.setPadding(new Insets(TEXT_CONTAINER_INSETS));
        layout.getChildren().addAll(logoView, titleContainer, bodyContainer);

        return layout;
    }

    public String paddingSize(int desiredPadding) {
        String fxPadding = "-fx-padding: ";
        return fxPadding + desiredPadding + ";";
    }

    @Override
    public void update(Advice advice) {
        buttonContainer.getChildren().removeAll(buttonContainer.getChildren());
        givenAdvice.setText(advice.getValue());
        description.setText(advice.getDescription());
        intro_video.setText(advice.getVideoURL());

        VBox adviceVideoContainer = new VBox();
        adviceVideoContainer.setMaxWidth(ADVICE_WIDTH);
        adviceVideoContainer.setAlignment(Pos.CENTER);

        Button videoButton = new Button("Subsidie introductie video bekijken");
        videoButton.setFont(Font.font(FONT_FAMILY, FontPosture.REGULAR, DEFAULT_TEXT_FONT_SIZE));
        videoButton.setTextFill(Color.web(ClientVariables.theme.getSecondaryColor()));
        videoButton.setAlignment(Pos.CENTER_LEFT);
        videoButton.setPadding(new Insets(BUTTON_PADDING));
        videoButton.setPrefWidth(BUTTON_WIDTH);
        videoButton.setStyle(String.format(FX_BACKGROUND_COLOR, ClientVariables.theme.getCtaButtonColor()) + "-fx-background-radius: 0;");
        videoButton.setOnMouseEntered(e -> {
            videoButton.setStyle(String.format(FX_BACKGROUND_COLOR, ClientVariables.theme.getSelectedButtonColor()) + "-fx-background-radius: 0;");
        });
        videoButton.setOnMouseExited(e -> {
            videoButton.setStyle(String.format(FX_BACKGROUND_COLOR, ClientVariables.theme.getCtaButtonColor()) + "-fx-background-radius: 0;");
        });
        intro_video.setText("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        videoButton.setOnMouseClicked(e -> {
            try {
                Desktop.getDesktop().browse(new URI(intro_video.getText()));
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
        });
        adviceVideoContainer.getChildren().addAll(videoButton);
        if (advice.hasVideoURL()) {
            buttonContainer.getChildren().addAll(adviceVideoContainer, contactContainer, sendEmailContainer);
        } else {
            buttonContainer.getChildren().addAll(contactContainer, sendEmailContainer);
        }
    }

}
