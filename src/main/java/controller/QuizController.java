package controller;

import DAO.QuizDAO;
import model.Answer;
import observer.QuizObserver;

import java.io.IOException;
import java.util.Objects;

public class QuizController {
    private static QuizController quizController;
    private QuizDAO quizDAO = QuizDAO.getInstance();

    // Call loadFirst after registering View
    public void loadFirst() {
        try {
            quizDAO.getFirst();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void next(Answer selectedAnswer){

        if (hasAdviceID(selectedAnswer)) {
            showAdvice(selectedAnswer.getAdviceId());
            return;
        }

        if (hasNextQuestionID(selectedAnswer)) {
            try {
                quizDAO.getNext(selectedAnswer.getNextQuestionId());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        // TODO: ERROR HANDLING FOR INCONSISTENT DATA? IF THERE IS NO NEXT QUESTION AND ALSO NO ADVICE
        // TODO: Make no advice screen
        System.out.println("Dead end?");
    }

    public void back() {
        quizDAO.getPrevious();
    }

    public void showAdvice(int adviceId){

    }

    public boolean hasAdviceID(Answer selectedAnswer){
        return !Objects.equals(selectedAnswer.getAdviceId(), null);
    }

    public boolean hasNextQuestionID(Answer selectedAnswer){
        return !Objects.equals(selectedAnswer.getNextQuestionId(), null);
    }

    public void registerObserver(QuizObserver quizObserver) {
        quizDAO.registerObserver(quizObserver);
    }

    public static QuizController getInstance() {
        if (quizController == null) {
            quizController = new QuizController();
        }

        return  quizController;
    }
}
