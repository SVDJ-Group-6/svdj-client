package DAO;

import Admin.AdminVariables;
import Client.ClientVariables;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.*;
import observer.EditObserver;
import service.RequestService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class EditDAO {

    private static EditDAO editDAO;
    private final Edit edit = new Edit();
    private RequestService requestService = RequestService.getInstance();
    private Gson gson = new Gson();

    public void loadAllNodes() {
        try {
            ArrayList<Node> nodes = new ArrayList<>();
            ArrayList<Question> questions = getQuestionsFromAPI();
            ArrayList<Advice> advices = getAdvicesFromAPI();
            for (Question question : questions) {
                ArrayList<Answer> answers = getAnswersFromAPI(Integer.toString(question.getId()));
                Node node = new Node(question, answers);
                nodes.add(node);
            }

            for (Advice advice : advices) {
                Node node = new Node(advice);
                nodes.add(node);
            }

            edit.setNodes(nodes);

            edit.setDeletedQuestionIds(new ArrayList<>());
            edit.setDeletedAnswerIds(new ArrayList<>());
            edit.setDeletedAdviceIds(new ArrayList<>());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Question> getQuestionsFromAPI() throws IOException {
        String questionURL = ClientVariables.API_URL + "/api/questions/";
        ArrayList<Question> questions = gson.fromJson(requestService.getResponse(questionURL), new TypeToken<ArrayList<Question>>(){}.getType());
        return questions;
    }

    public ArrayList<Answer> getAnswersFromAPI(String questionID) throws IOException {
        String answersURL = ClientVariables.API_URL + "/api/answers/question/" + questionID;
        ArrayList<Answer> answers = gson.fromJson(requestService.getResponse(answersURL), new TypeToken<ArrayList<Answer>>(){}.getType());
        return answers;
    }

    public ArrayList<Advice> getAdvicesFromAPI() throws IOException {
        String advicesURL = ClientVariables.API_URL + "/api/advices/";
        ArrayList<Advice> advices = gson.fromJson(requestService.getResponse(advicesURL), new TypeToken<ArrayList<Advice>>(){}.getType());
        return advices;
    }

    public void postChanges() throws IOException {

        for (int answerID : edit.getDeletedAnswerIds()) {
            String answerURL = AdminVariables.API_URL + "/api/answers/" + answerID + "?token=a612078c8a93ccc084ee565cfc471bb6";
            requestService.deleteRequest(answerURL);
        }

        for (int adviceID : edit.getDeletedAdviceIds()) {
            String adviceURL = AdminVariables.API_URL + "/api/advices/" + adviceID + "?token=a612078c8a93ccc084ee565cfc471bb6";
            requestService.deleteRequest(adviceURL);
        }

        for (int questionID : edit.getDeletedQuestionIds()) {
            String questionURL = AdminVariables.API_URL + "/api/questions/" + questionID + "?token=a612078c8a93ccc084ee565cfc471bb6";
            requestService.deleteRequest(questionURL);
        }

        ArrayList<Question> questions = new ArrayList<>();
        ArrayList<Answer> answers = new ArrayList<>();
        ArrayList<Advice> advices = new ArrayList<>();

        for (Node node : edit.getNodes()) {
            if (node.getQuestion() != null) {
                questions.add(node.getQuestion());
                answers.addAll(node.getAnswers());
            }

            if (node.getAdvice() != null) {
                advices.add(node.getAdvice());
            }
        }

        for (Question question: questions) {
            String questionsURL = AdminVariables.API_URL + "/api/questions?token=a612078c8a93ccc084ee565cfc471bb6";
            try {
                requestService.postRequest(questionsURL, question.toJsonString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (Advice advice: advices) {
            String advicesURL = AdminVariables.API_URL + "/api/advices?token=a612078c8a93ccc084ee565cfc471bb6";
            try {
                requestService.postRequest(advicesURL, advice.toJsonString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (Answer answer: answers) {
            String answersURL = AdminVariables.API_URL + "/api/answers?token=a612078c8a93ccc084ee565cfc471bb6";
            try {
                requestService.postRequest(answersURL, answer.toJsonString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        loadAllNodes();
    }

    public void changeQuestion(int questionId, String newValue){
        ArrayList<Node> nodes = edit.getNodes();
        Question question = findQuestionWithID(nodes, questionId);
        if (question != null) {
            question.setValue(newValue);
            edit.setNodes(nodes);
        }
    }

    public void changeAnswer(int questionId, int answerId, String newValue){
        ArrayList<Node> nodes = edit.getNodes();
        Answer toModifyAnswer = findAnswerWithQuestionIDAndAnswerID(nodes, questionId, answerId);
        if (toModifyAnswer != null) {
            toModifyAnswer.setValue(newValue);
            edit.setNodes(nodes);
        }
    }

    public void addQuestion(){
        ArrayList<Node> nodes = edit.getNodes();
        Question question = new Question(getQuestionID(nodes), "");
        Node node = new Node(question, new ArrayList<>());
        nodes.add(node);
        edit.setNodes(nodes);
    }
    public void addAnswer(int questionId){
        ArrayList<Node> nodes = edit.getNodes();
        Node node = findNodeWithQuestionID(nodes, questionId);
        if (node != null) {
            ArrayList<Answer> toModifyAnswers = node.getAnswers();
            Answer answer = new Answer(getAnswerID(nodes), "", questionId);
            toModifyAnswers.add(answer);
            node.setAnswers(toModifyAnswers);
            edit.setNodes(nodes);
        }
    }

    public void removeQuestion(int questionId){
        ArrayList<Node> nodes = edit.getNodes();
        Node node = findNodeWithQuestionID(nodes, questionId);
        if (node != null) {
            edit.addDeletedQuestionID(node.getQuestion().getId());
            for (Answer answer : node.getAnswers()) {
                edit.addDeletedAnswerID(answer.getId());
            }
        }
        nodes.remove(node);
        removeAllReferencesToQuestionID(nodes, questionId);
        System.out.println(edit.getDeletedAnswerIds().size());
        edit.setNodes(nodes);
    }

    public void removeAnswer(int questionId, int answerId){
        ArrayList<Node> nodes = edit.getNodes();
        Node toModifyNode = findNodeWithQuestionID(nodes, questionId);
        if (toModifyNode != null) {
            Answer toDelete = findAnswerWithQuestionIDAndAnswerID(nodes, questionId, answerId);
            ArrayList<Answer> newAnswers = toModifyNode.getAnswers();
            newAnswers.remove(toDelete);
            toModifyNode.setAnswers(newAnswers);
        }
        edit.addDeletedAnswerID(answerId);
        edit.setNodes(nodes);
    }

    public void changeReference(int questionId, int answerId, Question question){
        ArrayList<Node> nodes = edit.getNodes();
        Answer toModifyAnswer = findAnswerWithQuestionIDAndAnswerID(nodes, questionId, answerId);
        if (toModifyAnswer != null) {
            toModifyAnswer.setNextQuestionId(question.getId());
            toModifyAnswer.setAdviceId(null);
            edit.setNodes(nodes);
        }
    }

    public void changeReference(int questionId, int answerId, Advice advice){
        ArrayList<Node> nodes = edit.getNodes();
        Answer toModifyAnswer = findAnswerWithQuestionIDAndAnswerID(nodes, questionId, answerId);
        if (toModifyAnswer != null) {
            toModifyAnswer.setAdviceId(advice.getId());
            toModifyAnswer.setNextQuestionId(null);
            edit.setNodes(nodes);
        }
    }

    public void registerObserver(EditObserver editObserver) {
        edit.registerObserver(editObserver);
    }

    public static EditDAO getInstance(){
        if (editDAO == null) {
            editDAO = new EditDAO();
        }
        return editDAO;
    }

    private Node findNodeWithQuestionID(ArrayList<Node> nodes, int questionID) {
        for (Node node : nodes) {
            Question question = node.getQuestion();
            if (question != null && question.getId() == questionID) {
                return node;
            }
        }
        return null;
    }

    private Question findQuestionWithID(ArrayList<Node> nodes, int questionID) {
        Node node = findNodeWithQuestionID(nodes, questionID);
        if (node == null) {
            return null;
        }
        return node.getQuestion();
    }

    private Answer findAnswerWithQuestionIDAndAnswerID(ArrayList<Node> nodes, int questionID, int answerID) {
        Node node = findNodeWithQuestionID(nodes, questionID);
        for (Answer answer : node.getAnswers()) {
            if (answer.getId() == answerID) {
                return answer;
            }
        }
        return null;
    }

    private void removeAllReferencesToQuestionID(ArrayList<Node> nodes, int questionID) {
        for (Node node : nodes) {
            if (node.getQuestion() != null) {
                ArrayList<Answer> answers = node.getAnswers();
                for (Answer answer : answers) {
                    if (answer.getNextQuestionId() != null && answer.getNextQuestionId() == questionID) {
                        answer.setNextQuestionId(null);
                    }
                }
            }
        }
    }

    private int getQuestionID(ArrayList<Node> nodes) {
        int id = 0;
        for (Node node : nodes) {
            if (node.getQuestion() != null) {
                id = node.getQuestion().getId();
            }
        }
        return id + 1;
    }

    private int getAnswerID(ArrayList<Node> nodes) {
        int id = 0;
        for (Node node : nodes) {
            if (node.getQuestion() != null) {
                for (Answer answer : node.getAnswers()) {
                    id = answer.getId();
                }
            }
        }
        return id + 1;
    }

}
