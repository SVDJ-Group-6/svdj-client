package controller;

import Admin.AdminVariables;
import model.Email;
import observer.EmailObserver;
import view.LoginView;
import view.RecoveryCodeView;
import DAO.EmailDAO;
import java.io.IOException;

public class EmailController {
    static private EmailController emailController;
    private EmailDAO emailDAO;
    private Email email = new Email();

    public EmailController() {
        emailDAO = EmailDAO.getInstance();

    }

    static public EmailController getInstance(){
        if(emailController == null){
            emailController = new EmailController();
        }
        return emailController;
    }

    public void registerObserver(EmailObserver emailObserver){
        email.registerObserver(emailObserver);
    }

    public void checkEmailInput(String emailInput) {
        try {
            if (!emailInput.contains("@") || emailInput.isEmpty()){
                email.setValidEmail(false);
                System.out.println("fout!");
            } else {
                emailDAO.requestRecoveryCode(emailInput);
                switchToRecovery();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void switchToLogin() {
        AdminVariables.stage.setScene(new LoginView().getLoginScene());
    }

    public void switchToRecovery() {
        AdminVariables.stage.setScene(new RecoveryCodeView().getRecoveryCodeView());
    }
}
