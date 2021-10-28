package view;

import controller.EmailController;
import controller.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class EmailView {

    private EmailController emailController = EmailController.getInstance();

    public Scene getEmailPane() {

        final double buttonPadding = 17.5;
        final int headerFontSize = 64;
        final int buttonFontSize = 22;

        final int logoWidth = 480;
        final int logoHeight = 128;

        final int gridHGap = 75;
        final int gridVGap = 50;

        /* Begin Temporary */
        final String buttonColor = "#E4F6FF";
        final String hoverButtonColor = "#9CC2D4";

        final String fontFamily = "Arial";
        /* End Temporary */

        FileInputStream logoInput, backgroundInput;

        HBox logoHeader = new HBox();
        logoHeader
                .setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        try {
            logoInput = new FileInputStream("./src/main/resources/logo.png");
            Image logoImage = new Image(logoInput, logoWidth, logoHeight, true, false);
            ImageView logoImageView = new ImageView(logoImage);
            logoHeader.getChildren().add(logoImageView);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Text headerText = new Text("Wachtwoord vergeten");
        headerText.setFont(Font.font(fontFamily, FontWeight.NORMAL, headerFontSize));
        headerText.setFill(Color.WHITE);
        headerText.setTextAlignment(TextAlignment.CENTER);

        VBox headerContainer = new VBox();
        headerContainer.setPadding(new Insets(25, 0, 0, 0));
        headerContainer.setAlignment(Pos.CENTER);
        headerContainer.getChildren().add(headerText);

        TextField emailField = new TextField();
        emailField.setFont(Font.font(fontFamily, FontWeight.BOLD, buttonFontSize));
        emailField.setPrefWidth(700);
        emailField.setPadding(new Insets(buttonPadding));
        emailField.setPromptText("Vul hier je e-mail in");
        emailField.setFocusTraversable(false);

        VBox loginFormBox = new VBox(20);
        loginFormBox.setAlignment(Pos.CENTER);
        loginFormBox.setMaxWidth(750);
        loginFormBox.setPadding(new Insets(25, 0, 0, 0));
        loginFormBox.getChildren().addAll(emailField);

        Button backButton = new Button("Back");
        backButton.setFont(Font.font(fontFamily, FontWeight.BOLD, buttonFontSize));
        backButton.setTextFill(Color.BLACK);
        backButton.setAlignment(Pos.CENTER_LEFT);
        backButton.setPadding(new Insets(buttonPadding));
        backButton.setPrefWidth(326);
        backButton.setStyle(String.format("-fx-background-color: %s;", buttonColor));
        backButton.setOnMouseEntered(e -> {
            backButton.setStyle(String.format("-fx-background-color: %s;", hoverButtonColor));
        });
        backButton.setOnMouseExited(e -> {
            backButton.setStyle(String.format("-fx-background-color: %s;", buttonColor));
        });
        backButton.setOnMouseClicked(e -> {

            emailController.switchToLogin();
        });

        Button requestButton = new Button("Request");
        requestButton.setFont(Font.font(fontFamily, FontWeight.BOLD, buttonFontSize));
        requestButton.setTextFill(Color.BLACK);
        requestButton.setAlignment(Pos.CENTER_LEFT);
        requestButton.setPadding(new Insets(buttonPadding));
        requestButton.setPrefWidth(326);
        requestButton.setStyle(String.format("-fx-background-color: %s;", buttonColor));
        requestButton.setOnMouseEntered(e -> {
            requestButton.setStyle(String.format("-fx-background-color: %s;", hoverButtonColor));
        });
        requestButton.setOnMouseExited(e -> {
            requestButton.setStyle(String.format("-fx-background-color: %s;", buttonColor));
        });
        requestButton.setOnMouseClicked(e -> {
//TODO verander naar recovery code scene


        });

        GridPane actionList = new GridPane();
        actionList.setPadding(new Insets(25, 150, 100, 75));
        actionList.setAlignment(Pos.CENTER);
        actionList.setHgap(gridHGap);
        actionList.setVgap(gridVGap);
        actionList.add(backButton, 1, 0);
        actionList.add(requestButton, 2, 0);

        VBox root = new VBox(25);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(25));
        try {
            backgroundInput = new FileInputStream("./src/main/resources/background.png");
            Image backgroundImage = new Image(backgroundInput);

            root.setBackground(new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        root.getChildren().addAll(logoHeader, headerContainer, loginFormBox, actionList);

        return new Scene(root);
    }

}


