package view;

import Admin.AdminVariables;
import controller.DashboardController;

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

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class DashboardView {
    private DashboardController dashboardController = DashboardController.getInstance();

    public Scene Dashboard() {
        final double buttonPadding = 17.5;
        final int headerFontSize = 64;
        final int buttonFontSize = 22;

        final int logoWidth = 480;
        final int logoHeight = 128;

        final int gridHGap = 75;
        final int gridVGap = 50;

        /* Begin Temporary */
        final String buttonColor = "#9CC2D4";
        final String hoverButtonColor = "#E4F6FF";

        final String logoutButtonColor = "#FFFFFF";

        final String fontFamily = "Arial";
        /* End Temporary */

        FileInputStream logoInput, backgroundInput;
        Image logoImage, backgroundImage;
        ImageView logoImageView;

        HBox logoHeader = new HBox();
        logoHeader
                .setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        try {
            logoInput = new FileInputStream("./src/main/resources/logo.png");
            logoImage = new Image(logoInput, logoWidth, logoHeight, true, false);
            logoImageView = new ImageView(logoImage);

            logoHeader.getChildren().add(logoImageView);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        Text headerText = new Text("Dashboard");
        headerText.setFont(Font.font(fontFamily, FontWeight.NORMAL, headerFontSize));
        headerText.setFill(Color.WHITE);
        headerText.setTextAlignment(TextAlignment.CENTER);

        VBox headerContainer = new VBox();
        headerContainer.setPadding(new Insets(25, 0, 0, 0));
        headerContainer.setAlignment(Pos.CENTER);
        headerContainer.getChildren().add(headerText);

        Button editQuizButton = new Button("Vragenlijst");
        editQuizButton.setFont(Font.font(fontFamily, FontWeight.BOLD, buttonFontSize));
        editQuizButton.setTextFill(Color.BLACK);
        editQuizButton.setAlignment(Pos.CENTER_LEFT);
        editQuizButton.setPadding(new Insets(buttonPadding));
        editQuizButton.setPrefWidth(326);
        editQuizButton.setStyle(String.format("-fx-background-color: %s;", buttonColor));
        editQuizButton.setOnMouseEntered(e -> {
            editQuizButton.setStyle(String.format("-fx-background-color: %s;", hoverButtonColor));
        });
        editQuizButton.setOnMouseExited(e -> {
            editQuizButton.setStyle(String.format("-fx-background-color: %s;", buttonColor));
        });
        editQuizButton.setOnMouseClicked(e -> {
            dashboardController.navigateEditView();
        });

        Button showStatsButton = new Button("Statistieken");
        showStatsButton.setFont(Font.font(fontFamily, FontWeight.BOLD, buttonFontSize));
        showStatsButton.setTextFill(Color.BLACK);
        showStatsButton.setAlignment(Pos.CENTER_LEFT);
        showStatsButton.setPadding(new Insets(buttonPadding));
        showStatsButton.setPrefWidth(326);
        showStatsButton.setStyle(String.format("-fx-background-color: %s;", buttonColor));
        showStatsButton.setOnMouseEntered(e -> {
            showStatsButton.setStyle(String.format("-fx-background-color: %s;", hoverButtonColor));
        });
        showStatsButton.setOnMouseExited(e -> {
            showStatsButton.setStyle(String.format("-fx-background-color: %s;", buttonColor));
        });
        showStatsButton.setOnMouseClicked(e -> {
            dashboardController.saveStats();
        });

        Button editColorsButton = new Button("Kleuren");
        editColorsButton.setFont(Font.font(fontFamily, FontWeight.BOLD, buttonFontSize));
        editColorsButton.setTextFill(Color.BLACK);
        editColorsButton.setAlignment(Pos.CENTER_LEFT);
        editColorsButton.setPadding(new Insets(buttonPadding));
        editColorsButton.setPrefWidth(326);
        editColorsButton.setStyle(String.format("-fx-background-color: %s;", buttonColor));
        editColorsButton.setOnMouseEntered(e -> {
            editColorsButton.setStyle(String.format("-fx-background-color: %s;", hoverButtonColor));
        });
        editColorsButton.setOnMouseExited(e -> {
            editColorsButton.setStyle(String.format("-fx-background-color: %s;", buttonColor));
        });
        editColorsButton.setOnMouseClicked(e -> {
            dashboardController.navigateColorView();
        });

        Button logoutButton = new Button("Logout");
        logoutButton.setFont(Font.font(fontFamily, FontWeight.BOLD, buttonFontSize));
        logoutButton.setTextFill(Color.BLACK);
        logoutButton.setAlignment(Pos.CENTER_LEFT);
        logoutButton.setPadding(new Insets(buttonPadding));
        logoutButton.setPrefWidth(326);
        logoutButton.setStyle(String.format("-fx-background-color: %s;", logoutButtonColor));
        logoutButton.setOnMouseEntered(e -> {
            logoutButton.setStyle(String.format("-fx-background-color: %s;", hoverButtonColor));
        });
        logoutButton.setOnMouseExited(e -> {
            logoutButton.setStyle(String.format("-fx-background-color: %s;", logoutButtonColor));
        });
        logoutButton.setOnMouseClicked(e -> {
            AdminVariables.stage.setScene(new LoginView().Login());
        });

        GridPane actionList = new GridPane();
        actionList.setPadding(new Insets(50, 150, 100, 75));
        actionList.setAlignment(Pos.CENTER);
        actionList.setHgap(gridHGap);
        actionList.setVgap(gridVGap);
        actionList.add(editQuizButton, 1, 0);
        actionList.add(showStatsButton, 2, 0);
        actionList.add(editColorsButton, 1, 1);
        actionList.add(logoutButton, 2, 1);

        VBox root = new VBox(25);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(25));
        try {
            backgroundInput = new FileInputStream("./src/main/resources/background.png");
            backgroundImage = new Image(backgroundInput);
            root.setBackground(new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        root.getChildren().addAll(logoHeader, headerContainer, actionList);

        return new Scene(root);
    }
}
