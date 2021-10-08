package view;

import controller.QuizController;
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

public class QuizView implements QuizObserver {

    private Answer selectedAnswer;

    private final QuizController quizController;

    private final Text question;
    private final Text answer;
    private final TextField inputID;

    public QuizView() {
        quizController = QuizController.getInstance();

        question = new Text("");
        answer = new Text("");
        inputID = new TextField("");

        quizController.registerObserver(this);

        // TODO: Load first node.
    }

    public StackPane getQuizPane() {
        StackPane stackPane = new StackPane();

        Button retieve = new Button("Retrieve Question");
        retieve.setOnAction((event) -> retrieveQuestion());

        VBox vbox = new VBox();
        vbox.getChildren().add(question);
        vbox.getChildren().add(answer);
        vbox.getChildren().add(inputID);
        vbox.getChildren().add(retieve);

        stackPane.getChildren().add(vbox);

        return stackPane;
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
