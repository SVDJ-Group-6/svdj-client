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



    public HBox createQuizComponent(){
        //make a bunch  of test buttons
      //  makeAnswersFromJSon();

        //make a test question
      //  makeQuestionFromJSon();

        HBox hBox = new HBox();
        Text question = this.question;
        hBox.getChildren().add(question);
        System.out.println("I made a Questiom");

        for (Button answer : answers) {
            hBox.getChildren().add(answer);
        }
        return hBox;
    }

    public HBox createQuestion() {

        HBox hBox = new HBox();
        Text question = new QuestionCreator(1, this.question.getText()).createCustomQuestion();
        hBox.getChildren().add(question);
        System.out.println("I made a Questiom");
        return hBox;
    }

    public HBox createAnswers() {
        HBox hBox = new HBox();
        for(Button answer : answers) {
            hBox.getChildren().add(new ButtonCreator(answer.getText(),"ccccff"));
        }
        return hBox;
    }

}
