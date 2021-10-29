package controller;

import Admin.AdminVariables;
import DAO.LoginDAO;
import javafx.scene.Scene;
import model.Login;
import observer.LoginObserver;
import view.DashboardView;

import java.io.IOException;

public class LoginController {

    private final Login login;
    private final LoginDAO loginDAO;

    public LoginController() {
        this.login = new Login();
        this.loginDAO = LoginDAO.getInstance();
    }

    public void login(String username, String password) {
        try {
            if (username.isEmpty() && password.isEmpty()) {
                String exceptionMessage = "Fill in all the fields";
                login.setMessage(exceptionMessage);
                return;
            }
            AdminVariables.token = loginDAO.getToken(username, password);
            showDashboardView();
        } catch (IOException e) {
            String exceptionMessage = "Something went wrong";
            login.setMessage(exceptionMessage);
            System.out.println(e);
        }
    }

    public void showDashboardView() {
        Scene dashboard = new DashboardView().getDashboardScene();
        AdminVariables.stage.setScene(dashboard);
    }

    public void registerObserver(LoginObserver loginObserver) {
        login.registerObserver(loginObserver);
    }

    static LoginController loginController;
    static public LoginController getInstance() {
        if (loginController == null) loginController = new LoginController();
        return loginController;
    }
}
