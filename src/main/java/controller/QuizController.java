package controller;

import Client.ClientVariables;
import DAO.AdviceDAO;
import DAO.QuizDAO;
import DAO.StatsDAO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.scene.Scene;
import model.*;
import observer.QuizObserver;
import view.AdviceView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class QuizController {
    private static QuizController quizController;
    private final QuizDAO quizDAO = QuizDAO.getInstance();
    private final StatsDAO statsDAO = StatsDAO.getInstance();
    private final AdviceDAO adviceDAO = AdviceDAO.getInstance();
    private Quiz quiz = new Quiz();
    private final Gson gson = new Gson();


    /**
     * This function is ran on initialization, after registerObserver.
     * It gets the first question with the appropriate answers from the API,
     * and adds it to the Quiz Model.
     */
    public void loadFirst() {
        try {
            Question question = quizDAO.getQuestionFromAPI("first");
            ArrayList<Answer> answers = quizDAO.getAnswersFromAPI(Integer.toString(question.getId()));
            quiz.addNewQuestionAndAnswers(question, answers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * When the user clicks the next button of the QuizView, the users progress is first added to the Quiz model.
     * Then the selectedAnswer is checked for it's adviceID.
     * If adviceID exists, showAdvice() is called and all the stats (progress) are posted to the server.
     * If there is a nextQuestionID, the program fetches the appropriate question and answers from the API,
     * which are added to the Quiz model.
     * @param currentQuestion The question object the end user is currently on
     * @param selectedAnswer The answer the end user selected on the current question
     */
    public void next(Question currentQuestion, Answer selectedAnswer){

        addStats(currentQuestion, selectedAnswer);

        if (hasAdviceID(selectedAnswer)) {
            showAdvice(selectedAnswer.getAdviceId());
            postAllStats();
            return;
        }

        if (hasNextQuestionID(selectedAnswer)) {
            try {
                String questionID = Integer.toString(selectedAnswer.getNextQuestionId());
                Question newQuestion = quizDAO.getQuestionFromAPI(questionID);
                ArrayList<Answer> newAnswers = quizDAO.getAnswersFromAPI(questionID);
                quiz.addNewQuestionAndAnswers(newQuestion, newAnswers);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        // TODO: ERROR HANDLING FOR INCONSISTENT DATA? IF THERE IS NO NEXT QUESTION AND ALSO NO ADVICE
        // TODO: Make no advice screen
        System.out.println("Dead end?");
    }

    /**
     * Check if the answer the user chose has an adviceID.
     * If so, the advice value gets fetched, added the stat with the fetched advice.
     * If not, stat will be added to Quiz model with advice being null.
     * @param question The question from where the user just submitted.
     * @param answer The answer on the question the user submitted.
     */
    private void addStats(Question question, Answer answer) {
        try {
            if (hasAdviceID(answer)) {
                Advice advice = adviceDAO.getAdviceFromAPI(answer.getAdviceId());
                quiz.addStats(question.getValue(), answer.getValue(), advice.getValue());
                return;
            }

            quiz.addStats(question.getValue(), answer.getValue(), null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void postAllStats() {
        try {
            quizDAO.postStatsToAPI(quiz.getStats());
            quiz = new Quiz();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void back() {
        quiz.deleteLastQuestionAndAnswers();
    }

    /**
     * Creates a scene using AdviceView and changes the Scene.
     * @param adviceId Used to fetch the advice to show it to the end user.
     */
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
