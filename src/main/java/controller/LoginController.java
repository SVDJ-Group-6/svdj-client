package controller;

import DAO.LoginDAO;
import model.Login;

public class LoginController {

    private static LoginController loginController;
    //Todo
    private LoginDAO loginDAO;
    private Login login;

    public void login(String username, String password){}
    public void switchToDashboard(){}
    //Todo
    public static LoginController getInstance(){
        return loginController;
    }
}
