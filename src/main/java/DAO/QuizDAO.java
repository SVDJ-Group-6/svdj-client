package DAO;

import Client.ClientVariables;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Answer;
import model.Question;
import model.Stats;
import service.RequestService;

import java.io.IOException;
import java.util.ArrayList;

public class QuizDAO {

    private static QuizDAO quizDAO;
    private final RequestService requestService = RequestService.getInstance();

    private final Gson gson = new Gson();

    public Question getQuestionFromAPI(String questionID) throws IOException {
        String questionURL = ClientVariables.API_URL + "/api/questions/" + questionID;
        Question question = gson.fromJson(requestService.getRequest(questionURL, null), Question.class);
        return question;
    }

    public String postStatsToAPI(ArrayList<Stats> stats) throws IOException {
        String URL = ClientVariables.API_URL + "/api/stats";
        String body = gson.toJson(stats, new TypeToken<ArrayList<Stats>>(){}.getType());
        return requestService.postRequest(URL, body, null);
    }

    public ArrayList<Answer> getAnswersFromAPI(String questionID) throws IOException {
        String answersURL = ClientVariables.API_URL + "/api/answers/question/" + questionID;
        ArrayList<Answer> answers = gson.fromJson(requestService.getRequest(answersURL, null), new TypeToken<ArrayList<Answer>>(){}.getType());
        return answers;
    }

    public static QuizDAO getInstance() {
        if (quizDAO == null) {
            quizDAO = new QuizDAO();
        }

        return quizDAO;
    }

}
