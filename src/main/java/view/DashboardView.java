package view;

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

    public Scene getDashboardScene() {
        final double BUTTON_PADDING = 17.5;
        final int HEADER_FONT_SIZE = 64;
        final int BUTTON_FONT_SIZE = 22;

        final int LOGO_WIDTH = 480;
        final int LOGO_HEIGHT = 128;

        final int HORIZONTAL_GRID_GAP = 75;
        final int VERTICAL_GRID_GAP = 50;

        final String BUTTON_COLOR = "#9CC2D4";
        final String HOVER_BUTTON_COLOR = "#E4F6FF";

        final String LOGOUT_BUTTON_COLOR = "#FFFFFF";

        final String FONT_FAMILY = "Arial";

        FileInputStream logoInput, backgroundInput;
        Image logoImage, backgroundImage;
        ImageView logoImageView;

        HBox logoHeader = new HBox();
        logoHeader
                .setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        logoImage = new Image(getClass().getClassLoader().getResourceAsStream("logo.png"), LOGO_WIDTH, LOGO_HEIGHT, true, false);
        logoImageView = new ImageView(logoImage);

        logoHeader.getChildren().add(logoImageView);

        Text headerText = new Text("Dashboard");
        headerText.setFont(Font.font(FONT_FAMILY, FontWeight.NORMAL, HEADER_FONT_SIZE));
        headerText.setFill(Color.WHITE);
        headerText.setTextAlignment(TextAlignment.CENTER);

        VBox headerContainer = new VBox();
        headerContainer.setPadding(new Insets(25, 0, 0, 0));
        headerContainer.setAlignment(Pos.CENTER);
        headerContainer.getChildren().add(headerText);

        Button editQuizButton = new Button("Vragenlijst");
        editQuizButton.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, BUTTON_FONT_SIZE));
        editQuizButton.setTextFill(Color.BLACK);
        editQuizButton.setAlignment(Pos.CENTER_LEFT);
        editQuizButton.setPadding(new Insets(BUTTON_PADDING));
        editQuizButton.setPrefWidth(326);
        editQuizButton.setStyle(String.format("-fx-background-color: %s;", BUTTON_COLOR));
        editQuizButton.setOnMouseEntered(e -> {
            editQuizButton.setStyle(String.format("-fx-background-color: %s;", HOVER_BUTTON_COLOR));
        });
        editQuizButton.setOnMouseExited(e -> {
            editQuizButton.setStyle(String.format("-fx-background-color: %s;", BUTTON_COLOR));
        });
        editQuizButton.setOnMouseClicked(e -> {
            dashboardController.navigateEditView();
        });

        Button showStatsButton = new Button("Statistieken");
        showStatsButton.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, BUTTON_FONT_SIZE));
        showStatsButton.setTextFill(Color.BLACK);
        showStatsButton.setAlignment(Pos.CENTER_LEFT);
        showStatsButton.setPadding(new Insets(BUTTON_PADDING));
        showStatsButton.setPrefWidth(326);
        showStatsButton.setStyle(String.format("-fx-background-color: %s;", BUTTON_COLOR));
        showStatsButton.setOnMouseEntered(e -> {
            showStatsButton.setStyle(String.format("-fx-background-color: %s;", HOVER_BUTTON_COLOR));
        });
        showStatsButton.setOnMouseExited(e -> {
            showStatsButton.setStyle(String.format("-fx-background-color: %s;", BUTTON_COLOR));
        });
        showStatsButton.setOnMouseClicked(e -> {
            dashboardController.saveStats();
        });

        Button editColorsButton = new Button("Kleuren");
        editColorsButton.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, BUTTON_FONT_SIZE));
        editColorsButton.setTextFill(Color.BLACK);
        editColorsButton.setAlignment(Pos.CENTER_LEFT);
        editColorsButton.setPadding(new Insets(BUTTON_PADDING));
        editColorsButton.setPrefWidth(326);
        editColorsButton.setStyle(String.format("-fx-background-color: %s;", BUTTON_COLOR));
        editColorsButton.setOnMouseEntered(e -> {
            editColorsButton.setStyle(String.format("-fx-background-color: %s;", HOVER_BUTTON_COLOR));
        });
        editColorsButton.setOnMouseExited(e -> {
            editColorsButton.setStyle(String.format("-fx-background-color: %s;", BUTTON_COLOR));
        });
        editColorsButton.setOnMouseClicked(e -> {
            dashboardController.navigateColorView();
        });

        Button logoutButton = new Button("Logout");
        logoutButton.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, BUTTON_FONT_SIZE));
        logoutButton.setTextFill(Color.BLACK);
        logoutButton.setAlignment(Pos.CENTER_LEFT);
        logoutButton.setPadding(new Insets(BUTTON_PADDING));
        logoutButton.setPrefWidth(326);
        logoutButton.setStyle(String.format("-fx-background-color: %s;", LOGOUT_BUTTON_COLOR));
        logoutButton.setOnMouseEntered(e -> {
            logoutButton.setStyle(String.format("-fx-background-color: %s;", HOVER_BUTTON_COLOR));
        });
        logoutButton.setOnMouseExited(e -> {
            logoutButton.setStyle(String.format("-fx-background-color: %s;", LOGOUT_BUTTON_COLOR));
        });
        logoutButton.setOnMouseClicked(e -> {
            dashboardController.logout();
        });

        GridPane actionList = new GridPane();
        actionList.setPadding(new Insets(50, 150, 100, 75));
        actionList.setAlignment(Pos.CENTER);
        actionList.setHgap(HORIZONTAL_GRID_GAP);
        actionList.setVgap(VERTICAL_GRID_GAP);
        actionList.add(editQuizButton, 1, 0);
        actionList.add(showStatsButton, 2, 0);
        actionList.add(editColorsButton, 1, 1);
        actionList.add(logoutButton, 2, 1);

        VBox root = new VBox(25);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(25));
        backgroundImage = new Image(getClass().getClassLoader().getResourceAsStream("background.png"));
        root.setBackground(new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        root.getChildren().addAll(logoHeader, headerContainer, actionList);

        return new Scene(root);
    }
}
