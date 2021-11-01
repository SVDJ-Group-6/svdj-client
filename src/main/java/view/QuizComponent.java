package view;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class QuizComponent {

    //Todo add a json object or something

    private Text question;
    private ArrayList<Button> answers;
    private String Background;
    private Boolean isFirst;
    private Boolean isLast;

    public QuizComponent(){
        this.question = new QuestionCreator(1,"im a question").createCustomQuestion();
        this.answers = new ArrayList<>();
    }

    private void makeQuestionFromJSon(){
         //todo get the question text from a json
        //todo get the question number as well
        int questionNumber = 1;
        this.question = new QuestionCreator(questionNumber,"Am i made from json").createCustomQuestion();
    }

    private void makeAnswersFromJSon(){
        final int MAX = 4;
        String answer = "the answer from the json";
        for (int i = 0; i < MAX; i++) {
            Button b = new ButtonCreator(answer,"d3d3db").createCustomButton();
            this.answers.add(b);
        }
    }
    public HBox createQuizComponent(){
        //make a bunch  of test buttons
        makeAnswersFromJSon();

        //make a test question
        makeQuestionFromJSon();

        HBox hBox = new HBox();
        Text question = this.question;
        hBox.getChildren().add(question);
        System.out.println("I made a Questiom");

        for (Button answer : answers) {
            hBox.getChildren().add(answer);
        }
        return hBox;
    }
}
