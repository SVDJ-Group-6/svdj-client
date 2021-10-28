package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;



public class ButtonCreator extends Button {

    private String buttonText;
    private final double buttonPadding = 17.5;
    private final int buttonFontSize = 22;
    private String buttonColor;
    final String hoverButtonColor = "#E4F6FF";
    final String fontFamily = "Arial";


    public ButtonCreator(String buttonText, String buttonColor)
    {
        this.buttonText = buttonText;
        this.buttonColor = "#" + buttonColor;
    }

    public Button createCustomButton(){
        Button button = new Button(buttonText);
        button.setFont(Font.font(fontFamily, FontWeight.BOLD, buttonFontSize));
        button.setTextFill(Color.BLACK);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setPadding(new Insets(buttonPadding));
        button.setPrefWidth(326);
        button.setStyle(String.format("-fx-background-color: %s;", buttonColor));
        button.setOnMouseEntered(e -> {
            button.setStyle(String.format("-fx-background-color: %s;", hoverButtonColor));
        });
        button.setOnMouseExited(e -> {
            button.setStyle(String.format("-fx-background-color: %s;", buttonColor));
        });

        return button;
    }
}
