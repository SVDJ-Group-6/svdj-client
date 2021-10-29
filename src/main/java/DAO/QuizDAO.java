package DAO;

import Client.ClientVariables;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Answer;
import model.Question;
import service.RequestService;

import java.io.IOException;
import java.util.ArrayList;

public class QuizDAO {

    private static QuizDAO quizDAO;
    private RequestService requestService = RequestService.getInstance();

    private final Gson gson = new Gson();

    public Question getQuestionFromAPI(String questionID) throws IOException {
        String questionURL = ClientVariables.API_URL + "/api/questions/" + questionID;
        Question question = gson.fromJson(requestService.getResponse(questionURL), Question.class);
        return question;
    }

    public ArrayList<Answer> getAnswersFromAPI(String questionID) throws IOException {
        String answersURL = ClientVariables.API_URL + "/api/answers/question/" + questionID;
        ArrayList<Answer> answers = gson.fromJson(requestService.getResponse(answersURL), new TypeToken<ArrayList<Answer>>(){}.getType());
        return answers;
    }

    public static QuizDAO getInstance() {
        if (quizDAO == null) {
            quizDAO = new QuizDAO();
        }

        return quizDAO;
    }

}
