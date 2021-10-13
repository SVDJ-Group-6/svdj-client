package view;

import controller.ThemeController;
import model.Theme;
import observer.ThemeObserver;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class ThemeView implements ThemeObserver {
    private ThemeController themeController = ThemeController.getInstance();
    private Theme theme;

    public Scene Theme() {
        final double buttonPadding = 17.5;
        final int headerFontSize = 64;
        final int buttonFontSize = 22;

        final int logoWidth = 480;
        final int logoHeight = 128;

        final int gridHGap = 75;
        final int gridVGap = 50;

        /* Begin Temporary */
        final String buttonColor = "#FFFFFF";
        final String hoverButtonColor = "#000000";

        final String answerBackgroundColor = "#EDECEC";

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

        Text headerText = new Text("Kleuren aanpassen");
        headerText.setFont(Font.font(fontFamily, FontWeight.NORMAL, headerFontSize));
        headerText.setFill(Color.WHITE);
        headerText.setTextAlignment(TextAlignment.LEFT);

        HBox firstColumnBox = new HBox(10);
        HBox secondColumnBox = new HBox(10);
        HBox thirdColumnBox = new HBox(10);
        HBox fourthColumnBox = new HBox(10);
        HBox fifthColumnBox = new HBox(10);
        HBox sixthColumnBox = new HBox(10);
        HBox seventhColumnBox = new HBox(10);

        Text primaryColorText = new Text("Primary Color");
        primaryColorText.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        ColorPicker primaryColorPicker = new ColorPicker(Color.web("#FFFFFF")); // Get color from model from api ofzo
        primaryColorPicker
                .setBackground(new Background(new BackgroundFill(Color.web(toRGBCode(primaryColorPicker.getValue())),
                        CornerRadii.EMPTY, Insets.EMPTY)));
        primaryColorPicker.setOnAction(e -> {
            primaryColorPicker.setBackground(new Background(new BackgroundFill(
                    Color.web(toRGBCode(primaryColorPicker.getValue())), CornerRadii.EMPTY, Insets.EMPTY)));
        });
        firstColumnBox.getChildren().addAll(primaryColorText, primaryColorPicker);

        Text ctaButtonColorText = new Text("Cta Button Color");
        ctaButtonColorText.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        ColorPicker ctaButtonColorPicker = new ColorPicker(Color.web("#9CC2D4"));
        ctaButtonColorPicker
                .setBackground(new Background(new BackgroundFill(Color.web(toRGBCode(ctaButtonColorPicker.getValue())),
                        CornerRadii.EMPTY, Insets.EMPTY)));
        ctaButtonColorPicker.setOnAction(e -> {
            ctaButtonColorPicker.setBackground(new Background(new BackgroundFill(
                    Color.web(toRGBCode(ctaButtonColorPicker.getValue())), CornerRadii.EMPTY, Insets.EMPTY)));
        });
        secondColumnBox.getChildren().addAll(ctaButtonColorText, ctaButtonColorPicker);

        Text secondaryColorText = new Text("Secondary Color");
        secondaryColorText.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        ColorPicker secondaryColorPicker = new ColorPicker(Color.web("#000000"));
        secondaryColorPicker
                .setBackground(new Background(new BackgroundFill(Color.web(toRGBCode(secondaryColorPicker.getValue())),
                        CornerRadii.EMPTY, Insets.EMPTY)));
        secondaryColorPicker.setOnAction(e -> {
            secondaryColorPicker.setBackground(new Background(new BackgroundFill(
                    Color.web(toRGBCode(secondaryColorPicker.getValue())), CornerRadii.EMPTY, Insets.EMPTY)));
        });
        thirdColumnBox.getChildren().addAll(secondaryColorText, secondaryColorPicker);

        Text answerButtonColorText = new Text("Answer Button Color");
        answerButtonColorText.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        ColorPicker answerButtonColorPicker = new ColorPicker(Color.web("#AFCCDB"));
        answerButtonColorPicker.setBackground(new Background(new BackgroundFill(
                Color.web(toRGBCode(answerButtonColorPicker.getValue())), CornerRadii.EMPTY, Insets.EMPTY)));
        answerButtonColorPicker.setOnAction(e -> {
            answerButtonColorPicker.setBackground(new Background(new BackgroundFill(
                    Color.web(toRGBCode(answerButtonColorPicker.getValue())), CornerRadii.EMPTY, Insets.EMPTY)));
        });
        fourthColumnBox.getChildren().addAll(answerButtonColorText, answerButtonColorPicker);

        Text tertaireColorText = new Text("Tertaire Color");
        tertaireColorText.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        ColorPicker tertaireColorPicker = new ColorPicker(Color.web("#989898"));
        tertaireColorPicker
                .setBackground(new Background(new BackgroundFill(Color.web(toRGBCode(tertaireColorPicker.getValue())),
                        CornerRadii.EMPTY, Insets.EMPTY)));
        tertaireColorPicker.setOnAction(e -> {
            tertaireColorPicker.setBackground(new Background(new BackgroundFill(
                    Color.web(toRGBCode(tertaireColorPicker.getValue())), CornerRadii.EMPTY, Insets.EMPTY)));
        });
        fifthColumnBox.getChildren().addAll(tertaireColorText, tertaireColorPicker);

        Text selectedButtonColorText = new Text("Selected Button Color");
        selectedButtonColorText.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        ColorPicker selectedButtonColorPicker = new ColorPicker(Color.web("#E4F6FF"));
        selectedButtonColorPicker.setBackground(new Background(new BackgroundFill(
                Color.web(toRGBCode(selectedButtonColorPicker.getValue())), CornerRadii.EMPTY, Insets.EMPTY)));
        selectedButtonColorPicker.setOnAction(e -> {
            selectedButtonColorPicker.setBackground(new Background(new BackgroundFill(
                    Color.web(toRGBCode(selectedButtonColorPicker.getValue())), CornerRadii.EMPTY, Insets.EMPTY)));
        });
        sixthColumnBox.getChildren().addAll(selectedButtonColorText, selectedButtonColorPicker);

        Text previousButtonColorText = new Text("Previous Button Color");
        previousButtonColorText.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        ColorPicker previousButtonColorPicker = new ColorPicker(Color.web("#FFFFFF"));
        previousButtonColorPicker.setBackground(new Background(new BackgroundFill(
                Color.web(toRGBCode(previousButtonColorPicker.getValue())), CornerRadii.EMPTY, Insets.EMPTY)));
        previousButtonColorPicker.setOnAction(e -> {
            previousButtonColorPicker.setBackground(new Background(new BackgroundFill(
                    Color.web(toRGBCode(previousButtonColorPicker.getValue())), CornerRadii.EMPTY, Insets.EMPTY)));
        });
        seventhColumnBox.getChildren().addAll(previousButtonColorText, previousButtonColorPicker);

        Button backButton = new Button("Terug");
        backButton.setPadding(new Insets(buttonPadding));
        backButton.setFont(Font.font(fontFamily, FontWeight.BOLD, buttonFontSize));
        backButton.setTextFill(Color.BLACK);
        backButton.setStyle(String.format("-fx-background-color: %s;", buttonColor));
        backButton.setOnMouseClicked(e -> themeController.navigateBack());
        backButton.setOnMouseEntered(e -> {
            backButton.setStyle(
                    String.format("-fx-background-color: %s; -fx-text-fill: %s;", hoverButtonColor, buttonColor));
        });
        backButton.setOnMouseExited(e -> {
            backButton.setStyle(String.format("-fx-background-color: %s;", buttonColor));
        });

        Button applyButton = new Button("Bevestigen");
        applyButton.setPadding(new Insets(buttonPadding));
        applyButton.setFont(Font.font(fontFamily, FontWeight.BOLD, buttonFontSize));
        applyButton.setTextFill(Color.BLACK);
        applyButton.setPrefWidth(160);
        applyButton.setStyle(String.format("-fx-background-color: %s;", buttonColor));
        applyButton.setOnMouseEntered(e -> {
            applyButton.setStyle(
                    String.format("-fx-background-color: %s; -fx-text-fill: %s;", hoverButtonColor, buttonColor));
        });
        applyButton.setOnMouseExited(e -> {
            applyButton.setStyle(String.format("-fx-background-color: %s;", buttonColor));
        });
        applyButton.setOnMouseClicked(e -> {
            theme.setPrimaryColor(toRGBCode(primaryColorPicker.getValue()));
            theme.setSecondaryColor(toRGBCode(secondaryColorPicker.getValue()));
            theme.setTertaireColor(toRGBCode(tertaireColorPicker.getValue()));
            theme.setCtaButtonColor(toRGBCode(ctaButtonColorPicker.getValue()));
            theme.setAnswerButtonColor(toRGBCode(answerButtonColorPicker.getValue()));
            theme.setSelectedButtonColor(toRGBCode(secondaryColorPicker.getValue()));
            theme.setPreviousButtonColor(toRGBCode(previousButtonColorPicker.getValue()));

            themeController.submitColors(theme);
        });

        GridPane actionContainer = new GridPane();
        actionContainer.setHgap(25);
        actionContainer.setAlignment(Pos.CENTER_RIGHT);
        actionContainer.add(backButton, 0, 0);
        actionContainer.add(applyButton, 1, 0);

        HBox headerContainer = new HBox();
        headerContainer.setAlignment(Pos.CENTER_LEFT);
        headerContainer.setHgrow(actionContainer, Priority.ALWAYS);
        headerContainer.getChildren().addAll(headerText, actionContainer);

        GridPane actionList = new GridPane();
        actionList.setPadding(new Insets(50, 0, 100, 0));
        actionList.setBackground(
                new Background(new BackgroundFill(Color.web("#E8F1F6"), CornerRadii.EMPTY, Insets.EMPTY)));
        actionList.setHgap(gridHGap);
        actionList.setVgap(gridVGap);
        actionList.add(firstColumnBox, 1, 0);
        actionList.add(secondColumnBox, 2, 0);
        actionList.add(thirdColumnBox, 1, 1);
        actionList.add(fourthColumnBox, 2, 1);
        actionList.add(fifthColumnBox, 1, 2);
        actionList.add(sixthColumnBox, 2, 2);
        actionList.add(seventhColumnBox, 1, 3);

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

    public String toRGBCode(Color color) {
        int red = (int) (color.getRed() * 255);
        int green = (int) (color.getGreen() * 255);
        int blue = (int) (color.getBlue() * 255);

        return String.format("#%02X%02X%02X", red, green, blue);
    }

    @Override
    public void update(Theme theme) {
        // TODO Auto-generated method stub
        Platform.runLater(() -> {

        });
    }
}
