package DAO;

import model.Login;
import observer.LoginObserver;

public class LoginDAO {
    static LoginDAO loginDAO;
    public Login login;

    public LoginDAO() {
        login = new Login();
    }

    static public LoginDAO getInstance() {
        if (loginDAO == null) {
            loginDAO = new LoginDAO();
        }
        return loginDAO;
    }

    public String requestToken(String username, String password_hash) {
        login.setMessage("This is working");
        return "Token";
    }

    public void registerObserver(LoginObserver loginObserver) {
        login.registerObserver(loginObserver);
    }
}
