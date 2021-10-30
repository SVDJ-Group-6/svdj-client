package DAO;

import Admin.AdminVariables;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
        String response = requestService.getResponse(URL);
        return gson.fromJson(response, new TypeToken<ArrayList<Question>>(){}.getType());
    }

    public ArrayList<Advice> getAdvicesFromAPI() throws IOException {
        String URL = AdminVariables.API_URL + "/api/advices/";
        String response = requestService.getResponse(URL);
        return gson.fromJson(response, new TypeToken<ArrayList<Advice>>(){}.getType());
    }

    public ArrayList<Answer> getAnswersFromAPI(String questionID) throws IOException {
        String URL = AdminVariables.API_URL + "/api/answers/question/" + questionID;
        String response = requestService.getResponse(URL);
        return gson.fromJson(response, new TypeToken<ArrayList<Answer>>(){}.getType());
    }

    public void postAddNodes() {
        // TODO POST ALL NODES
    }

    static EditDAO editDAO;
    static public EditDAO getInstance() {
        if (editDAO == null) editDAO = new EditDAO();
        return editDAO;
    }
}
