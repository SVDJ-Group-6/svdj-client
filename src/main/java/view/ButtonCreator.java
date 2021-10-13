package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.awt.*;

public class ButtonCreator extends Button {

    private String buttonText;
    private String buttonColorHash;

    //Todo
    private Point buttonSize;

    public ButtonCreator(String buttonText, String buttonColor)
    {
        this.buttonText = buttonText;
        this.buttonColorHash = "#" + buttonColor;

    }

    public Button createCustomButton(){
        System.out.println("Creating a CustomButton for you...");
        Button button = new Button(buttonText);
        button.setBackground(
                new Background(new BackgroundFill(Color.web(buttonColorHash), CornerRadii.EMPTY, Insets.EMPTY)));
        return button;
    }
}
