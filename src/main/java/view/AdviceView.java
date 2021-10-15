package view;

import Client.ClientVariables;
import controller.AdviceController;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
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
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
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
    private Advice advice;


    public AdviceView() {

    }

    public VBox getAdvicePane() {
        final double BUTTON_PADDING = 17.5;
        final int BUTTON_WIDTH = 326;
        final int BUTTON_FONT_SIZE = 22;
        final String BUTTON_COLOR = "#9CC2D4";
        final String HOVER_BUTTON_COLOR = "#E4F6FF";

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
        final String WEB_URL = "https://ww.svdj.nl/";
        final String VIDEO_URL = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
        final String CONTACT_URL = "https://www.svdj.nl/contact/";
        final String FX_BACKGROUND_COLOR = "-fx-background-color: %s;";

        Stage stage = ClientVariables.stage;

        BackgroundImage background = null;
        try {
            background = new BackgroundImage(new Image(new FileInputStream("./src/main/resources/background.png")),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Image logo = null;
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
        titleContainer.setStyle(paddingSize(16));
        Text title = new Text();
        title.setText("Bedankt voor het gebruiken van ons applicatie");
        title.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, FontPosture.REGULAR, TITLE_FONT_SIZE));
        title.setFill(Color.WHITE);
        titleContainer.getChildren().addAll(title);

        //TODO moet nog naar database connecten om de advies te halen
        VBox adviceContainer = new VBox();
        adviceContainer.setMaxWidth(ADVICE_CONTAINER_WIDTH);
        adviceContainer.setStyle(paddingSize(10));
        Text adviceText = new Text();
        String advice = "'Talent ontwikkeling'";
        adviceText.setWrappingWidth(ADVICE_CONTAINER_WRAPPING_WIDTH);
        adviceText.setText("Uit het vragenlijst is gebleken dat u mogelijk in aanmerking kom voor " + advice);
        adviceText.setFont(Font.font(FONT_FAMILY, FontPosture.REGULAR, UNDER_TITLE_FONT_SIZE));
        adviceText.setFill(Color.WHITE);
        adviceContainer.getChildren().addAll(adviceText);

        VBox descriptionContainer = new VBox();
        descriptionContainer.setMinWidth(DESCRIPTION_WIDTH);
        descriptionContainer.setStyle(paddingSize(10));
        descriptionContainer.setMinHeight(DESCRIPTION_CONTAINER_HEIGHT);
        descriptionContainer.setMaxHeight(DESCRIPTION_CONTAINER_HEIGHT);
        Text descriptionText = new Text();
        descriptionText.setWrappingWidth(DESCRIPTION_WRAPPING_WIDTH);
        descriptionText.setText("Lorem lorem Lorem lorem Lorem lorem Lorem lorem Lorem lorem Lorem lorem Lorem lorem Lorem lorem Lorem lorem Lorem lorem Lorem lorem Lorem lorem Lorem lorem Lorem lorem Lorem lorem Lorem lorem Lorem lorem Lorem lorem Lorem lorem Lorem lorem Lorem lorem Lorem lorem Lorem lorem Lorem lorem Lorem lorem Lorem lorem Lorem lorem Lorem lorem Lorfdfdsfdsfdsfdsfd fdfdsf dfehfh dsf hk fhjkdshjk f df dhf hkdfhkdsf hjkd fhjk dshjkfds hjkfdsfdshjkf dhjklsfdshjklf dshjkfdsjkfem lorem LoreLorem lorem Lorem lorem Lorem lorem Lorem lorem Lorfdfdsfdsfdsfdsfd fdfdsf dfehfh dsf hk fhjkdshjk f df dhf hkdfhkdsf hjkd fhjk dshjkfds hjkfdsfdshjkf dhjklsfdshjklf dshjkfdsjkfem lorem LoreLorem lorem Lorem lorem Lorem lorem Lorem lorem Lorfdfdsfdsfdsfdsfd fdfdsf dfehfh dsf hk fhjkdshjk f df dhf hkdfhkdsf hjkd fhjk dshjkfds hjkfdsfdshjkf dhjklsfdshjklf dshjkfdsjkfem lorem LoreLorem lorem Lorem lorem Lorem lorem Lorem lorem Lorfdfdsfdsfdsfdsfd fdfdsf dfehfh dsf hk fhjkdshjk f df dhf hkdfhkdsf hjkd fhjk.Lorem lorem Lorem lorem Lorem lorem Lorfdfdsfdsfdsfdsfd fdfdsf dfehfh dsf hk fhjkdshjk f df dhf hkdfhkdsf hjkd fhjk.");
        descriptionText.setFont(Font.font(FONT_FAMILY, FontPosture.REGULAR, DEFAULT_TEXT_FONT_SIZE));
        descriptionText.setFill(Color.WHITE);
        ScrollPane sp = new ScrollPane();
        sp.setContent(descriptionText);
        sp.setHbarPolicy(ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        sp.setStyle("-fx-background: transparent;" + "-fx-border-color: transparent;" + "-fx-background-color: transparent");
        descriptionContainer.getChildren().addAll(sp);

        Hyperlink svdjLink = new Hyperlink("www.svdj.nl");
        svdjLink.setFont(Font.font(FONT_FAMILY, FontPosture.REGULAR, DEFAULT_TEXT_FONT_SIZE));
        svdjLink.setOnAction(e-> {
            try {
                svdjLink.setVisited(false);
                Desktop.getDesktop().browse(new URI(WEB_URL));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (URISyntaxException uriSyntaxException) {
                uriSyntaxException.printStackTrace();
            }

        });
        svdjLink.setStyle("-fx-border-color: transparent;");

        Text moreInformationText = new Text("Voor meer informatie, ga naar: ");
        moreInformationText.setFont(Font.font(FONT_FAMILY, FontPosture.REGULAR, DEFAULT_TEXT_FONT_SIZE));
        moreInformationText.setFill(Color.WHITE);

        VBox moreInformationContainer = new VBox(moreInformationText, svdjLink);
        moreInformationContainer.setMaxWidth(INFORMATION_WIDTH);
        moreInformationContainer.setStyle(paddingSize(8));


        VBox homeButtonContainer = new VBox();
        homeButtonContainer.setPrefHeight(HOME_CONTAINER_HEIGHT);
        homeButtonContainer.setMaxWidth(HOME_CONTAINER_WIDTH);
        homeButtonContainer.setAlignment(Pos.CENTER_LEFT);
        Button homeButton = new Button("Home");
        homeButton.setFont(Font.font(FONT_FAMILY, FontPosture.REGULAR, BUTTON_FONT_SIZE));
        homeButton.setTextFill(Color.BLACK);
        homeButton.setAlignment(Pos.CENTER_LEFT);
        homeButton.setPadding(new Insets(BUTTON_PADDING));
        homeButton.setPrefWidth(BUTTON_WIDTH);
        homeButton.setStyle(String.format(FX_BACKGROUND_COLOR, BUTTON_COLOR) + "-fx-background-radius: 0;");
        homeButton.setOnMouseEntered(e -> {
            homeButton.setStyle(String.format(FX_BACKGROUND_COLOR, HOVER_BUTTON_COLOR) + "-fx-background-radius: 0;");
        });
        homeButton.setOnMouseExited(e -> {
            homeButton.setStyle(String.format(FX_BACKGROUND_COLOR, BUTTON_COLOR) + "-fx-background-radius: 0;");
        });
        homeButton.setOnMouseClicked(e -> {
            stage.setScene(new Scene(new HomeView().getHomePane()));
            System.out.println("Veranderd naar HomeView");
        });
        homeButtonContainer.getChildren().addAll(homeButton);


        VBox adviceVideoContainer = new VBox();
        adviceVideoContainer.setMaxWidth(ADVICE_WIDTH);
        adviceVideoContainer.setAlignment(Pos.CENTER);
        Button videoButton = new Button("Subsidie introductie video bekijken");
        videoButton.setFont(Font.font(FONT_FAMILY, FontPosture.REGULAR, DEFAULT_TEXT_FONT_SIZE));
        videoButton.setTextFill(Color.BLACK);
        videoButton.setAlignment(Pos.CENTER_LEFT);
        videoButton.setPadding(new Insets(BUTTON_PADDING));
        videoButton.setPrefWidth(BUTTON_WIDTH);
        videoButton.setStyle(String.format(FX_BACKGROUND_COLOR, BUTTON_COLOR) + "-fx-background-radius: 0;");
        videoButton.setOnMouseEntered(e -> {
            videoButton.setStyle(String.format(FX_BACKGROUND_COLOR, HOVER_BUTTON_COLOR) + "-fx-background-radius: 0;");
        });
        videoButton.setOnMouseExited(e -> {
            videoButton.setStyle(String.format(FX_BACKGROUND_COLOR, BUTTON_COLOR) + "-fx-background-radius: 0;");
        });
        videoButton.setOnMouseClicked(e -> {
            try {
                Desktop.getDesktop().browse(new URI(VIDEO_URL));
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
            System.out.println("Er is op de video geklikt");
        });
        adviceVideoContainer.getChildren().addAll(videoButton);


        VBox contactContainer = new VBox();
        contactContainer.setMaxWidth(CONTACT_WIDTH);
        contactContainer.setAlignment(Pos.CENTER);
        Button contactButton = new Button("Ik wil graag contact opnemen");
        contactButton.setFont(Font.font(FONT_FAMILY, FontPosture.REGULAR, DEFAULT_TEXT_FONT_SIZE));
        contactButton.setTextFill(Color.BLACK);
        contactButton.setAlignment(Pos.CENTER_LEFT);
        contactButton.setPadding(new Insets(BUTTON_PADDING));
        contactButton.setPrefWidth(BUTTON_WIDTH);
        contactButton.setStyle(String.format(FX_BACKGROUND_COLOR, BUTTON_COLOR) + "-fx-background-radius: 0;");
        contactButton.setOnMouseEntered(e -> {
            contactButton.setStyle(String.format(FX_BACKGROUND_COLOR, HOVER_BUTTON_COLOR) + "-fx-background-radius: 0;");
        });
        contactButton.setOnMouseExited(e -> {
            contactButton.setStyle(String.format(FX_BACKGROUND_COLOR, BUTTON_COLOR) + "-fx-background-radius: 0;");
        });
        //TODO verander naar website
        contactButton.setOnMouseClicked(e -> {
            try {
                Desktop.getDesktop().browse(new URI(CONTACT_URL));
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
            System.out.println("Er is geklikt op contact");
        });
        contactContainer.getChildren().addAll(contactButton);


        HBox sendEmailContainer = new HBox();
        sendEmailContainer.setMaxWidth(EMAIL_CONTAINER_WIDTH);
        sendEmailContainer.setAlignment(Pos.CENTER);
        TextField emailTextfield = new TextField();
        emailTextfield.setPrefWidth(EMAIL_TEXTFIELD_WIDTH);
        emailTextfield.setPrefHeight(EMAIL_TEXTFIELD_HEIGHT);
        emailTextfield.setPromptText("Mail mijn advies!");
        emailTextfield.setStyle("-fx-background-radius: 0;");

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

        Button sendEmailButton = new Button("",imageView);
        sendEmailButton.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, DEFAULT_TEXT_FONT_SIZE));
        sendEmailButton.setTextFill(Color.BLACK);
        sendEmailButton.setAlignment(Pos.CENTER_LEFT);
        sendEmailButton.setPadding(new Insets(BUTTON_PADDING));
        sendEmailButton.setStyle(String.format(FX_BACKGROUND_COLOR, BUTTON_COLOR) + "-fx-background-radius: 0;");
        sendEmailButton.setOnMouseEntered(e -> {
            sendEmailButton.setStyle(String.format(FX_BACKGROUND_COLOR, HOVER_BUTTON_COLOR) + "-fx-background-radius: 0;");
        });
        sendEmailButton.setOnMouseExited(e -> {
            sendEmailButton.setStyle(String.format(FX_BACKGROUND_COLOR, BUTTON_COLOR) + "-fx-background-radius: 0;");
        });
        sendEmailButton.setOnMouseClicked(e -> {
//            dashboardController.navigateEditView();

            System.out.println("Email is verstuurd");
        });
        sendEmailContainer.getChildren().addAll(emailTextfield, sendEmailButton);


        VBox textContainer = new VBox(TEXT_CONTAINER_PADDING);
        textContainer.setMaxWidth(TEXT_CONTAINER_WIDTH);
        textContainer.setMinHeight(TEXT_CONTAINER_HEIGHT);
        textContainer.getChildren().addAll(adviceContainer, descriptionContainer, moreInformationContainer,homeButtonContainer);


        VBox buttonContainer = new VBox(BUTTON_CONTAINER_PADDING);
        buttonContainer.setPrefWidth(BUTTON_CONTAINER_WIDTH);
        buttonContainer.setAlignment(Pos.BOTTOM_CENTER);
        buttonContainer.getChildren().addAll(adviceVideoContainer, contactContainer, sendEmailContainer);


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


    public AdviceView(int adviceId){
        adviceController.registerObserver(this);
        adviceController.loadAdvice(adviceId);
    }


    @Override
    public void update(Advice advice) {

    }
}
