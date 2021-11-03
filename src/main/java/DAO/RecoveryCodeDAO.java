package DAO;

import Admin.AdminVariables;
import model.RecoveryCode;
import observer.RecoveryCodeObserver;
import service.RequestService;

import java.io.IOException;

public class RecoveryCodeDAO {
    private RequestService requestService = RequestService.getInstance();
    private RecoveryCode recoveryCode = new RecoveryCode();
    static RecoveryCodeDAO recoveryCodeDAO;

    static public RecoveryCodeDAO getInstance() {
        if (recoveryCodeDAO == null) {
            recoveryCodeDAO = new RecoveryCodeDAO();
        }
        return recoveryCodeDAO;
    }

    public Boolean checkRecoveryCode(String code) throws IOException {
        String URL = AdminVariables.API_URL + "/api/auth/check_recovery_code/" + code;
        String response = requestService.getRequest(URL, null);
        Boolean isMatching = Boolean.parseBoolean(response);
        recoveryCode.setMatching(isMatching);
        return isMatching;
    }

    public void registerObserver(RecoveryCodeObserver recoveryCodeObserver){
        recoveryCode.registerObserver(recoveryCodeObserver);
    }
}
