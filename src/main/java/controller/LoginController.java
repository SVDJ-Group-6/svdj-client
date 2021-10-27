package controller;

import Admin.AdminVariables;
import DAO.LoginDAO;
import observer.LoginObserver;
import view.DashboardView;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

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
        if (username.isEmpty() && password.isEmpty()) {
            loginDAO.setMessage("Fill in all the required fields!");
            return;
        }

        try {
            loginDAO.setMessage("Checking credentials...");
            String token = loginDAO.requestToken(username, password);
            AdminVariables.token = token;
            switchToDashboard();
        } catch (NoSuchAlgorithmException | IOException e) {
            loginDAO.setMessage("Combination does not exists.");
            e.printStackTrace();
        }
    }

    public void switchToDashboard() {
        AdminVariables.stage.setScene(new DashboardView().getDashboardScene());
    }

    public void registerObserver(LoginObserver loginObserver){
        loginDAO.registerObserver(loginObserver);
    }
}
