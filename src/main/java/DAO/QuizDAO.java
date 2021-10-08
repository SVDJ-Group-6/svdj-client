package DAO;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Answer;
import model.Question;
import model.Quiz;
import observer.QuizObserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class QuizDAO {

    private static QuizDAO quizDAO;

    private final String API_URL = "http://localhost:8080";
    private final Gson gson = new Gson();
    private final Quiz quiz = new Quiz();

    public void getFirst() throws IOException {
        Question question = getQuestionFromAPI("first");
        ArrayList<Answer> answers = getAnswersFromAPI(Integer.toString(question.getId()));

        quiz.addNewQuestionAndAnswers(question, answers);
    }

    public void getNext(int questionID) throws IOException {
        Question question = getQuestionFromAPI(Integer.toString(questionID));
        ArrayList<Answer> answers = getAnswersFromAPI(Integer.toString(questionID));

        quiz.addNewQuestionAndAnswers(question, answers);
    }

    public void getPrevious() {
        if (quiz.getQuestions().size() > 1) {
            quiz.deleteLastQuestionAndAnswers();
        }
    }

    private Question getQuestionFromAPI(String questionID) throws IOException {
        String questionURL = API_URL + "/api/questions/" + questionID;
        Question question = gson.fromJson(getResponse(questionURL), Question.class);
        return question;
    }

    private ArrayList<Answer> getAnswersFromAPI(String questionID) throws IOException {
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

    public void registerObserver(QuizObserver quizObserver) {
        quiz.registerObserver(quizObserver);
    }

    public static QuizDAO getInstance() {
        if (quizDAO == null) {
            quizDAO = new QuizDAO();
        }

        return quizDAO;
    }

}
