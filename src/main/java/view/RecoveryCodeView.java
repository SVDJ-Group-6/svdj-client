package view;

import controller.RecoveryCodeController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.RecoveryCode;
import observer.RecoveryCodeObserver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RecoveryCodeView implements RecoveryCodeObserver {
    private RecoveryCodeController recoveryCodeController = RecoveryCodeController.getInstance();
    private Text message = new Text();

    public RecoveryCodeView() {
        recoveryCodeController.registerObserver(this);
    }

    public Scene getRecoveryCodeView() {
        final double buttonPadding = 17.5;
        final int headerFontSize = 64;
        final int buttonFontSize = 22;
        final int messageFontSize = 20;

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

        Image logoImage = new Image(getClass().getClassLoader().getResourceAsStream("logo.png"), logoWidth, logoHeight, true, false);
        ImageView logoImageView = new ImageView(logoImage);
        logoHeader.getChildren().add(logoImageView);

        Text headerText = new Text("Check your mail for recovery code");
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


        TextField RecoveryCode = new TextField();
        RecoveryCode.setFont(Font.font(fontFamily, FontWeight.BOLD, buttonFontSize));
        RecoveryCode.setPrefWidth(867);
        RecoveryCode.setPadding(new Insets(buttonPadding));
        RecoveryCode.setPromptText("Recovery Code");
        RecoveryCode.setFocusTraversable(false);

        VBox loginFormBox = new VBox(20);
        loginFormBox.setAlignment(Pos.CENTER);
        loginFormBox.setMaxWidth(900);
        loginFormBox.setPadding(new Insets(0, 0, 0, 0));
        loginFormBox.getChildren().addAll(RecoveryCode);

        Button ChangePassword = new Button("Next");
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
            String recoveryCode = RecoveryCode.getText();
            recoveryCodeController.checkRecoveryCode(recoveryCode);
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
        Image backgroundImage = new Image(getClass().getClassLoader().getResourceAsStream("background.png"));

        root.setBackground(new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        root.getChildren().addAll(logoHeader, headerContainer, loginFormBox, actionList);

        return new Scene(root);
    }

    @Override
    public void update(RecoveryCode recoveryCode) {
        if (!recoveryCode.getMatching()) {
            message.setText("Recovery code is incorrect!");
        }
    }
}
