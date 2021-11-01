package controller;

import DAO.EditDAO;
import model.*;
import observer.EditObserver;

import java.io.IOException;
import java.util.ArrayList;

public class EditController {

    private final Edit edit = new Edit();
    private final EditDAO editDAO = EditDAO.getInstance();

    public void initialize() {
        try {
            ArrayList<Node> allNodes = editDAO.getAllNodes();
            edit.setNodes(allNodes);
            edit.resetDeletedState();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeQuestion(int questionID, String newValue){
        edit.changeQuestion(questionID, newValue);
    }

    public void changeAnswer(int questionId, int answerId, String newValue){
        edit.changeAnswer(questionId, answerId, newValue);
    }

    public void changeAdvice(int adviceId, String newValue) {
        edit.changeAdvice(adviceId, newValue);
    }

    public void changeAdviceDescription(Integer adviceID, String newDescription) {
        edit.changeAdviceDescription(adviceID, newDescription);
    }

    public void changeAdviceMoreInfoURL(Integer adviceID, String newURL) {
        edit.changeAdviceMoreInfoURL(adviceID, newURL);
    }

    public void changeAdviceVideoURL(Integer adviceID, String newURL) {
        edit.changeAdviceVideoURL(adviceID, newURL);
    }

    public void changeAdviceOtherFundURL(Integer adviceID, String newURL) {
        edit.changeAdviceOtherFundURL(adviceID, newURL);
    }

    public void addQuestion() {
        edit.addQuestion();
    }
    public void addAnswer(int questionId){
        edit.addAnswer(questionId);
    }

    public void addAdvice() {
        edit.addAdvice();
    }

    public void removeQuestion(int questionId){
        edit.removeQuestion(questionId);
    }

    public void removeAnswer(int questionId, int answerId){
        edit.removeAnswer(questionId, answerId);
    }

    public void removeAdvice(int adviceId) {
        edit.removeAdvice(adviceId);
    }

    public void changeReference(int questionId, int answerId, Question question){
        edit.changeReference(questionId, answerId, question);
    }
    public void changeReference(int questionId, int answerId, Advice advice){
        edit.changeReference(questionId, answerId, advice);
    }

    public void submitChanges() {
        try {
            editDAO.deleteAnswersFromAPI(edit.getDeletedAnswerIds());
            editDAO.deleteQuestionsFromAPI(edit.getDeletedQuestionIds());
            editDAO.deleteAdviceFromAPI(edit.getDeletedAdviceIds());

            ArrayList<Question> questions = edit.getAllQuestions();
            editDAO.postQuestionsToAPI(questions);

            ArrayList<Advice> advices = edit.getAllAdvices();
            editDAO.postAdviceToAPI(advices);

            ArrayList<Answer> answers = edit.getAllAnswers();
            editDAO.postAnswersToAPI(answers);

            initialize();

            // TODO MVC bericht van status
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registerObserver(EditObserver editObserver) {
        edit.registerObserver(editObserver);
    }

    static EditController editController;
    static public EditController getInstance() {
        if (editController == null) editController = new EditController();
        return editController;
    }

}
