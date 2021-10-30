package DAO;

import Admin.AdminVariables;
import model.Email;
import observer.EmailObserver;
import service.RequestService;

import java.io.IOException;

public class EmailDAO {
    private RequestService requestService = RequestService.getInstance();
    private Email email = new Email();
    static EmailDAO emailDAO;

    static public EmailDAO getInstance() {
        if (emailDAO == null) {
            emailDAO = new EmailDAO();
        }
        return emailDAO;
    }

    public void requestRecoveryCode(String emailInput) throws IOException {
        String URL = AdminVariables.API_URL + "/api/auth/request_recovery_code?email=" + emailInput;
        requestService.getRequest(URL, null);
    }

    public void registerObserver(EmailObserver emailObserver){
        email.registerObserver(emailObserver);
    }

}
