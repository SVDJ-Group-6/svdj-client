package view;

import Client.ClientVariables;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class QuestionCreator extends Text {

    private int questionNumber;
    private String questionText;

    public QuestionCreator(int questionNumber, String questionText) {
        this.questionNumber = questionNumber;
        this.questionText = questionText;
    }

    public Text createCustomQuestion() {
        final String STANDARD_TEXT = "Vraag";
        Text text = new Text(String.format("%s %d. %s", STANDARD_TEXT, questionNumber, questionText));
        text.setFont(Font.font("Verdana", 40));
        text.setFill(Color.web(ClientVariables.theme.getPrimaryColor()));
        text.setWrappingWidth(1000);

        return text;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }
}
