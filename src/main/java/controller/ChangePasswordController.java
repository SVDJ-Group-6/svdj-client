package controller;

import Admin.AdminVariables;
import DAO.ChangePasswordDAO;
import model.ChangePassword;
import observer.ChangePasswordObserver;
import view.LoginView;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class ChangePasswordController {
    static private ChangePasswordController changePasswordController;
    private ChangePasswordDAO changePasswordDAO = ChangePasswordDAO.getInstance();
    private ChangePassword changePassword = new ChangePassword();

    static public ChangePasswordController getInstance(){
        if(changePasswordController == null){
            changePasswordController = new ChangePasswordController();
        }
        return changePasswordController;
    }

    public void registerObserver(ChangePasswordObserver changePasswordObserver){
        changePassword.registerObserver(changePasswordObserver);
    }

    public boolean checkIdenticalPasswords(String password1, String password2) {
        if(password1.equals(password2)){
            return true;
        }else{
            return false;
        }
    }

    public void changePassword(String newPassword, String recoveryCode){
        try {
            changePasswordDAO.postPassword(newPassword, recoveryCode);
            switchToHome();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void switchToHome() {
        AdminVariables.stage.setScene(new LoginView().getLoginScene());
    }

    public void setMessage(String message){
        changePassword.setMessage(message);
    }


}
