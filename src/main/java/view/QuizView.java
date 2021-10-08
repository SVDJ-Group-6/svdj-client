package view;

import controller.QuizController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Answer;
import model.Question;
import model.Quiz;
import observer.QuizObserver;

import java.util.ArrayList;

public class QuizView extends StackPane implements QuizObserver {

    private Answer selectedAnswer;
    private QuizController quizController = QuizController.getInstance();

    private Text question;
    private Text answer;
    private TextField inputID;

    public QuizView() {
        quizController.registerObserver(this);

        question = new Text("");
        answer = new Text("");
        inputID = new TextField("");

        Button retieve = new Button("Retrieve Question");
        retieve.setOnAction((event) -> retrieveQuestion());

        VBox vbox = new VBox();
        vbox.getChildren().add(question);
        vbox.getChildren().add(answer);
        vbox.getChildren().add(inputID);
        vbox.getChildren().add(retieve);

        this.getChildren().add(vbox);
    }

    private void retrieveQuestion() {
        Answer dummyAnswer = new Answer(Integer.parseInt(inputID.getText()));
        quizController.next(dummyAnswer);
    }

    @Override
    public void update(Quiz quiz) {
        ArrayList<Question> questions = quiz.getQuestions();
        Question lastQuestion = questions.get(questions.size() - 1);

        ArrayList<ArrayList<Answer>> allAnswers = quiz.getAnswers();
        ArrayList<Answer> answers = allAnswers.get(allAnswers.size() - 1);

        question.setText(lastQuestion.getValue());

        StringBuilder stringBuilder = new StringBuilder();
        for (Answer answer : answers) {
            stringBuilder.append(answer.getValue()).append("\n");
        }

        this.answer.setText(stringBuilder.toString());
    }
}
