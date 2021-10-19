package view;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class QuestionCreator extends Text {

    private int questionNumber;
    private String questionText;

    public QuestionCreator(int questionNumber, String questionText){
        this.questionNumber = questionNumber;
        this.questionText = questionText;
    }

    public Text createCustomQuestion(){
        final String STANDARD_TEXT = "Vraag";
        Text text = new Text( String.format( "%s %d. %s ?", STANDARD_TEXT,questionNumber,questionText));
        text.setFont(Font.font("Verdana",40));
        text.setFill(Color.WHITE);
        return text;
    }

    public void setQuestionText(String questionText) {
        this.setText(questionText);
    }
}
