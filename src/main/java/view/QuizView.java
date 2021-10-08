package view;

import controller.QuizController;
import model.Answer;
import model.Quiz;
import observer.QuizObserver;

public class QuizView implements QuizObserver {

    private Answer selectedAnswer;
    private QuizController quizController;
    private Quiz quiz;

    @Override
    public void update(Quiz quiz) {

    }
}
