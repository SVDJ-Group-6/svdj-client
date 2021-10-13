package controller;

import Admin.AdminVariables;
import DAO.LoginDAO;
import observer.LoginObserver;
import view.DashboardView;

public class LoginController {
    static private LoginController loginController;
    private LoginDAO loginDAO;

    public LoginController(){
        loginDAO = LoginDAO.getInstance();
    }

    static public LoginController getInstance(){
        if(loginController == null){
            loginController = new LoginController();
        } 
        return loginController;
    }

    public void login(String username, String password){
        loginDAO.requestToken(username, password);
    }

    public void switchToDashboard() {
        AdminVariables.stage.setScene(new DashboardView().Dashboard());
    }

    public void registerObserver(LoginObserver loginObserver){
        loginDAO.registerObserver(loginObserver);
    }
}
