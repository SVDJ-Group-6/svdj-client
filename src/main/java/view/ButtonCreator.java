package view;

import Client.ClientVariables;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Answer;

public class ButtonCreator extends Button {
    final double buttonPadding = 17.5;
    private int preferedWidth;
    private int buttonFontSize = 22;
    private Boolean selected = false;
    private Answer answer;

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    private String buttonColor;

    final String fontFamily = "Arial";

    private String buttonText;

    public ButtonCreator(String buttonText, String color) {
        this.buttonText = buttonText;
        this.buttonColor = color;
        this.preferedWidth = 250;
    }

    public ButtonCreator(String buttonText) {
        this.buttonText = buttonText;
        this.buttonColor = ClientVariables.theme.getAnswerButtonColor();
        this.preferedWidth = 500;
    }

    public Button createCustomButton() {
        Button button = new Button(buttonText);
        button.setFont(Font.font(fontFamily, FontWeight.BOLD, buttonFontSize));
        button.setTextFill(Color.web(ClientVariables.theme.getSecondaryColor()));
        button.setAlignment(Pos.CENTER_LEFT);
        button.setPadding(new Insets(buttonPadding));
        button.setPrefWidth(preferedWidth);
        button.setStyle(String.format("-fx-background-color: %s;", ClientVariables.theme.getAnswerButtonColor()));

        button.setOnMouseEntered(e -> {
            if (!selected) {
                button.setStyle(String.format("-fx-background-color: %s;", ClientVariables.theme.getSelectedButtonColor()));
            }
        });
        button.setOnMouseExited(e -> {
            if (!selected) {
                button.setStyle(String.format("-fx-background-color: %s;", ClientVariables.theme.getAnswerButtonColor()));
            }
        });

        return button;
    }

    public void setPreferedWidth(int preferedWidth) {
        this.preferedWidth = preferedWidth;
    }
}
