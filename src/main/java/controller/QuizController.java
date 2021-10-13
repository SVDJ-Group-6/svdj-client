package controller;

import ClientApplication.ClientVariables;
import DAO.QuizDAO;
import javafx.scene.Scene;
import model.Answer;
import model.Question;
import model.Quiz;
import observer.QuizObserver;
import view.AdviceView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class QuizController {
    private static QuizController quizController;
    private QuizDAO quizDAO = QuizDAO.getInstance();

    private Quiz quiz = new Quiz();
    
    public void loadFirst() {
        try {
            Question question = quizDAO.getQuestionFromAPI("first");
            ArrayList<Answer> answers = quizDAO.getAnswersFromAPI(Integer.toString(question.getId()));
            quiz.addNewQuestionAndAnswers(question, answers);
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
                String questionID = Integer.toString(selectedAnswer.getNextQuestionId());

                Question question = quizDAO.getQuestionFromAPI(questionID);
                ArrayList<Answer> answers = quizDAO.getAnswersFromAPI(questionID);

                quiz.addNewQuestionAndAnswers(question, answers);
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
        quiz.deleteLastQuestionAndAnswers();
    }

    public void showAdvice(int adviceId){
        Scene scene = new Scene(new AdviceView(adviceId).getAdvicePane());
        ClientVariables.stage.setScene(scene);
    }

    private boolean hasAdviceID(Answer selectedAnswer){
        return !Objects.equals(selectedAnswer.getAdviceId(), null);
    }

    private boolean hasNextQuestionID(Answer selectedAnswer){
        return !Objects.equals(selectedAnswer.getNextQuestionId(), null);
    }

    public void registerObserver(QuizObserver quizObserver) {
        quiz.registerObserver(quizObserver);
    }

    public static QuizController getInstance() {
        if (quizController == null) {
            quizController = new QuizController();
        }
        return quizController;
    }
}
