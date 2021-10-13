package view;

import controller.HomeController;
import controller.ThemeController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HomeView {
    private HomeController homeController;
    private ThemeController themeController;

    public Scene homeScreen() throws FileNotFoundException {
        BackgroundSize backgroundSize = new BackgroundSize(1280, 800, true, true, true, false);
        BackgroundImage background = null;
        try {
            background = new BackgroundImage(new Image(new FileInputStream("./src/main/resources/background.png")),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image logo = new Image(new FileInputStream("./src/main/resources/logo.png"),480, 127, false, false);
        ImageView logoView = new ImageView(logo);

        HBox logoContainer = new HBox(25);
        logoContainer.getChildren().add(logoView);
        logoContainer.setLayoutX(300);
        logoContainer.setLayoutY(300);

        Text Title = new Text();
        Title.setText("Weten welke subsidie bij jou past?");
        Title.setFont(Font.font("Arial",FontWeight.BOLD,48));
        Title.setFill(Color.WHITE);

        VBox titleContainer = new VBox(25);
        titleContainer.setAlignment(Pos.CENTER);
        titleContainer.getChildren().add(Title);

        VBox layout = new VBox(25);
        layout.setBackground(new Background(background));
        layout.getChildren().addAll(logoContainer,titleContainer);

        return new Scene(layout, 1280,720);
    }
}
