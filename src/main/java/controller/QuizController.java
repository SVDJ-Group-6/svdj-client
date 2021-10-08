package controller;

import DAO.QuizDAO;
import model.Answer;

public class QuizController {
    private static QuizController quizController;
    private QuizDAO quizDAO;

    public void loadFirst(){}
    public void next(Answer selectedAnswer){}
    //Todo moet iets returnen
//    public nextHasAdvice(Answer selectedAnswer){}

    public void back(){}
    public void showAdvice(int adviceId){}
    //Todo
    public static QuizController getInstance() {
        if (quizController == null) {
            quizController = new QuizController();
        }
        return quizController;
    }
}
