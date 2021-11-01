package view;

import controller.ChangePasswordController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.ChangePassword;
import observer.ChangePasswordObserver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Client.ClientVariables;

public class ChangePasswordView implements ChangePasswordObserver {
    private ChangePasswordController changePasswordController = ChangePasswordController.getInstance();
    private String recoverycode;
    private Text message = new Text();


    public ChangePasswordView(String code){
        this.recoverycode = code;
        this.changePasswordController.registerObserver(this);
    }

    public Scene getChangePasswordScene() {
        final double buttonPadding = 17.5;
        final int headerFontSize = 64;
        final int buttonFontSize = 22;
        final int messageFontSize = 20;

        final int logoWidth = 480;
        final int logoHeight = 128;

        final int gridHGap = 75;
        final int gridVGap = 50;

        final String fontFamily = "Arial";

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

        Text headerText = new Text("Change Password");
        headerText.setFont(Font.font(fontFamily, FontWeight.NORMAL, headerFontSize));
        headerText.setFill(Color.WHITE);
        headerText.setTextAlignment(TextAlignment.CENTER);

        message.setFont(Font.font(fontFamily, FontWeight.NORMAL, messageFontSize));
        message.setFill(Color.WHITE);
        message.setTextAlignment(TextAlignment.CENTER);

        VBox headerContainer = new VBox();
        headerContainer.setPadding(new Insets(25, 0, 0, 0));
        headerContainer.setAlignment(Pos.CENTER);
        headerContainer.getChildren().addAll(headerText,message);

        PasswordField NewPassword = new PasswordField();
        NewPassword.setFont(Font.font(fontFamily, FontWeight.BOLD, buttonFontSize));
        NewPassword.setPrefWidth(867);
        NewPassword.setPadding(new Insets(buttonPadding));
        NewPassword.setPromptText("Password");
        NewPassword.setFocusTraversable(false);

        PasswordField NewPassword2 = new PasswordField();
        NewPassword2.setFont(Font.font(fontFamily, FontWeight.BOLD, buttonFontSize));
        NewPassword2.setPrefWidth(867);
        NewPassword2.setPadding(new Insets(buttonPadding));
        NewPassword2.setPromptText("Write Password again");
        NewPassword2.setFocusTraversable(false);

        VBox loginFormBox = new VBox(20);
        loginFormBox.setAlignment(Pos.CENTER);
        loginFormBox.setMaxWidth(900);
        loginFormBox.setPadding(new Insets(25, 0, 0, 0));
        loginFormBox.getChildren().addAll(NewPassword, NewPassword2);

        Button ChangePassword = new Button("Change Password");
        ChangePassword.setFont(Font.font(fontFamily, FontWeight.BOLD, buttonFontSize));
        ChangePassword.setTextFill(Color.BLACK);
        ChangePassword.setAlignment(Pos.CENTER_LEFT);
        ChangePassword.setPadding(new Insets(buttonPadding));
        ChangePassword.setPrefWidth(326);
        ChangePassword.setStyle(String.format("-fx-background-color: %s;", "#E4F6FF"));
        ChangePassword.setOnMouseEntered(e -> {
            ChangePassword.setStyle(String.format("-fx-background-color: %s;", "#9CC2D4"));
        });
        ChangePassword.setOnMouseExited(e -> {
            ChangePassword.setStyle(String.format("-fx-background-color: %s;", "#E4F6FF"));
        });
        ChangePassword.setOnMouseClicked(e -> {
            String password1 = NewPassword.getText();
            String password2 = NewPassword2.getText();
            if(changePasswordController.checkIdenticalPasswords(password1, password2)){
                changePasswordController.changePassword(password1,recoverycode);
            }else{
                changePasswordController.setMessage("Password is not identical.");
            }
        });

        GridPane actionList = new GridPane();
        actionList.setPadding(new Insets(25, 150, 100, 75));
        actionList.setAlignment(Pos.CENTER);
        actionList.setHgap(gridHGap);
        actionList.setVgap(gridVGap);
        actionList.add(ChangePassword, 1, 0);

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


    @Override
    public void update(ChangePassword changePassword) {
        message.setText(changePassword.getMessage());
    }
}
