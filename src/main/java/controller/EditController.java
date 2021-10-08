package controller;

public class EditController {

    private static EditController editController;

    public void changeQuestion(int questionId, String newValue){}
    public void changeAnswer(int questionId, int answerId, String newValue){}
    public void addQuestion(){}
    public void addAnswer(int questionId){}
    public void removeQuestion(int questionId){}
    public void removeAnswer(int questionId, int answerId){}
    public void changeReference(int questionId, int answerId, int referenceQuestionId){}
    public void submitChanges(){}
    public void navigateBack(){}
    //Todo
    public static EditController getInstance(){
        return editController;
    }


}
