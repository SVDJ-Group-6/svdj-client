package controller;

import model.Login;

public class LoginController {

    private static LoginController loginController;
    //Todo
    private LoginService loginService;
    private Login login;

    public void login(String username, String password){}
    public void switchToDashboard(){}
    //Todo
    public static LoginController getInstance(){
        return loginController;
    }
}
