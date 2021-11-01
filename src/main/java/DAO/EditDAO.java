package DAO;

import Admin.AdminVariables;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Application;
import model.Advice;
import model.Answer;
import model.Node;
import model.Question;
import service.RequestService;

import java.io.IOException;
import java.util.ArrayList;

public class EditDAO {

    private final RequestService requestService;
    private final Gson gson;

    public EditDAO() {
        this.requestService = RequestService.getInstance();
        this.gson = new Gson();
    }

    public ArrayList<Node> getAllNodes() throws IOException {
        ArrayList<Node> allNodes = new ArrayList<>();

        ArrayList<Question> questions = getQuestionsFromAPI();
        ArrayList<Advice> advices = getAdvicesFromAPI();

        Node node;
        String questionID;
        ArrayList<Answer> answers;
        for (Question question : questions) {
            questionID = String.valueOf(question.getId());
            answers = getAnswersFromAPI(questionID);
            node = new Node(question, answers);
            allNodes.add(node);
        }

        for (Advice advice : advices) {
            node = new Node(advice);
            allNodes.add(node);
        }

        return allNodes;
    }

    public ArrayList<Question> getQuestionsFromAPI() throws IOException {
        String URL = AdminVariables.API_URL + "/api/questions/";
        String response = requestService.getRequest(URL, null);
        return gson.fromJson(response, new TypeToken<ArrayList<Question>>(){}.getType());
    }

    public ArrayList<Advice> getAdvicesFromAPI() throws IOException {
        String URL = AdminVariables.API_URL + "/api/advices/";
        String response = requestService.getRequest(URL, null);
        return gson.fromJson(response, new TypeToken<ArrayList<Advice>>(){}.getType());
    }

    public ArrayList<Answer> getAnswersFromAPI(String questionID) throws IOException {
        String URL = AdminVariables.API_URL + "/api/answers/question/" + questionID;
        String response = requestService.getRequest(URL, null);
        return gson.fromJson(response, new TypeToken<ArrayList<Answer>>(){}.getType());
    }

    public void postQuestionsToAPI(ArrayList<Question> questions) throws IOException {
        String body = gson.toJson(questions, new TypeToken<ArrayList<Question>>(){}.getType());
        String URL = AdminVariables.API_URL + "/api/questions";
        requestService.postRequest(URL, body, AdminVariables.token);
    }

    public void postAnswersToAPI(ArrayList<Answer> answers) throws IOException {
        String body = gson.toJson(answers, new TypeToken<ArrayList<Answer>>(){}.getType());
        String URL = AdminVariables.API_URL + "/api/answers";
        requestService.postRequest(URL, body, AdminVariables.token);
    }

    public void postAdviceToAPI(ArrayList<Advice> advice) throws IOException {
        String body = gson.toJson(advice, new TypeToken<ArrayList<Advice>>(){}.getType());
        String URL = AdminVariables.API_URL + "/api/advices";
        requestService.postRequest(URL, body, AdminVariables.token);
    }

    public void deleteQuestionsFromAPI(ArrayList<Integer> ids) throws IOException {
        deleteEndpointFromAPI("questions", ids);
    }

    public void deleteAnswersFromAPI(ArrayList<Integer> ids) throws IOException {
        deleteEndpointFromAPI("answers", ids);
    }

    public void deleteAdviceFromAPI(ArrayList<Integer> ids) throws IOException {
        deleteEndpointFromAPI("advices", ids);
    }

    private void deleteEndpointFromAPI(String endpoint, ArrayList<Integer> ids) throws IOException {
        if (ids.size() > 0 && endpoint != null && !endpoint.isEmpty()) {
            StringBuilder params = new StringBuilder();
            for (Integer id : ids) {
                if (params.length() == 0) {
                    params.append("?");
                } else {
                    params.append("&");
                }
                params.append("ids=").append(id);
            }
            String URL = AdminVariables.API_URL + "/api/" + endpoint + params;
            requestService.deleteRequest(URL, AdminVariables.token);
        }
    }

    static EditDAO editDAO;
    static public EditDAO getInstance() {
        if (editDAO == null) editDAO = new EditDAO();
        return editDAO;
    }
}
