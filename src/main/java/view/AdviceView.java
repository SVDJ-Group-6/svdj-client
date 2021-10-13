package view;

import controller.AdviceController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.layout.StackPane;
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
    private Advice advice;


    public AdviceView() {

    }

    public VBox getAdvicePane() {
        final double buttonPadding = 17.5;
        final int headerFontSize = 64;
        final int buttonFontSize = 22;

        final int logoWidth = 480;
        final int logoHeight = 128;

        final int gridHGap = 75;
        final int gridVGap = 50;

//        /* Begin Temporary /
        final String buttonColor = "#9CC2D4";
        final String hoverButtonColor = "#E4F6FF";

        final String logoutButtonColor = "#FFFFFF";

        final String fontFamily = "Arial";
//        / End Temporary */

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
        logoView.setFitHeight(128);
        logoView.setFitWidth(480);
        logoView.setPreserveRatio(true);


        VBox titleContainer = new VBox();
        titleContainer.setStyle("-fx-padding: 16;");
//        titleContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        Text title = new Text();
        title.setText("Bedankt voor het gebruiken van ons applicatie");
        title.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 50));
        title.setFill(Color.WHITE);
//        titleContainer.setAlignment(Pos.CENTER);
        titleContainer.getChildren().addAll(title);



        //TODO moet nog naar database connecten om de advies te halen
        VBox adviceContainer = new VBox();
        adviceContainer.setMaxWidth(670);
        adviceContainer.setStyle("-fx-padding: 10;");
//        adviceContainer.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        Text adviceText = new Text();
        String advice = "'Talent ontwikkeling'";
        adviceText.setWrappingWidth(650);
        adviceText.setText("Uit het vragenlijst is gebleken dat u mogelijk in aanmerking kom voor " + advice);
        adviceText.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
        adviceText.setFill(Color.WHITE);
        adviceContainer.getChildren().addAll(adviceText);

        VBox descriptionContainer = new VBox();
        descriptionContainer.setMaxWidth(800);
        descriptionContainer.setStyle("-fx-padding: 10;");
//        descriptionContainer.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        Text descriptionText = new Text();
        descriptionText.setWrappingWidth(800);
        descriptionText.setText("Lorem lorem Lorem lorem Lorem lorem Lorem lorem Lorfdfdsfdsfdsfdsfd fdfdsf dfehfh dsf hk fhjkdshjk f df dhf hkdfhkdsf hjkd fhjk dshjkfds hjkfdsfdshjkf dhjklsfdshjklf dshjkfdsjkfem lorem LoreLorem lorem Lorem lorem Lorem lorem Lorem lorem Lorfdfdsfdsfdsfdsfd fdfdsf dfehfh dsf hk fhjkdshjk f df dhf hkdfhkdsf hjkd fhjk dshjkfds hjkfdsfdshjkf dhjklsfdshjklf dshjkfdsjkfem lorem LoreLorem lorem Lorem lorem Lorem lorem Lorem lorem Lorfdfdsfdsfdsfdsfd fdfdsf dfehfh dsf hk fhjkdshjk f df dhf hkdfhkdsf hjkd fhjk dshjkfds hjkfdsfdshjkf dhjklsfdshjklf dshjkfdsjkfem lorem LoreLorem lorem Lorem lorem Lorem lorem Lorem lorem Lorfdfdsfdsfdsfdsfd fdfdsf dfehfh dsf hk fhjkdshjk f df dhf hkdfhkdsf hjkd fhjk.Lorem lorem Lorem lorem Lorem lorem Lorfdfdsfdsfdsfdsfd fdfdsf dfehfh dsf hk fhjkdshjk f df dhf hkdfhkdsf hjkd fhjk.");
        descriptionText.setFont(Font.font("Arial", FontPosture.REGULAR, 16));
        descriptionText.setFill(Color.WHITE);
        descriptionContainer.getChildren().addAll(descriptionText);

        Hyperlink svdjLink = new Hyperlink("www.svdj.nl");
        svdjLink.setFont(Font.font("Arial", FontPosture.REGULAR, 16));
        svdjLink.setOnAction(e-> {
            try {
                svdjLink.setVisited(false);
                Desktop.getDesktop().browse(new URI("https://www.svdj.nl/"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (URISyntaxException uriSyntaxException) {
                uriSyntaxException.printStackTrace();
            }

        });
        svdjLink.setStyle("-fx-border-color: transparent;");
//        svdjLink.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));

        Text moreInformationText = new Text("Voor meer informatie, ga naar: ");
//        moreInformationText.setWrappingWidth(300);
        moreInformationText.setFont(Font.font("Arial", FontPosture.REGULAR, 16));
        moreInformationText.setFill(Color.WHITE);

        VBox moreInformationContainer = new VBox(moreInformationText, svdjLink);
        moreInformationContainer.setMaxWidth(500);
        moreInformationContainer.setStyle("-fx-padding: 8;");
//        moreInformationContainer.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));

//        moreInformationContainer.getChildren().addAll(moreInformationText, svdjLink);

        VBox homeButtonContainer = new VBox();
        homeButtonContainer.setPrefHeight(100);
        homeButtonContainer.setMaxWidth(400);
        homeButtonContainer.setAlignment(Pos.CENTER_LEFT);
//        homeButtonContainer.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        Button homeButton = new Button("Home");
        homeButton.setFont(Font.font(fontFamily, FontPosture.REGULAR, buttonFontSize));
        homeButton.setTextFill(Color.BLACK);
        homeButton.setAlignment(Pos.CENTER_LEFT);
        homeButton.setPadding(new Insets(buttonPadding));
        homeButton.setPrefWidth(326);

        homeButton.setStyle(String.format("-fx-background-color: %s;", buttonColor) + "-fx-background-radius: 0;");
        homeButton.setOnMouseEntered(e -> {
            homeButton.setStyle(String.format("-fx-background-color: %s;", hoverButtonColor) + "-fx-background-radius: 0;");
        });
        homeButton.setOnMouseExited(e -> {
            homeButton.setStyle(String.format("-fx-background-color: %s;", buttonColor) + "-fx-background-radius: 0;");
        });
        homeButton.setOnMouseClicked(e -> {
//            dashboardController.navigateEditView();
            System.out.println("Er is geklikt");
        });
        homeButtonContainer.getChildren().addAll(homeButton);

        VBox adviceVideoContainer = new VBox();
//        adviceVideoContainer.setPrefHeight(100);
        adviceVideoContainer.setMaxWidth(400);
        adviceVideoContainer.setAlignment(Pos.CENTER);
//        adviceVideoContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        Button videoButton = new Button("Subsidie introductie video bekijken");
        videoButton.setFont(Font.font(fontFamily, FontPosture.REGULAR, 16));
        videoButton.setTextFill(Color.BLACK);
        videoButton.setAlignment(Pos.CENTER_LEFT);
        videoButton.setPadding(new Insets(buttonPadding));
        videoButton.setPrefWidth(326);
        videoButton.setStyle(String.format("-fx-background-color: %s;", buttonColor) + "-fx-background-radius: 0;");
        videoButton.setOnMouseEntered(e -> {
            videoButton.setStyle(String.format("-fx-background-color: %s;", hoverButtonColor) + "-fx-background-radius: 0;");
        });
        videoButton.setOnMouseExited(e -> {
            videoButton.setStyle(String.format("-fx-background-color: %s;", buttonColor) + "-fx-background-radius: 0;");
        });
        videoButton.setOnMouseClicked(e -> {
//            dashboardController.navigateEditView();
            System.out.println("Er is geklikt");
        });
        adviceVideoContainer.getChildren().addAll(videoButton);

        VBox contactContainer = new VBox();
//        contactContainer.setPrefHeight(100);
        contactContainer.setMaxWidth(400);
        contactContainer.setAlignment(Pos.CENTER);
//        contactContainer.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        Button contactButton = new Button("Ik wil graag contact opnemen");
//        contactButton.setStyle("-fx-border-radius: 0px;");
        contactButton.setFont(Font.font(fontFamily, FontPosture.REGULAR, 16));
        contactButton.setTextFill(Color.BLACK);
        contactButton.setAlignment(Pos.CENTER_LEFT);
        contactButton.setPadding(new Insets(buttonPadding));
        contactButton.setPrefWidth(326);
        contactButton.setStyle(String.format("-fx-background-color: %s;", buttonColor) + "-fx-background-radius: 0;");
        contactButton.setOnMouseEntered(e -> {
            contactButton.setStyle(String.format("-fx-background-color: %s;", hoverButtonColor) + "-fx-background-radius: 0;");
        });
        contactButton.setOnMouseExited(e -> {
            contactButton.setStyle(String.format("-fx-background-color: %s;", buttonColor) + "-fx-background-radius: 0;");
        });
        contactButton.setOnMouseClicked(e -> {
//            dashboardController.navigateEditView();
            System.out.println("Er is geklikt op contact");
        });
        contactContainer.getChildren().addAll(contactButton);

        HBox sendEmailContainer = new HBox();
//        contactContainer.setPrefHeight(100);
        sendEmailContainer.setMaxWidth(400);
        sendEmailContainer.setAlignment(Pos.CENTER);
//        sendEmailContainer.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        TextField emailTextfield = new TextField();
        emailTextfield.setPrefWidth(270);
        emailTextfield.setPrefHeight(59);
        emailTextfield.setPromptText("John.Doe@gmail.com");
        emailTextfield.setStyle("-fx-background-radius: 0;");
        FileInputStream input = null;
        try {
            input = new FileInputStream("./src/main/resources/email_logo.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(22);
        imageView.setFitWidth(22);
        Button sendEmailButton = new Button("",imageView);
        sendEmailButton.setFont(Font.font(fontFamily, FontWeight.BOLD, 16));
        sendEmailButton.setTextFill(Color.BLACK);
        sendEmailButton.setAlignment(Pos.CENTER_LEFT);
        sendEmailButton.setPadding(new Insets(buttonPadding));
//        sendEmailButton.setMaxSize(20,20);
        sendEmailButton.setStyle("-fx-font: 5 arial;");
        sendEmailButton.setStyle(String.format("-fx-background-color: %s;", buttonColor) + "-fx-background-radius: 0;");
        sendEmailButton.setOnMouseEntered(e -> {
            sendEmailButton.setStyle(String.format("-fx-background-color: %s;", hoverButtonColor) + "-fx-background-radius: 0;");
        });
        sendEmailButton.setOnMouseExited(e -> {
            sendEmailButton.setStyle(String.format("-fx-background-color: %s;", buttonColor) + "-fx-background-radius: 0;");
        });
        sendEmailButton.setOnMouseClicked(e -> {
//            dashboardController.navigateEditView();
            System.out.println("Email is verstuurd");
        });
        sendEmailContainer.getChildren().addAll(emailTextfield, sendEmailButton);

        VBox textContainer = new VBox(10);
//        textContainer.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        textContainer.setMaxWidth(800);
        textContainer.setMinHeight(400);
        textContainer.getChildren().addAll(adviceContainer, descriptionContainer, moreInformationContainer,homeButtonContainer);

        VBox buttonContainer = new VBox(30);
//        buttonContainer.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        buttonContainer.setPrefWidth(500);
        buttonContainer.setAlignment(Pos.BOTTOM_CENTER);
        buttonContainer.getChildren().addAll(adviceVideoContainer, contactContainer, sendEmailContainer);

        HBox bodyContainer = new HBox(30);
        bodyContainer.getChildren().addAll(textContainer, buttonContainer);

        VBox layout = new VBox(10);
        layout.setBackground(new Background(background));
        layout.setPadding(new Insets(25));
//        layout.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        layout.getChildren().addAll(logoView, titleContainer, bodyContainer);

        return layout;
    }


    public AdviceView(int adviceId){
        adviceController.registerObserver(this);
        adviceController.loadAdvice(adviceId);
    }


    @Override
    public void update(Advice advice) {

    }
}
