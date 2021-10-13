package view;

import controller.AdviceController;
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
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;
import model.Advice;
import observer.AdviceObserver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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

        BackgroundSize backgroundSize = new BackgroundSize(1280, 720, true, true, true, false);
        BackgroundImage background = null;
        try {
            background = new BackgroundImage(new Image(new FileInputStream("./src/main/resources/background.png")),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
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
        titleContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        Text title = new Text();
        title.setText("Bedankt voor het gebruiken van ons applicatie");
        title.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 50));
        title.setFill(Color.WHITE);
//        titleContainer.setAlignment(Pos.CENTER);
        titleContainer.getChildren().addAll(title);

        //TODO moet nog naar database connecten om de advies te halen
        VBox adviceContainer = new VBox();
        adviceContainer.setMaxWidth(670);
        adviceContainer.setStyle("-fx-padding: 16;");
        adviceContainer.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        Text adviceText = new Text();
        String advice = "'Talent ontwikkeling'";
        adviceText.setWrappingWidth(650);
        adviceText.setText("Uit het vragelijst is gebleken dat u mogelijk in aanmerking kom voor " + advice);
        adviceText.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
        adviceText.setFill(Color.WHITE);
        adviceContainer.getChildren().addAll(adviceText);

        VBox descriptionContainer = new VBox();
        descriptionContainer.setMaxWidth(800);
        descriptionContainer.setStyle("-fx-padding: 16;");
        descriptionContainer.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        Text descriptionText = new Text();
        descriptionText.setWrappingWidth(800);
        descriptionText.setText("Lorem lorem Lorem lorem Lorem lorem Lorem lorem Lorfdfdsfdsfdsfdsfd fdfdsf dfehfh dsf hk fhjkdshjk f df dhf hkdfhkdsf hjkd fhjk dshjkfds hjkfdsfdshjkf dhjklsfdshjklf dshjkfdsjkfem lorem LoreLorem lorem Lorem lorem Lorem lorem Lorem lorem Lorfdfdsfdsfdsfdsfd fdfdsf dfehfh dsf hk fhjkdshjk f df dhf hkdfhkdsf hjkd fhjk dshjkfds hjkfdsfdshjkf dhjklsfdshjklf dshjkfdsjkfem lorem LoreLorem lorem Lorem lorem Lorem lorem Lorem lorem Lorfdfdsfdsfdsfdsfd fdfdsf dfehfh dsf hk fhjkdshjk f df dhf hkdfhkdsf hjkd fhjk dshjkfds hjkfdsfdshjkf dhjklsfdshjklf dshjkfdsjkfem lorem LoreLorem lorem Lorem lorem Lorem lorem Lorem lorem Lorfdfdsfdsfdsfdsfd fdfdsf dfehfh dsf hk fhjkdshjk f df dhf hkdfhkdsf hjkd fhjk.Lorem lorem Lorem lorem Lorem lorem Lorfdfdsfdsfdsfdsfd fdfdsf dfehfh dsf hk fhjkdshjk f df dhf hkdfhkdsf hjkd fhjk.");
        descriptionText.setFont(Font.font("Arial", FontPosture.REGULAR, 16));
        descriptionText.setFill(Color.WHITE);
        descriptionContainer.getChildren().addAll(descriptionText);

        VBox moreInformationContainer = new VBox();
        moreInformationContainer.setMaxWidth(300);
        moreInformationContainer.setStyle("-fx-padding: 16;");
        moreInformationContainer.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
//        Hyperlink link = new Hyperlink("hier!");
        Text moreInformationText = new Text();
        moreInformationText.setWrappingWidth(300);
        moreInformationText.setText("Voor meer informatie klik ");
        moreInformationText.setFont(Font.font("Arial", FontPosture.REGULAR, 16));
        moreInformationText.setFill(Color.WHITE);
//        moreInformationContainer.getChildren().addAll(moreInformationText, link);

        VBox homeButtonContainer = new VBox();
        homeButtonContainer.setPrefHeight(100);
        homeButtonContainer.setMaxWidth(400);
        homeButtonContainer.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        Button homeButton = new Button("Home");
        homeButton.setFont(Font.font(fontFamily, FontWeight.BOLD, buttonFontSize));
        homeButton.setTextFill(Color.BLACK);
        homeButton.setAlignment(Pos.CENTER_LEFT);
        homeButton.setPadding(new Insets(buttonPadding));
        homeButton.setPrefWidth(326);
        homeButton.setStyle(String.format("-fx-background-color: %s;", buttonColor));
        homeButton.setOnMouseEntered(e -> {
            homeButton.setStyle(String.format("-fx-background-color: %s;", hoverButtonColor));
        });
        homeButton.setOnMouseExited(e -> {
            homeButton.setStyle(String.format("-fx-background-color: %s;", buttonColor));
        });
        homeButton.setOnMouseClicked(e -> {
//            dashboardController.navigateEditView();
            System.out.println("Er is geklikt");
        });
        homeButtonContainer.getChildren().addAll(homeButton);

        VBox layout = new VBox(10);
        layout.setBackground(new Background(background));
        layout.setPadding(new Insets(25));
        layout.getChildren().addAll(logoView, titleContainer, adviceContainer, descriptionContainer, moreInformationContainer,homeButtonContainer);

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
