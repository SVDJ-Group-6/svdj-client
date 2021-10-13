package DAO;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Answer;
import model.Question;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class QuizDAO {

    private static QuizDAO quizDAO;

    private final String API_URL = "http://localhost:8080";
    private final Gson gson = new Gson();

    public Question getQuestionFromAPI(String questionID) throws IOException {
        String questionURL = API_URL + "/api/questions/" + questionID;
        Question question = gson.fromJson(getResponse(questionURL), Question.class);
        return question;
    }

    public ArrayList<Answer> getAnswersFromAPI(String questionID) throws IOException {
        String answersURL = API_URL + "/api/answers/question/" + questionID;
        ArrayList<Answer> answers = gson.fromJson(getResponse(answersURL), new TypeToken<ArrayList<Answer>>(){}.getType());
        return answers;
    }

    private String getResponse(String URL) throws IOException {
        System.out.println(URL);
        URL endpointURL = new URL(URL);
        HttpURLConnection connection = (HttpURLConnection) endpointURL.openConnection();
        connection.setRequestMethod("GET");

        InputStream inputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        StringBuilder stringBuilder = new StringBuilder();

        while (true) {
            String line = bufferedReader.readLine();

            if (line == null) {
                break;
            }

            stringBuilder.append(line);
        }

        return stringBuilder.toString();
    }

    public static QuizDAO getInstance() {
        if (quizDAO == null) {
            quizDAO = new QuizDAO();
        }

        return quizDAO;
    }

}
