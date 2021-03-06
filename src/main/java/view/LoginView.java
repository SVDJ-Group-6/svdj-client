package view;

import controller.LoginController;
import observer.LoginObserver;
import model.Login;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
public class LoginView implements LoginObserver {

    private final Text message;
    private final LoginController loginController;

    public LoginView() {
        this.message = new Text();
        this.loginController = LoginController.getInstance();

        this.loginController.registerObserver(this);
    }

    public Scene getLoginScene() {
        final double buttonPadding = 17.5;
        final int headerFontSize = 64;
        final int buttonFontSize = 22;
        final int messageFontSize = 20;

        final int logoWidth = 480;
        final int logoHeight = 128;

        final int gridHGap = 75;
        final int gridVGap = 50;

        final String buttonColor = "#E4F6FF";
        final String hoverButtonColor = "#9CC2D4";

        final String fontFamily = "Arial";

        InputStream logoInput;
        FileInputStream backgroundInput;

        HBox logoHeader = new HBox();
        logoHeader
                .setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        logoInput = getClass().getClassLoader().getResourceAsStream("logo.png");
        Image logoImage = new Image(logoInput, logoWidth, logoHeight, true, false);
        ImageView logoImageView = new ImageView(logoImage);
        logoHeader.getChildren().add(logoImageView);

        Text headerText = new Text("Admin login");
        headerText.setFont(Font.font(fontFamily, FontWeight.NORMAL, headerFontSize));
        headerText.setFill(Color.WHITE);
        headerText.setTextAlignment(TextAlignment.CENTER);

        message.setFont(Font.font(fontFamily, FontWeight.NORMAL, messageFontSize));
        message.setFill(Color.WHITE);
        message.setTextAlignment(TextAlignment.CENTER);

        VBox headerContainer = new VBox();
        headerContainer.setPadding(new Insets(25, 0, 0, 0));
        headerContainer.setAlignment(Pos.CENTER);
        headerContainer.getChildren().add(headerText);
        headerContainer.getChildren().add(message);

        TextField usernameField = new TextField();
        usernameField.setFont(Font.font(fontFamily, FontWeight.BOLD, buttonFontSize));
        usernameField.setPrefWidth(867);
        usernameField.setPadding(new Insets(buttonPadding));
        usernameField.setPromptText("Gebruikersnaam");
        usernameField.setFocusTraversable(false);

        PasswordField passwordField = new PasswordField();
        passwordField.setFont(Font.font(fontFamily, FontWeight.BOLD, buttonFontSize));
        passwordField.setPrefWidth(867);
        passwordField.setPadding(new Insets(buttonPadding));
        passwordField.setPromptText("Password");
        passwordField.setFocusTraversable(false);

        VBox loginFormBox = new VBox(20);
        loginFormBox.setAlignment(Pos.CENTER);
        loginFormBox.setMaxWidth(900);
        loginFormBox.setPadding(new Insets(0, 0, 0, 0));
        loginFormBox.getChildren().addAll(usernameField, passwordField);

        Button loginButton = new Button("Login");
        loginButton.setFont(Font.font(fontFamily, FontWeight.BOLD, buttonFontSize));
        loginButton.setTextFill(Color.BLACK);
        loginButton.setAlignment(Pos.CENTER_LEFT);
        loginButton.setPadding(new Insets(buttonPadding));
        loginButton.setPrefWidth(326);
        loginButton.setStyle(String.format("-fx-background-color: %s;", buttonColor));
        loginButton.setOnMouseEntered(e -> {
            loginButton.setStyle(String.format("-fx-background-color: %s;", hoverButtonColor));
        });
        loginButton.setOnMouseExited(e -> {
            loginButton.setStyle(String.format("-fx-background-color: %s;", buttonColor));
        });
        loginButton.setOnMouseClicked(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            loginController.login(username, password);
        
            passwordField.setText("");
        });

        Button ChangePassword = new Button("Change Password");
        ChangePassword.setFont(Font.font(fontFamily, FontWeight.BOLD, buttonFontSize));
        ChangePassword.setTextFill(Color.BLACK);
        ChangePassword.setAlignment(Pos.CENTER_LEFT);
        ChangePassword.setPadding(new Insets(buttonPadding));
        ChangePassword.setPrefWidth(326);
        ChangePassword.setStyle(String.format("-fx-background-color: %s;", buttonColor));
        ChangePassword.setOnMouseEntered(e -> {
            ChangePassword.setStyle(String.format("-fx-background-color: %s;", hoverButtonColor));
        });
        ChangePassword.setOnMouseExited(e -> {
            ChangePassword.setStyle(String.format("-fx-background-color: %s;", buttonColor));
        });
        ChangePassword.setOnMouseClicked(e -> {

            loginController.switchToEmail();
        });

        GridPane actionList = new GridPane();
        actionList.setPadding(new Insets(25, 150, 100, 75));
        actionList.setAlignment(Pos.CENTER);
        actionList.setHgap(gridHGap);
        actionList.setVgap(gridVGap);
        actionList.add(loginButton, 1, 0);
        actionList.add(ChangePassword, 2, 0);

        VBox root = new VBox(25);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(25));
        Image backgroundImage = new Image(getClass().getClassLoader().getResourceAsStream("background.png"));

        root.setBackground(new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        root.getChildren().addAll(logoHeader, headerContainer, loginFormBox, actionList);

        return new Scene(root);
    }

    @Override
    public void update(Login login) {
        message.setText(login.getMessage());
    }
}
