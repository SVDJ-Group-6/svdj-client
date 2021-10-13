package DAO;

import model.Edit;
import model.Node;

import java.util.ArrayList;

public class EditDAO {

    private static EditDAO editDAO;
    private Edit edit;

    public void postChanges(ArrayList<Node> nodes){}

    public void changeQuestion(int questionId, String newValue){}
    public void changeAnswer(int questionId, int answerId, String newValue){}
    public void addQuestion(){}
    public void addAnswer(int questionId){}
    public void removeQuestion(int questionId){}
    public void removeAnswer(int questionId, int answerId){}
    public void changeReference(int questionId, int answerId, int referenceQuestionId){}
    //Todo
    public static EditDAO getInstance(){
        return editDAO;
    }


}
