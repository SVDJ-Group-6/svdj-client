package view;

import controller.QuizController;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Answer;
import model.Question;
import model.Quiz;
import observer.QuizObserver;

import java.util.ArrayList;

public class QuizView implements QuizObserver {

    private QuizController quizController = QuizController.getInstance();

    private Answer selectedAnswer;
    private Text question;
    private HBox answerButtons;

    private Button previous;

    public QuizView() {
        question = new Text("");
        answerButtons = new HBox();

        previous = new Button("Previous");
        previous.setOnAction((event -> {
            quizController.back();
        }));

        quizController.registerObserver(this);
        quizController.loadFirst();
    }

    public StackPane getQuizPane() {
        StackPane stackPane = new StackPane();

        VBox vbox = new VBox();
        vbox.getChildren().add(question);
        vbox.getChildren().add(answerButtons);

        Button next = new Button("Next");
        next.setOnAction((event -> {
            if (selectedAnswer != null) {
                quizController.next(selectedAnswer);
            }
        }));

        vbox.getChildren().add(previous);
        vbox.getChildren().add(next);
        stackPane.getChildren().add(vbox);

        return stackPane;
    }

    @Override
    public void update(Quiz quiz) {
        ArrayList<Question> questions = quiz.getQuestions();
        ArrayList<ArrayList<Answer>> allAnswers = quiz.getAnswers();

        Question currentQuestion = questions.get(questions.size() - 1);
        ArrayList<Answer> currentAnswers = allAnswers.get(allAnswers.size() - 1);

        // Empty HBOX for the new Buttons.
        this.answerButtons.getChildren().removeAll(this.answerButtons.getChildren());

        ArrayList<Button> newButtons = new ArrayList<>();
        for (Answer answer : currentAnswers) {
            Button tmpBtn = new Button(answer.getValue());
            tmpBtn.setOnAction((event -> {
                this.selectedAnswer = answer;
            }));
            newButtons.add(tmpBtn);
        }

        // Change value of elements
        this.previous.setVisible(questions.size() != 1);
        this.question.setText(currentQuestion.getValue());
        this.answerButtons.getChildren().addAll(newButtons);
        this.selectedAnswer = null;
    }
    
}
