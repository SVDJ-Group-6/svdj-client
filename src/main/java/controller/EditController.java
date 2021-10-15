package controller;

import DAO.EditDAO;
import model.Advice;
import model.Edit;
import model.Question;
import observer.EditObserver;

import java.io.IOException;

public class EditController {

    private static EditController editController;
    private EditDAO editDAO = EditDAO.getInstance();

    public void loadAllNodes() {
        editDAO.loadAllNodes();
    }

    public void changeQuestion(int questionId, String newValue){
        editDAO.changeQuestion(questionId, newValue);
    }

    public void changeAnswer(int questionId, int answerId, String newValue){
        editDAO.changeAnswer(questionId, answerId, newValue);
    }
    public void addQuestion(){
        editDAO.addQuestion();
    }
    public void addAnswer(int questionId){
        editDAO.addAnswer(questionId);
    }

    public void removeQuestion(int questionId){
        editDAO.removeQuestion(questionId);
    }
    public void removeAnswer(int questionId, int answerId){
        editDAO.removeAnswer(questionId, answerId);
    }

    public void changeReference(int questionId, int answerId, Question question){
        editDAO.changeReference(questionId, answerId, question);
    }
    public void changeReference(int questionId, int answerId, Advice advice){
        editDAO.changeReference(questionId, answerId, advice);
    }

    public void submitChanges(){
        try {
            editDAO.postChanges();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void navigateBack(){}

    public void registerObserver(EditObserver editObserver) {
        editDAO.registerObserver(editObserver);
    }

    public static EditController getInstance(){
        if (editController == null) {
            editController = new EditController();
        }
        return editController;
    }


}
