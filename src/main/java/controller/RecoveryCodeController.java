package controller;

import Admin.AdminVariables;
import DAO.RecoveryCodeDAO;
import observer.RecoveryCodeObserver;
import view.ChangePasswordView;


import java.io.IOException;

public class RecoveryCodeController {
    static private RecoveryCodeController recoveryCodeController;
    private RecoveryCodeDAO recoveryCodeDAO;

    public RecoveryCodeController(){
        recoveryCodeDAO = RecoveryCodeDAO.getInstance();
    }


    static public RecoveryCodeController getInstance(){
        if(recoveryCodeController == null){
            recoveryCodeController = new RecoveryCodeController();
        }
        return recoveryCodeController;
    }

    public void registerObserver(RecoveryCodeObserver recoveryCodeObserver){
        recoveryCodeDAO.registerObserver(recoveryCodeObserver);
    }

    public void switchToChangePassword(String code) {
        AdminVariables.stage.setScene(new ChangePasswordView(code).getChangePasswordScene());
    }

    public void checkRecoveryCode(String code){
        try {
            Boolean isMatching = recoveryCodeDAO.checkRecoveryCode(code);
            if (isMatching) {
                switchToChangePassword(code);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
