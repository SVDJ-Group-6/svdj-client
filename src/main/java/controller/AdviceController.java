package controller;

import DAO.AdviceDAO;
import model.Advice;

public class AdviceController {

    private static AdviceController adviceController;
    private AdviceDAO adviceDAO;

    public void loadAdvice(int adviceId){}
    public void sendAdviceToMail(String mail, Advice advice){}
    public void openUrl(String url){}
    public void navigateHome(){}

    //Todo
    public static AdviceController getInstance(){
        return adviceController;
    }
}
