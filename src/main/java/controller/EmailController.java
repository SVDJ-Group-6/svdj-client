package controller;

import Admin.AdminVariables;
import view.LoginView;

public class EmailController {
    static private EmailController emailController;

    static public EmailController getInstance(){
        if(emailController == null){
            emailController = new EmailController();
        }
        return emailController;
    }


    public void switchToLogin() {
        AdminVariables.stage.setScene(new LoginView().getLoginScene());
    }
}
