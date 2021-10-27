package view;

import controller.QuizController;
import controller.ThemeController;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Answer;
import model.Question;
import model.Quiz;
import model.Theme;
import observer.QuizObserver;

import java.util.ArrayList;

public class QuizView implements QuizObserver {

    private QuizController quizController = QuizController.getInstance();
    private ThemeController themeController = ThemeController.getInstance();
    private Theme theme = themeController.getTheme();

    private Question currentQuestion;
    private Answer selectedAnswer;
    private Text question;
    private HBox answerButtons;

    private Button previous;
    private Button next;

    public QuizView() {

        this.quizController = QuizController.getInstance();
        this.question = new QuestionCreator(0,"").createCustomQuestion();
        answerButtons = new HBox();

        previous = new ButtonCreator("Previous","d3d3db").createCustomButton();
        previous.setOnAction((event -> {
            quizController.back();
        }));
      
        next = new ButtonCreator("Next","d3d3db").createCustomButton();
        next.setOnAction((event -> {
            if (selectedAnswer != null) {
                quizController.next(currentQuestion, selectedAnswer);
            }
        }));

        quizController.registerObserver(this);
        quizController.loadFirst();
    }

    public HBox getQuizPane() {
        HBox quizBox = new HBox();

        HBox vbox = new HBox();
        vbox.getChildren().add(question);
        vbox.getChildren().add(answerButtons);

        vbox.getChildren().add(previous);
        vbox.getChildren().add(next);
        quizBox.getChildren().add(vbox);

        return quizBox;
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
            Button tmpBtn = new ButtonCreator(answer.getValue(),"d3d3db").createCustomButton();
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
        this.currentQuestion = currentQuestion;
    }
    
}
