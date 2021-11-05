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
import java.io.IOException;

import Admin.AdminVariables;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class ThemeView implements ThemeObserver {
    ThemeController themeController = ThemeController.getInstance();

    final double BUTTON_PADDING = 17.5;
    final int HEADER_FONT_SIZE = 64;
    final int BUTTON_FONT_SIZE = 22;

    final int LOGO_WIDTH = 480;
    final int LOGO_HEIGHT = 128;

    final int HORIZONTAL_GRID_GAP = 75;
    final int VERTICAL_GRID_GAP = 50;

    final String FONT_FAMILY = "Arial";

    public Scene getThemeScene() {

        FileInputStream logoInput, backgroundInput;
        Image logoImage, backgroundImage;
        ImageView logoImageView;

        HBox logoHeader = new HBox();
        logoHeader
                .setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        try {
            logoImage = new Image(getClass().getClassLoader().getResourceAsStream("logo.png"), LOGO_WIDTH, LOGO_HEIGHT, true, false);
            logoImageView = new ImageView(logoImage);

            logoHeader.getChildren().add(logoImageView);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        Text headerText = new Text("Kleuren aanpassen");
        headerText.setFont(Font.font(FONT_FAMILY, FontWeight.NORMAL, HEADER_FONT_SIZE));
        headerText.setFill(Color.WHITE);
        headerText.setTextAlignment(TextAlignment.LEFT);

        Text primaryColorText = createColorText("Primary color");
        ColorPicker primaryColorPicker = createColorPicker(Color.web(AdminVariables.theme.getPrimaryColor()));
        HBox primaryColorPickerWrapper = createColorPickerWrapper(primaryColorPicker);
        HBox firstColumnBox = createColumnBox(primaryColorText, primaryColorPickerWrapper);

        Text callToActionColorText = createColorText("Call to action color");
        ColorPicker callToActionColorPicker = createColorPicker(Color.web(AdminVariables.theme.getCtaButtonColor()));
        HBox callToActionColorPickerWrapper = createColorPickerWrapper(callToActionColorPicker);
        HBox secondColumnBox = createColumnBox(callToActionColorText, callToActionColorPickerWrapper);

        Text secondaryColorText = createColorText("Secondary color");
        ColorPicker secondaryColorPicker = createColorPicker(Color.web(AdminVariables.theme.getSecondaryColor()));
        HBox secondaryColorPickerWrapper = createColorPickerWrapper(secondaryColorPicker);
        HBox thirdColumnBox = createColumnBox(secondaryColorText, secondaryColorPickerWrapper);

        Text answerButtonColorText = createColorText("Answer button color");
        ColorPicker answerColorPicker = createColorPicker(Color.web(AdminVariables.theme.getAnswerButtonColor()));
        HBox answerColorPickerWrapper = createColorPickerWrapper(answerColorPicker);
        HBox fourthColumnBox = createColumnBox(answerButtonColorText, answerColorPickerWrapper);

        Text tertaireColorText = createColorText("Tertaire color");
        ColorPicker tertaireColorPicker = createColorPicker(Color.web(AdminVariables.theme.getTertaireColor()));
        HBox tertaireColorPickerWrapper = createColorPickerWrapper(tertaireColorPicker);
        HBox fifthColumnBox = createColumnBox(tertaireColorText, tertaireColorPickerWrapper);

        Text selectedButtonColorText = createColorText("Selected button color");
        ColorPicker selectedButtonColorPicker = createColorPicker(
                Color.web(AdminVariables.theme.getSelectedButtonColor()));
        HBox selectedButtonColorPickerWrapper = createColorPickerWrapper(selectedButtonColorPicker);
        HBox sixthColumnBox = createColumnBox(selectedButtonColorText, selectedButtonColorPickerWrapper);

        Text previousButtonColorText = createColorText("Previous button color");
        ColorPicker previousButtonColorPicker = createColorPicker(
                Color.web(AdminVariables.theme.getPreviousButtonColor()));
        HBox previousButtonColorPickerWrapper = createColorPickerWrapper(previousButtonColorPicker);
        HBox seventhColumnBox = createColumnBox(previousButtonColorText, previousButtonColorPickerWrapper);

        Button backButton = new Button("Terug");
        backButton.setPadding(new Insets(BUTTON_PADDING));
        backButton.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, BUTTON_FONT_SIZE));
        backButton.setTextFill(Color.BLACK);
        backButton.setStyle(String.format("-fx-background-color: %s;", "#ffffff"));
        backButton.setOnMouseClicked(e -> themeController.navigateBack());
        backButton.setOnMouseEntered(e -> {
            backButton.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", "#000000", "#ffffff"));
        });
        backButton.setOnMouseExited(e -> {
            backButton.setStyle(String.format("-fx-background-color: %s;", "#ffffff"));
        });

        Button applyButton = new Button("Bevestigen");
        applyButton.setPadding(new Insets(BUTTON_PADDING));
        applyButton.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, BUTTON_FONT_SIZE));
        applyButton.setTextFill(Color.BLACK);
        applyButton.setPrefWidth(160);
        applyButton.setStyle(String.format("-fx-background-color: %s;", "#ffffff"));
        applyButton.setOnMouseEntered(e -> {
            applyButton.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", "#000000", "#ffffff"));
        });
        applyButton.setOnMouseExited(e -> {
            applyButton.setStyle(String.format("-fx-background-color: %s;", "#ffffff"));
        });
        applyButton.setOnMouseClicked(e -> {
            AdminVariables.theme.setPrimaryColor(themeController.toRGBCode(primaryColorPicker.getValue()));
            AdminVariables.theme.setSecondaryColor(themeController.toRGBCode(secondaryColorPicker.getValue()));
            AdminVariables.theme.setTertaireColor(themeController.toRGBCode(tertaireColorPicker.getValue()));
            AdminVariables.theme.setCtaButtonColor(themeController.toRGBCode(callToActionColorPicker.getValue()));
            AdminVariables.theme.setAnswerButtonColor(themeController.toRGBCode(answerColorPicker.getValue()));
            AdminVariables.theme
                    .setSelectedButtonColor(themeController.toRGBCode(selectedButtonColorPicker.getValue()));
            AdminVariables.theme
                    .setPreviousButtonColor(themeController.toRGBCode(previousButtonColorPicker.getValue()));

            try {
                themeController.submitColors(AdminVariables.theme);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        GridPane actionContainer = new GridPane();
        actionContainer.setHgap(25);
        actionContainer.setAlignment(Pos.CENTER_RIGHT);
        actionContainer.add(backButton, 0, 0);
        actionContainer.add(applyButton, 1, 0);

        HBox headerContainer = new HBox();
        headerContainer.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(actionContainer, Priority.ALWAYS);
        headerContainer.getChildren().addAll(headerText, actionContainer);

        GridPane actionList = new GridPane();
        actionList.setPadding(new Insets(75, 0, 100, 50));
        actionList.setBackground(
                new Background(new BackgroundFill(Color.web("#E8F1F6"), CornerRadii.EMPTY, Insets.EMPTY)));
        actionList.setHgap(HORIZONTAL_GRID_GAP);
        actionList.setVgap(VERTICAL_GRID_GAP);
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
            backgroundImage = new Image(getClass().getClassLoader().getResourceAsStream("background.png"));
            root.setBackground(new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        root.getChildren().addAll(logoHeader, headerContainer, actionList);

        return new Scene(root);
    }

    public Text createColorText(String text) {
        Text colortext = new Text(text);
        colortext.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, BUTTON_FONT_SIZE));

        return colortext;
    }

    public ColorPicker createColorPicker(Color defaultcolor) {
        ColorPicker colorpicker = new ColorPicker(defaultcolor);
        colorpicker.setBackground(new Background(new BackgroundFill(
                Color.web(themeController.toRGBCode(colorpicker.getValue())), CornerRadii.EMPTY, Insets.EMPTY)));
        colorpicker.setOnAction(e -> {
            colorpicker.setBackground(new Background(new BackgroundFill(
                    Color.web(themeController.toRGBCode(colorpicker.getValue())), CornerRadii.EMPTY, Insets.EMPTY)));
        });

        return colorpicker;
    }

    public HBox createColorPickerWrapper(ColorPicker colorpicker) {
        HBox colorpickerwrapper = new HBox();
        HBox.setHgrow(colorpickerwrapper, Priority.ALWAYS);

        colorpickerwrapper.setAlignment(Pos.BASELINE_RIGHT);
        colorpickerwrapper.setMinWidth(200);
        colorpickerwrapper.setMaxWidth(300);
        colorpickerwrapper.getChildren().add(colorpicker);

        return colorpickerwrapper;
    }

    public HBox createColumnBox(Text text, HBox colorpickerwrapper) {
        HBox columnbox = new HBox(10);
        columnbox.getChildren().addAll(text, colorpickerwrapper);

        return columnbox;
    }

    @Override
    public void update(Theme theme) {
        Platform.runLater(() -> {

        });
    }
}
