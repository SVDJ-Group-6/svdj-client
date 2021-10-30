package controller;

import DAO.AdviceDAO;
import model.Advice;
import observer.AdviceObserver;
import view.HomeView;

import java.io.IOException;

import Admin.AdminVariables;

public class AdviceController {

    private static AdviceController adviceController;
    private AdviceDAO adviceDAO = AdviceDAO.getInstance();
    private Advice advice = new Advice();

    public void loadAdvice(int adviceId){
        try {
            Advice newAdvice = adviceDAO.getAdviceFromAPI(adviceId);
            advice.replaceAdvice(newAdvice);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAdviceToMail(String mail, Advice advice){

    }
    public void openUrl(String url){
        
    }
    public void navigateHome(){
        
    }

    public static AdviceController getInstance(){
        if (adviceController == null) {
            adviceController = new AdviceController();
        }
        return adviceController;
    }

    public void registerObserver(AdviceObserver adviceObserver) {
        advice.registerObserver(adviceObserver);
    }
}
